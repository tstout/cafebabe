(ns cafebabe.reader-test
  (:require [expectations :refer :all]
            [cafebabe.reader :refer [decode-class decode-class-file]]
            [cafebabe.fixtures :refer [empty-class]]
            [clojure.string :as str])
  (:import (java.io ByteArrayOutputStream)
           (cafebabe.prototypes Empty)))


(defn class-file-path
  "Used to read prototype class files for testing purposes"
  [^Class c]
  (let [cname (-> c .getName (str/replace "." "/"))]
    (str "target/classes/" cname ".class")))

;;
;; Verify decode-class creates data which
;; conforms to the expected structure for
;; a minimal, empty java class.
;; An empty class has the following shape:
;;
;;{:header        {:magic 3405691582 :major-version 0 :minor-version 52}
;   :constant-pool [{:constant-type :c-method-ref :class-index 3 :name-and-type-index 10}
;                   {:constant-type :c-class :name-index 11}
;                   {:constant-type :c-class :name-index 12}
;                   {:constant-type :c-utf8 :str "<init>"}
;                   {:constant-type :c-utf8 :str "()V"}
;                   {:constant-type :c-utf8 :str "Code"}
;                   {:constant-type :c-utf8 :str "LineNumberTable"}
;                   {:constant-type :c-utf8 :str "SourceFile"}
;                   {:constant-type :c-utf8 :str "Empty.java"}
;                   {:constant-type :c-name-and-type :descriptor-index 4 :name-index 5}
;                   {:constant-type :c-utf8 :str "cafebabe/prototypes/Empty"}
;                   {:constant-type :c-utf8 :str "java/lang/Object"}]
;   :access-flags  33
;   :this-class    2
;   :super-class   3
;   :interfaces    []
;   :fields        []
;   :methods       [{:access-flags     1
;                    :name-index       4
;                    :descriptor-index 5
;                    :attributes       [{:attribute-name-index 6
;                                        :info                 [0 1 0 1 0 0 0 5 42 183 0 1 177 0 0 0 1 0 7 0 0 0 6 0 1 0 0 0 6]}]}]
;   :attributes    [{:attribute-name-index 8 :info [0 9]}]})
;;
(expect empty-class
        (decode-class Empty))

(expect empty-class
        (->
          Empty
          class-file-path
          decode-class-file))





