(ns cool-clojure-snippets.core
  (:require [cool-clojure-snippets.eratosthenes_sieve :as sieve])
  (:gen-class))

(defn -main
  [& args]
  (declare runAll)
  (runAll))

(defn runAll []
  (println "________________________ \n")
  (println "Running all the snippets")
  (println "________________________ \n")
  (println "Generating all prime numbers less than 1000:")
  (println (sieve/generate-primes 1000)))