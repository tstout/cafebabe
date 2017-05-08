(ns cafebabe.methods
  (:require [cafebabe.constant-pool :refer [cp-resolve]]
            [gloss.io :refer [decode encode]]
            [cafebabe.writer :refer [vec-to-bytes]]
            [cafebabe.instructions :refer [op-code]]
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
    (map-indexed
      (fn [index m]
        (let [{:keys [name-index descriptor-index]} m]
          {:name       (cp-resolve class-data name-index)
           :descriptor (cp-resolve class-data descriptor-index)
           :code       (method-code m class-data)
           :index      index}))
      (class-data :methods))
    vec))

(defn positions
  [pred coll]
  (keep-indexed
    (fn [idx x] (when (pred x) idx))
    coll))

(defn index-of [class-data name]
  )


;;; TODO - need to deal with overloaded signatures...
(defn method-by-name [class-data name]
  (->
    (filter
      #(= (:name %) name)
      (ls-methods class-data))
    first))

(defn to-byte-array [v]
  (vec
    (map #(byte %) v)))

(defn decode-method
  ""
  [class-data name]
  (let [meth (method-by-name class-data name)]
    (->>
      meth
      :code
      vec-to-bytes
      (decode code-attribute)
      (assoc meth :code-attribute))))

(defn intercept-before [opts]
  (let [{:keys [class-data method-name func]} opts]
    ))


(defn readable-opcodes
  ""
  [class-data m-name]
  (->>
    m-name
    (decode-method class-data)
    :code
    (map (fn [op] (op-code op)))
    vec))

;;
;; REPL experiments...
;;
(comment
  (def t-data (rdr/parse-class cafebabe.prototypes.PropsAndMethods))
  (def t-code (-> (meth/meth-by-name t-data "sum") :code))
  (def t-meth (meth/decode-method t-data "sum"))
  )

