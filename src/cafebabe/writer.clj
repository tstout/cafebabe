(ns cafebabe.writer
  (:require [gloss.io :refer [encode contiguous]]
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

(defn class-to-file
  ""
  [dest class-data]
  (bytes-to-file
    dest
    (contiguous
      (encode class-codec class-data))))


