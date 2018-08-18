(ns cafebabe.signature-test
  (:require
    [expectations :refer [expect]])
  (:require [cafebabe.signature :refer [xfrm-sig]])
  (:import (cafebabe.prototypes PropsAndMethods)))

(expect
  "(II)I"
  (xfrm-sig PropsAndMethods "sum"))

(expect
  "()Z"
  (xfrm-sig PropsAndMethods "boolFieldValue"))

(expect
  "()F"
  (xfrm-sig PropsAndMethods "floatFieldValue"))

(expect
  "()[B"
  (xfrm-sig PropsAndMethods "byteArrayField"))

(expect
  "()Ljava/lang/String;"
  (xfrm-sig PropsAndMethods "stream"))

;;
;; REPL Experiments
;;
#_
(def sum-sig
  (sig/meth-sig PropsAndMethods "sum"))