(ns cafebabe.reader-test
  (:require [expectations :refer :all]
            [cafebabe.reader :refer [parse-class]]
            [cafebabe.fixtures :refer [empty-class]])
  (:import (java.io ByteArrayOutputStream)
           (cafebabe.prototypes Empty)))


(expect empty-class
        (parse-class Empty))

