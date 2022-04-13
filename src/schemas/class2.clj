(ns schemas.class2
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

;(s/defrecord Patient
;  [id :- Long name :- s/Str])
;
;(pprint (Patient. 1 "John"))
;(pprint (->Patient 1 "John"))

(def Patient
  "Patient schema"
  {:id s/Num :name s/Str})

(pprint (s/explain Patient))
(pprint (s/validate Patient {:id 12 :name "John"}))

(println "\n\n")

(s/defn new-patient :- Patient
  [id :- s/Num name :- s/Str]
  {:id id :name name})

(pprint (new-patient 12 "Mary"))

(println "\n\n")

(s/defn only-positive?
  [value :- s/Num]
  (> value 0))

(def OnlyPositive (s/pred only-positive? 'OnlyPositive))

(pprint (s/validate OnlyPositive 16))

(println "\n\n")

(def OnlyPositive2 (s/pred
                    (s/fn
                      [value :- s/Num]
                      (> value 0)) 'OnlyPositive2))

(pprint (s/validate OnlyPositive2 16))

