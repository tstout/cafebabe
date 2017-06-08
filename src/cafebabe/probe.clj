;;
;; https://groups.google.com/forum/#!topic/clojure/KmNbLo8xTSs
;;
(ns cafebabe.probe
  (:gen-class :methods [^:static [onEntry [String ] void]
                        ^:static [onExit [String] void]]
              :name cafebabe.Probe
              :main false
              :exposes-methods [onEntry onExit]))

(defn -onEntry [s]
  (prn "onEntry called"))

(defn -onExit [s]
  (prn "onExit called"))


;;
;; REPL experiments...
;;
(comment
  (probe/-onEntry)
  )