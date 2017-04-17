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

(defn parse-class [^Class c]
  (decode class-codec (class-bytes c) false))

;;
;; REPL Experiments...
;;
(comment

  (use 'clojure.tools.trace)
  ()
  (trace-ns gloss.core))