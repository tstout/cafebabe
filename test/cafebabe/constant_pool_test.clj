(ns cafebabe.constant-pool-test
  (:require [clojure.test :refer :all])
  (:require [cafebabe.constant-pool :refer :all])
  (:import (cafebabe.prototypes PropsAndMethods)
           (clojure.reflect Method)))



;;
;; REPL experiments
;;
#_(def t-data (rdr/decode-class cafebabe.prototypes.PropsAndMethods))

#_(cp/cp-resolve t-data 1)

#_(:members (r/type-reflect PropsAndMethods))