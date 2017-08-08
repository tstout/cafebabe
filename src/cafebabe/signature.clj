(ns cafebabe.signature
  (:import (clojure.reflect Method))
  (:require [clojure.reflect :as cr]))

(def type-map
  {"int"              "I"
   "java.lang.String" "Ljava/lang/String;"
   "double"           "D"
   "float"            "F"
   "long"             "J"
   "short"            "S"
   "char"             "C"
   "byte"             "B"
   "byte<>"           "[B"
   "boolean"          "Z"
   "void"             "V"})

(defn meth-sigs [c]
  (filter
    #(instance? Method %)
    (:members (cr/reflect c))))

(defn meth-sig [c name]
  (->>
    (meth-sigs c)
    (filter #(= (str (:name %)) name))
    first))

(defn meth-parms [m]
  (->>
    (get-in m [:sig :parameter-types])
    (map name)
    (assoc m :parms)))

(defn meth-ret [m]
  (->>
    m
    :sig
    :return-type
    name
    (assoc m :return)))

(defn to-class-fmt [m]
  (let [args (mapv #(type-map %) (:parms m))
        ret (type-map (:return m))]
    (format
      "(%s)%s"
      (reduce str args)
      ret)))

(defn xfrm-sig
  "Given a class and a method name, return a string conforming to the java class
  file format spec for method signatures.
  See: https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.3"
  [c name]
  (->
    {:sig (meth-sig c name)}
    meth-parms
    meth-ret
    to-class-fmt))
