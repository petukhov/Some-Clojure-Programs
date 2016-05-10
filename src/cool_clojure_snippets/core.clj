(ns cool-clojure-snippets.core
  (:require [cool-clojure-snippets.eratosthenes_sieve :as sieve]
            [cool-clojure-snippets.infix-arithmetic :as infix])
  (:gen-class))

(defn -main
  [& args]
  (declare runAll)
  (runAll))

(defn runAll []
  (println "________________________ \n")
  (println "Running all the snippets")
  (println "________________________ \n")
  #_(println "Generating all prime numbers less than 1000:")
  #_(println (sieve/generate-primes 1000))
  (println "Demonstrating the infix expression being calculated:")
  (println (infix/calculate (5 - 3))))