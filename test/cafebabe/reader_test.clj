(ns cafebabe.reader-test
  (:require [clojure.test :refer :all])
  (:import (java.io ByteArrayOutputStream)
           (cafebabe.prototypes Empty)))





(deftest something
  (let [eClass (Empty.)]
    (is (= 1 1))))
