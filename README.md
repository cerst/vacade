# Value Class Derivations

TODO: documentation links

# Design Principles

* Only add features & convenience which are missing from existing libraries
  * i.e. don't provide methods for every single library out there 
* Don't break with the design on a particular API
  * e.g. if it doesn't have implicits for a particular type, don't retrofit
* All libraries are configured as _Provided_