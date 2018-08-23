(ns cafebabe.reader
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [gloss.io :refer [decode]]
            [cafebabe.class-codec :refer [class-codec
                                          constant-type
                                          constant-pool
                                          cp-info]])
  (:import (java.io ByteArrayOutputStream)
           (clojure.lang DynamicClassLoader)))

(defn slurp-bytes
  "Convert the specified file to a byte array"
  [src]
  (with-open [out (ByteArrayOutputStream.)]
    (io/copy (io/input-stream src) out)
    (.toByteArray out)))

(defn class-stream
  "Given a class, use the class loader to obtain in input stream of the
  raw byte definition of the class"
  [^Class c]
  (let [class-path (->
                     c
                     .getName
                     (str/replace "." "/")
                     (str ".class"))]
    (-> c
        .getClassLoader
        (.getResourceAsStream class-path))))

(defn class-bytes
  "Given a java class, create a byte array of the raw class definition"
  [^Class c]
  (with-open [out (ByteArrayOutputStream.)]
    (io/copy (class-stream c) out)
    (.toByteArray out)))

(defn fix-nil-repeated
  "If the class has no fields or interfaces, the gloss codec I have
  defined decodes a repeated to a nil. This nil causes encode to fail.
  I have not determined how to get gloss to decode an empty repeated
  frame into an empty vector.
  This fix is necessary for encode to function properly when a class
  contains no interfaces or methods."
  [class-data k]
  (if (nil? (k class-data))
    (update class-data k (fn [_] []))
    class-data))

(defn fix-nil-field-attrs
  "See fix-nil-repeated"
  [class-data]
  (let [updated-fields (->>
                         :fields
                         class-data
                         (map (fn [f-info]
                                (if (nil? (:attributes f-info))
                                  (update f-info :attributes (fn [_] []))
                                  f-info)))
                         vec)]
    (update class-data :fields (fn [_] updated-fields))))

(defn utf8? [c-info]
  (= :c-utf8 (c-info :constant-type)))

(defn fix-nil-utf8 [class-data]
  (let [updated-fields (->>
                         :constant-pool
                         class-data
                         (map (fn [c-info]
                                (if (and (utf8? c-info) (nil? (c-info :str)))
                                  (update c-info :str (fn [_] ""))
                                  c-info)))
                         vec)]
    (update class-data :constant-pool (fn [_] updated-fields))))

(defn decode-class-bytes
  "Convert a byte array containing a class definition to clojure data."
  [b]
  (->
    class-codec
    (decode b false)
    (fix-nil-repeated :interfaces)
    (fix-nil-repeated :fields)
    fix-nil-field-attrs
    fix-nil-utf8))

(defn decode-class
  "Convert a class definition to clojure data."
  [^Class c]
  (->
    c
    class-bytes
    decode-class-bytes))

(defn decode-class-file
  "Convert a class file to clojure data"
  [src]
  (->
    src
    slurp-bytes
    decode-class-bytes))

;(defn load-class [name bytes]
;  (.defineClass
;    ^DynamicClassLoader (deref clojure.lang.Compiler/LOADER)
;    name
;    bytes
;    nil))

(defn load-class [name bytes]
  (->
    (DynamicClassLoader.)
    (.defineClass name bytes nil)))


;;
;; loading classes on the fly...
;;
;(.defineClass ^DynamicClassLoader (deref clojure.lang.Compiler/LOADER)
;              (str (:name options-map)) bytecode options)


;;
;; REPL Experiments...
;;
(comment

  (use 'clojure.tools.trace)

  ;(def dyn-class
  ;  (rdr/load-class
  ;    "cafebabe.prototypes.PropsAndMethods"
  ;    (rdr/class-bytes cafebabe.prototypes.PropsAndMethods)))

  (trace-ns gloss.core)

  )
