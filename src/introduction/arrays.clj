(ns introduction.arrays)

(def prices
  [30.0 700.0 1000.0 105.0])

(println (prices 0))
(println (get prices 1))
(println (get prices 4 100))

(println (conj prices 50))

(println (+ 30.0 1.0))
(println (inc 30.0))
(println (get (update prices 0 inc) 0))

(println (- 30.0 1.0))
(println (dec 30.0))
(println (get (update prices 0 dec) 0))

(println (filter #(<= % 30) prices))
(println (map #(+ % 10) prices))

(println (reduce + prices))
(println (reduce #(+ %1 %2) prices))