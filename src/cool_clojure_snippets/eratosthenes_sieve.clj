(ns cool-clojure-snippets.eratosthenes_sieve
  (:require [clojure.set :as set]))

(defn- logical-subtract [c1 c2]
  (vec (set/difference (apply sorted-set c1) (apply sorted-set c2))))

(defn- generate-multiples [upper-limit curr]
  (->> (iterate #(+ %1 curr) curr)                          ; 2, 4, 6, 8, 9 when curr is 2
       (take-while #(< %1 upper-limit))                     ; need to be within upper-limit
       (rest)                                               ; remove the first number because it's prime
       (vec)))

(defn- do-sieve [upper-limit sieved curr]
  (if (some #(= curr %) sieved)
    (logical-subtract sieved (generate-multiples upper-limit curr))
    sieved))

(defn generate-primes [upper-limit]
  (let [range-to-sieve (vec (range 2 upper-limit))
        dividers (vec (range 2 (int (Math/ceil (/ upper-limit 2)))))]
    (reduce (partial do-sieve upper-limit) range-to-sieve dividers)))
