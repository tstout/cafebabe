# cafebabe

# Introduction

There are many libraries available for manipulating JVM bytecode:
bcel, asm, bytebuddy, cglib, byteman, javassist, etc. However, I have not
seen any JVM bytecode tools written in clojure.

Clojure seems to excel at data manipulation. Transforming bytecode, on the surface,
would appear to be a series of data transformations. This is an experiment regarding 
the application of functional techniques towards a simpler implementation of a byte 
code manipulation library.

## Usage
cafebabe
Given a java class, transform the raw bytes into a clojure data structure



## License

Copyright © 2018 Todd Stout

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
