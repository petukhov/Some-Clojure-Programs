(ns cool-clojure-snippets.core
  (:require [cool-clojure-snippets.eratosthenes_sieve :as sieve]
            [cool-clojure-snippets.infix-arithmetic :as i]
            [clojure.test :as test :refer [testing is]])
  (:gen-class))

(defn demo []
  (println "________________________ \n")
  (println "Running all the snippets")
  (println "________________________ \n")
  (println "Generating all prime numbers less than 1000:")
  (println (sieve/generate-primes 1000))
  (println "Demonstrating the infix expression being calculated:")
  (println (i/infix-macro 5 - 3)))

(defn run-tests []
  (testing "Eratosthenes sieve"
    (is (= [2 3 5 7 11 13 17 19 23 29] (sieve/generate-primes 30)) "checking a basic case")
    (is (= [] (sieve/generate-primes 0)) "checking when limit is zero")
    (is (= [] (sieve/generate-primes 2)) "checking when limit is 2")
    (is (= [2] (sieve/generate-primes 3)) "checking when limit is 3"))

  (testing "Infix arithmetic"
    (is (= 51 (i/infix-macro 3 + (5 - 10 / 5) * 16 )) "checking a normal case")
    (is (= 4 (i/infix-macro (((5 - 1))) )) "checking multiple layers of brackets")
    (is (= 6 (i/infix-macro 6 )) "checking just one number")
    (is (= -7 (i/infix-macro ((((-7)))) )) "checking just one number wrapped in many brackets")
    (is (= -52/3 (i/infix-macro (2 / 3) - 18 )) "checking expression resulting in fraction")))

(defn -main
  [& args]
  (do
    (run-tests)
    (demo)))