(ns introduction.maps)

(def storage {:bag 20
              :hat 20})

(println (count storage))
(println (keys storage))
(println (vals storage))

(println (assoc storage :shirt 50))
(println storage)

(println (update storage :bag inc))
(println (update storage :bag #(+ % 10)))

(def purchase-order {:bag {:quantity 20 :price 100.0}
                     :hat {:quantity 10 :price 20.00}})
(println (get purchase-order :items))

(println "\n\n")

(println (-> purchase-order
             :bag
             :quantity))

(def customers {
                :15 {
                     :name      "Guilherme"
                     :certifies ["Clojure" "Java" "Machine Learning"]}})

(println (count (-> customers
                    :15
                    :certifies)))

(println "\n\n")

(map (fn
       [[key value]]
       (println key
                (map
                  (fn [[key value]] (println key " e " value))
                  value)))
     purchase-order)

(defn total-item
  [item]
  (* (:quantity item) (:price item)))

(println (->> purchase-order
              vals
              (map total-item)))

(defn total-purchase
  [total-by-item]
  (reduce + total-by-item))

(println "Total Purchase:" (->> purchase-order
                                vals
                                (map total-item)
                                total-purchase))

(println "Total Purchase:" (total-purchase (->> purchase-order
                                                vals
                                                (map total-item)
                                                )))

(def purchase-order {:bag      {:quantity 20 :price 100.0}
                     :hat      {:quantity 10 :price 20.00}
                     :keychain {:quantity 1}})

(defn is-free?
  [item]
  (= (get item :price -1) -1))

(def is-paid? (comp not is-free?))

(println "Purchase order items than are free:\n" (filter #(is-free? (second %)) purchase-order))

(println "Purchase order items than are paid:\n" (filter #(is-paid? (second %)) purchase-order))

(def customers [
                {:name         "Guilherme"
                 :certificates ["Clojure" "Java" "Machine Learning"]}
                {:name         "Paulo"
                 :certificates ["Java" "Ciência da Computação"]}
                {:name         "Daniela"
                 :certificates ["Arquitetura" "Gastronomia"]}])

(println (reduce + (map
           #(count (get % :certificates))
           customers)))

(println (->> customers
              (map :certificates)
              (map count)
              (reduce +)))