(defproject cool-clojure-snippets "0.1.0-SNAPSHOT"
  :description "Interesting short programs I wrote in Clojure or ClojureScript"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot cool-clojure-snippets.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
