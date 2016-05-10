(ns cool-clojure-snippets.infix-arithmetic)

(def allowed-ops {+ 1
                  - 1
                  * 2
                  / 2})

(defn process-operator [[ops-stack output] operator]
  (let [operator-on-stack (peek ops-stack)]
    (if (or (empty? ops-stack) (< (allowed-ops operator-on-stack) (allowed-ops operator)))
      [(conj ops-stack operator) output]
      (-> [(pop ops-stack) output]
          (assoc 1 (conj output operator-on-stack))
          (process-operator operator)))))

(defn process-token [[ops-stack output] token]
  (cond
    (number? token) [ops-stack (conj output token)]
    (contains? allowed-ops token) (process-operator [ops-stack output] token)
    :else (throw (Exception. "Macro supplied with invalid data"))))

(defn forms->postfix [expr]
  (let [[ops-stack output] (reduce process-token [() ()] expr)]
    (into ops-stack output)))

(defn postfix->value [postfix-stack]
  postfix-stack)

(defn calculate [& expr]
  (-> expr
      (forms->postfix)
      (postfix->value)))
