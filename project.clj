(defproject cafebabe "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [gloss "0.2.6"]
                 [org.clojure/tools.trace "0.7.9"]
                 [expectations "2.2.0-beta1"]]
  :profiles {:uberjar {:aot :all}
             :test    {:java-source-paths ["java"]
                       :dependencies [[expectations "2.2.0-beta1"]
                                      [digest "1.4.5"]
                                      [clj-tagsoup "0.3.0" :exclusions [org.clojure/clojure]]]}
             :dev     {:source-paths      ["dev"]
                       :java-source-paths ["java"]
                       :dependencies      [[org.clojure/tools.namespace "0.2.11"]
                                           [org.clojure/java.classpath "0.2.3"]
                                           [clj-tagsoup "0.3.0" :exclusions [org.clojure/clojure]]
                                           [digest "1.4.5"]]}}
  :plugins [[lein-expectations "0.0.7"]
            [lein-autoexpect "1.9.0"]])
