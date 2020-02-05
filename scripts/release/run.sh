#!/usr/bin/env bash

set -euo pipefail

export RELEASE=true

# greadlink because readlink has no -f on Mac
script_dir_abs_path="$(dirname $(greadlink -f "$0"))"

project_root_abs_path="${script_dir_abs_path}/../.."

pre_release_tasks="\
$(cat ${script_dir_abs_path}/pre_release_tasks)
;versionToFile"

release_tasks=$(cat ${script_dir_abs_path}/release_tasks)


echo "\
=========================
Run sbt PRE-release tasks
${pre_release_tasks}
========================="
( cd ${project_root_abs_path} && sbt --warn ${pre_release_tasks} )


# pipe sbt output to /dev/null as it otherwise interferes with the variable assignment using cat
PROJECT_VERSION=$(cat ${project_root_abs_path}/target/version-to-file/version)
echo ""
echo "Do you want to publish v$PROJECT_VERSION (y/n)?"
while true; do
    read yn
        case ${yn} in
            [Yy]* ) break;;
            [Nn]* ) echo "Aborted" exit 0;;
            * ) echo "Please answer yes or no.";;
        esac
done


echo "\
===========================
Ensure Git branch is master
==========================="
GIT_BRANCH=$(git symbolic-ref --short HEAD)
if [[ ${GIT_BRANCH} -ne "master" ]]; then
    echo "You must be on Git branch 'master' to publish"
    exit 1
else
    echo "Ok"
fi


# super shell messes-up password queries in the terminal
echo "\
=====================
Run sbt release tasks
${release_tasks}
====================="
( cd ${project_root_abs_path} && sbt --warn --supershell=false ${release_tasks} )


echo "\
=================
Release succeeded
================="
