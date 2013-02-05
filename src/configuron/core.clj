(ns configuron.core
  (:require [environ.core :as environ]
        ))

(def ^:private env*
  (let [all-props (eval (read-string (slurp (clojure.java.io/resource "configs.clj"))))
        env-name (or (environ/env :env-name) "default")]
    (merge 
      (get all-props "default")
      (let [x (filter (fn [[k _]] (.startsWith env-name k)) (dissoc all-props env-name))]
        (if (empty? x) {} (second (first x))))
      (if env-name (all-props env-name) {})
      environ/env)))

(defn env
  [key]
  (env* key))