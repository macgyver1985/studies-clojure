(ns introduction.funcs)

(defn apply-discount?
  [value]
  (when (>= value 50.0)
    true))

(defn calculate-discount
  "Calculate the discount value.
  - For values greater than or equal 50 and less than or equal 100, the discount will be 10%.
  - To values greater than 100, the discount will be 15%."
  [apply? value]
  (if (apply? value)
    (if (<= value 100.0)
      (let [percentage 0.1]
        (println "Will be applyed 10%")
        (* value percentage))
      (let [percentage 0.15]
        (println "Will be apllyed 15%")
        (* value percentage)))
    0.0))

(defn apply-discount
  "Apply discount in value."
  [value, discount-value]
  (- value discount-value))

(println
  (let [value 50]
    (apply-discount
      value
      (calculate-discount apply-discount? value))))

(println
  "Lambda 1"
  (let [value 50]
    (apply-discount
      value
      (calculate-discount #(>= %1 50.0) value))))

(println
  "Lambda 2"
  (let [value 50]
    (apply-discount
      value
      (calculate-discount #(>= % 50.0) value))))

(println
  "Lambda 3"
  (let [value 50]
    (apply-discount
      value
      (calculate-discount (fn [v] (>= v 50.0)) value))))

(def apply-discount-lambda? #(>= % 50.0))

(println
  "Lambda 4"
  (let [value 50]
    (apply-discount
      value
      (calculate-discount apply-discount-lambda? value))))