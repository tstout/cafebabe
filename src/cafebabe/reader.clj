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
  "If the class has no fields or interfaces, gloss decodes a repeated
  to a nil. This nil causes encode to fail. I have not determined
  how to get gloss to decode an empty repeated into an empty vector.
  This fix is necessary for encode to function properyly when a class
  contains no interfaces or methods."
  [class-data k]
  (if (nil? (k class-data))
    (update class-data k (fn [_] []))
    class-data))

;;
(defn parse-class [^Class c]
  (->
    (decode class-codec (class-bytes c) false)
    (fix-nil-repeated :interfaces)
    (fix-nil-repeated :fields)))

;;
;; REPL Experiments...
;;
(comment

  (use 'clojure.tools.trace)
  ()
  (trace-ns gloss.core))