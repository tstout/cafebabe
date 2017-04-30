(ns cafebabe.reader-test
  (:require [expectations :refer :all]
            [cafebabe.reader :refer [parse-class]]
            [cafebabe.fixtures :refer [empty-class]])
  (:import (java.io ByteArrayOutputStream)
           (cafebabe.prototypes Empty)))


;;
;; Verify parse class creates data which
;; conforms to the expected structure for
;; a miminmal, empty java class
;;
(expect empty-class
        (parse-class Empty))



