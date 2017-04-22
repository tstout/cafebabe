(ns cafebabe.constant-pool)

(defn cp-lookup
  "Lookup an entry in the constant pool of the specified class data.
  Note: offset is 1 origin, not zero, per the class file spec."
  [class-data offset]
  {:pre [(> offset 0)]}
  (->
    :constant-pool
    class-data
    (nth (dec offset))))

(defmulti cp-resolve
          "Given a map representing a class, create a map containing the
          data for the constant in the constant pool at a specific offset."
          (fn [class-data offset]
            (:constant-type
              (cp-lookup class-data offset))))

(defmethod cp-resolve :c-utf8 [class-data offset]
  (:str (cp-lookup class-data offset)))

(defmethod cp-resolve :c-class [class-data offset]
  (let [{:keys [name-index]} (cp-lookup class-data offset)]
    (cp-resolve class-data name-index)))

(defmethod cp-resolve :c-name-and-type [class-data offset]
  (let [{:keys [descriptor-index name-index]} (cp-lookup class-data offset)]
    {:c-type     :c-name-and-type
     :descriptor (cp-resolve class-data descriptor-index)
     :name       (cp-resolve class-data name-index)}))

(defn name-type [class-data index]
  (let [{:keys [name descriptor]} (cp-resolve class-data index)]
    {:name       name
     :descriptor descriptor}))

(defmethod cp-resolve :c-method-ref [class-data offset]
  (let [entry (cp-lookup class-data offset)
        {:keys [class-index name-and-type-index]} entry]

    (merge
      {:c-type :c-method-ref
       :class  (cp-resolve class-data class-index)}
      (name-type class-data name-and-type-index))))

(defmethod cp-resolve :c-field-ref [class-data offset]
  (let [entry (cp-lookup class-data offset)
        {:keys [class-index name-and-type-index]} entry]
    (merge
      {:c-type :c-field-ref
       :class  (cp-resolve class-data class-index)
       :offset offset}
      (name-type class-data name-and-type-index))))

;;
;; REPL experiments
;;
(comment
  (def t-data (rdr/parse-class cafebabe.prototypes.PropsAndMethods))
  (cp/cp-resolve t-data 1)
  )
