(ns cafebabe.reader-test
  (:require [clojure.test :refer :all])
  (:import (java.io ByteArrayOutputStream)
           (cafebabe.prototypes Empty)))


(deftest java-compile-working
  (let [eClass (Empty.)]
    (is (= 1 1))))
