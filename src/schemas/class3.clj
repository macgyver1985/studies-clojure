(ns schemas.class3
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

(def PosInt (s/pred pos-int?))

(def Patient
  {:id PosInt, :name s/Str})