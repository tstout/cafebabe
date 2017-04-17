(ns user)

;;
;; Look at debugging tools mentioned here...
;; http://brownsofa.org/blog/2014/08/03/debugging-in-clojure-tools/http://brownsofa.org/blog/2014/08/03/debugging-in-clojure-tools/


(prn "---REPL Customizations Initialized---")

(defn load-vars []
  (require
    '[clojure.string :as str]
    '[cafebabe.reader :as rdr]
    '[cafebabe.class-codec :as codec]
    '[gloss.core :as gc]
    '[gloss.io :as gio]
    '[clojure.tools.trace :as trace]))

(load-vars)