(ns cafebabe.instructions
  (:require [clojure.set :refer [map-invert]]))

;; Obviously this is currently incomplete
(def op-code-def
  {50  :aaload
   83  :aastore
   25  :aload
   42  :aload_0
   43  :aload_1
   44  :aload_2
   45  :aload_3
   189 :anewarray
   190 :arraylength
   176 :areturn
   58  :astore
   75  :astore_0
   76  :astore_1
   77  :astore_2
   78  :astore_3
   1   :aconst_null
   191 :athrow
   51  :baload
   84  :bastore
   202 :breakpoint
   85  :castore
   16  :bipush
   52  :caload
   89  :dup
   27  :iload_1
   28  :iload_2
   96  :iadd
   2   :iconst_m1
   3   :iconst_0
   4   :iconst_1
   5   :iconst_2
   6   :iconst_3
   7   :iconst_4
   8   :iconst_5
   172 :ireturn
   186 :invokedynamic
   185 :invokeinterface
   183 :invokespecial
   184 :invokestatic
   182 :invokevirtual
   254 :impdep1
   255 :impdep2
   9   :lconst_0
   10  :lconst_1
   187 :new
   0   :nop
   177 :return
   })

(defn bimap
  [m]
  (merge m (map-invert m)))

(def op-codes
  (bimap op-code-def))

(defn op-code [num-or-keyword]
  {:pre [(or
           (keyword? num-or-keyword)
           (number? num-or-keyword))]}
  (or (op-codes num-or-keyword) :unknown))



