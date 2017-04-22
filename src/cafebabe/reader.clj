(ns cafebabe.reader
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [gloss.io :refer [decode]]
            [cafebabe.class-codec :refer [class-codec
                                          constant-type
                                          constant-pool
                                          cp-info]])
  (:import (java.io ByteArrayOutputStream)))



(defn slurp-bytes
  "Convert the specified file to a byte array"
  [src]
  (with-open [out (ByteArrayOutputStream.)]
    (io/copy (io/input-stream src) out)
    (.toByteArray out)))

(defn class-file-path
  "Used to read prototype class files for testing purposes"
  [^Class c]
  (let [cname (-> c .getName (str/replace "." "/"))]
    (str "target/classes/" cname ".class")))

(defn class-bytes [^Class c]
  (->
    c
    class-file-path
    slurp-bytes))

(defn fix-nil-repeated
  "If the class has no fields or interfaces, the gloss codec I have
  defined decodes a repeated to a nil. This nil causes encode to fail.
  I have not determined how to get gloss to decode an empty repeated
  frame into an empty vector.
  This fix is necessary for encode to function properyly when a class
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

  ;;
  (defn parse-class [^Class c]
    (->
      class-codec
      (decode  (class-bytes c) false)
      (fix-nil-repeated :interfaces)
      (fix-nil-repeated :fields)
      fix-nil-field-attrs))

  ;;
  ;; REPL Experiments...
  ;;
  (comment

    (use 'clojure.tools.trace)
    ()
    (trace-ns gloss.core))