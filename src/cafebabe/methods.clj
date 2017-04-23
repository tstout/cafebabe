(ns cafebabe.methods
  (:require [cafebabe.constant-pool :refer [cp-resolve]]
            [gloss.io :refer [decode encode]]
            [cafebabe.class-codec :refer [code-attribute raw-bytes]]))

(defn method-code [m class-data]
  (->
    (filter
      (fn [attr]
        (= "Code" (cp-resolve class-data (:attribute-name-index attr))))
      (m :attributes))
    first
    :info))

(defn ls-methods [class-data]
  (->
    (map
      (fn [m]
        (let [{:keys [name-index descriptor-index]} m]
          {:name       (cp-resolve class-data name-index)
           :descriptor (cp-resolve class-data descriptor-index)
           :code       (method-code m class-data)}))
      (class-data :methods))
    vec))

;;;
(defn meth-by-name [class-data name]
  (->
    (filter
      #(= (:name %) name)
      (ls-methods class-data))
    first))

(defn to-byte-array [v]
  (vec
    (map #(byte %) v)))

(defn decode-method-code [class-data name]
  (let [m-code (->>
                  name
                  (meth-by-name class-data)
                  :code
                  (encode raw-bytes))]
    (decode code-attribute m-code false)))