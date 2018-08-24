# cafebabe

# Introduction

There are many libraries available for manipulating JVM bytecode:
bcel, asm, bytebuddy, cglib, byteman, javassist, etc. However, I have not
seen any JVM bytecode tools written in clojure.

Clojure seems to excel at data manipulation. Transforming bytecode, on the surface,
appears to be a series of data transformations. This is an experiment regarding 
the application of functional techniques towards a simpler implementation of a byte 
code manipulation library.

## Usage
The functionality is primitive at the moment, but interesting IMHO. A codec is defined which facilitates transforming 
raw class bytes into clojure data. The clojure data can also be transformed back into the raw bytes conforming 
to the class file specification.

For example, given a java class, transform the raw bytes of the class into clojure data.

cafebabe/prototypes/Empty.java
```java
package cafebabe.prototypes;

public class Empty {
}
```

```clojure
(ns an.example
  (:require [cafebabe.reader :refer [decode-class]])
  (:import (cafebabe.prototypes Empty)))
            
 (decode-class Empty)            
```
The result of (decode-class Empty) is
 
```clojure
{:header        {:magic 3405691582 :major-version 0 :minor-version 52}
 :constant-pool [{:constant-type :c-method-ref :class-index 3 :name-and-type-index 10}
                 {:constant-type :c-class :name-index 11}
                 {:constant-type :c-class :name-index 12}
                 {:constant-type :c-utf8 :str "<init>"}
                 {:constant-type :c-utf8 :str "()V"}
                 {:constant-type :c-utf8 :str "Code"}
                 {:constant-type :c-utf8 :str "LineNumberTable"}
                 {:constant-type :c-utf8 :str "SourceFile"}
                 {:constant-type :c-utf8 :str "Empty.java"}
                 {:constant-type :c-name-and-type :descriptor-index 4 :name-index 5}
                 {:constant-type :c-utf8 :str "cafebabe/prototypes/Empty"}
                 {:constant-type :c-utf8 :str "java/lang/Object"}]
 :access-flags  33
 :this-class    2
 :super-class   3
 :interfaces    []
 :fields        []
 :methods       [{:access-flags     1
                  :name-index       4
                  :descriptor-index 5
                  :attributes       [{:attribute-name-index 6
                                      :info                 [0 1 0 1 0 0 0 5 42 183 0 1 177 0 0 0 1 0 7 0 0 0 6 0 1 0 0 0 6]}]}]
 :attributes    [{:attribute-name-index 8 :info [0 9]}]}
```
The cafebabe.reader namespace also contains decode-class functions for a file, and a byte array.
The clojure data representing a class can be transformed into a binary form consumable by the JVM via functions
in the cafebabe.writer namespace.

```clojure
(ns an.example
  (:require [cafebabe.reader :refer [decode-class]]
            [cafebabe.writer :refer [encode-class]])
  (:import (cafebabe.prototypes Empty)))
  
(def transformed-class 
    (->
      Empty
      decode-class
      some-transform
      encode-class))  
```
The some-transform function is a bit hand-wavy. I plan on providing various class transformation functions in the near
future.

## License

Copyright © 2018 Todd Stout

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
