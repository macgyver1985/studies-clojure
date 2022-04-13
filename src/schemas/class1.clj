(ns schemas.class1
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(defn add-patient
  [patients patient]
  (if-let [id (:id patient)]
    (assoc patients id patient)
    (throw (ex-info "Patient has no id" {:patient patient}))))

(defn add-visits
  [visits patient-id new-visits]
  (if (contains? visits patient-id)
    (update visits patient-id concat new-visits)
    (assoc visits patient-id new-visits)))

(defn test-patient-use []
  (let [pt1 {:id 1 :name "Guilherme"}
        pt2 {:id 2 :name "John"}
        pt3 {:id 3 :name "Mary"}
        patients (reduce add-patient {} [pt1 pt2 pt3])
        visits {}]
    (pprint patients)
    (pprint (add-visits visits 1 ["01/01/2019"]))
    (pprint (add-visits visits 2 ["01/01/2019" "01/01/2020"]))
    (pprint (add-visits visits 1 ["01/03/2019"]))))

(test-patient-use)

(println "\n\n")

(defn variation1-test-patient-use []
  (let [pt1 {:id 1 :name "Guilherme"}
        pt2 {:id 2 :name "John"}
        pt3 {:id 3 :name "Mary"}
        patients (reduce add-patient {} [pt1 pt2 pt3])
        visits {}
        visits (add-visits visits 1 ["01/01/2019"])
        visits (add-visits visits 2 ["01/01/2019" "01/01/2020"])
        visits (add-visits visits 1 ["01/03/2019"])]
    (pprint patients)
    (pprint visits)))

(variation1-test-patient-use)

(println "\n\n")

(defn variation2-test-patient-use []
  (let [pt1 {:id 1 :name "Guilherme"}
        pt2 {:id 2 :name "John"}
        pt3 {:id 3 :name "Mary"}
        patients (reduce add-patient {} [pt1 pt2 pt3])
        visits (reduce #(add-visits %1 (:id %2) (:visits %2))
                       {} [{:id 1 :visits ["01/01/2019"]}
                           {:id 2 :visits ["01/01/2019" "01/01/2020"]}
                           {:id 1 :visits ["01/03/2019"]}])]
    (pprint patients)
    (pprint visits)))

(variation2-test-patient-use)

(println "\n\n")

(s/set-fn-validation! true)

(def patients (reduce
                #(add-patient %1 %2)
                {} [{:id 1 :name "Guilherme"}
                    {:id 2 :name "John"}
                    {:id 3 :name "Mary"}]))

(def visits (reduce
              #(add-visits %1 (:id %2) (:visits %2))
              {} [{:id 1 :visits ["01/01/2019"]}
                  {:id 2 :visits ["01/01/2019" "01/01/2020"]}
                  {:id 1 :visits ["01/03/2019"]}]))

(s/defn print-visits-patient-report
  [visits patient-id :- Long]
  (let [visits (get visits patient-id)]
    (println
      "The number of visits patient"
      patient-id "is"
      (count visits)
      "and were in"
      visits)))

(pprint visits)

(print-visits-patient-report visits (id (patients 1)))