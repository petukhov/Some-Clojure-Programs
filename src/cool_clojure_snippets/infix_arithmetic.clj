(ns cool-clojure-snippets.infix-arithmetic)

(def allowed-ops {+ 1, - 1, * 2, / 2})

(defn- process-operator [[ops-stack output] operator]
  (let [operator-on-stack (peek ops-stack)]
    (if (or (empty? ops-stack) (< (allowed-ops operator-on-stack) (allowed-ops operator)))
      [(conj ops-stack operator) output]
      (-> [(pop ops-stack) output]                          ; popping off ops-stack
          (assoc 1 (conj output operator-on-stack))         ; and putting that operator on the output stack
          (process-operator operator)))))

(defn- process-token [[ops-stack output] token]
  (cond
    (number? token) [ops-stack (conj output token)]
    (contains? allowed-ops token) (process-operator [ops-stack output] token)
    :else (throw (Exception. "Macro supplied with invalid data"))))

(defn- forms->postfix [expr]
  (let [[ops-stack output] (reduce process-token [() ()] expr)]
    (into ops-stack output)))                               ; since ops-stack is not empty, we need to merge it with output

(defn- calculate [stack token]
  (if (number? token)
    (conj stack token)
    (conj (drop 2 stack) (token (second stack) (first stack)))))

(defn- postfix->value [postfix-stack]
  (first (reduce calculate () postfix-stack)))

(defn infix [& expr]
  (-> expr
      (forms->postfix)
      (postfix->value)))
