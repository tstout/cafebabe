(ns cafebabe.writer
  (:require [gloss.io :refer [encode contiguous]]
            [gloss.core :refer [defcodec
                                enum
                                string
                                repeated
                                header
                                prefix
                                finite-frame
                                ordered-map]]
            [cafebabe.class-codec :refer [class-codec]]
            [clojure.java.io :as io])
  (:import (java.io ByteArrayOutputStream FileOutputStream)
           (java.nio.channels WritableByteChannel Channels)))

(defn bytes-to-file
  ""
  [^String dest bytes]
  (with-open [out (FileOutputStream. dest)
              channel (Channels/newChannel out)]
    (.write channel bytes)))

(defn bytes-to-array
  [encoded]
  (with-open [out (ByteArrayOutputStream.)
              channel (Channels/newChannel out)]
    (.write channel encoded)
    (.toByteArray out)))

(defn encode-class
  "Given a class data structure, transform it to a byte array"
  [c]
  (->>
    c
    (encode class-codec)
    contiguous
    bytes-to-array))

(defcodec byte-vec
          (repeated :ubyte :prefix :uint32))

(defn vec-to-bytes [v]
  (encode byte-vec v))



(defn class-to-file
  ""
  [dest class-data]
  (bytes-to-file
    dest
    (contiguous
      (encode class-codec class-data))))


