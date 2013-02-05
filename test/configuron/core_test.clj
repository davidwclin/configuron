(ns configuron.core-test
  (:use clojure.test)
  (:require [configuron.core :refer [env]]))

(defn reload []
  (require 'environ.core :reload)
  (require 'configuron.core :reload))

(deftest test-devweb
  (spit ".lein-env" (prn-str {:env-name "devweb"}))
  (reload)
  (is (= 5 (env :max-workers)))
  (is (= "c:/temp" (env :temp-dir)))
  (is (= true (env :async?)))
  (is (= false (env :google-analytics?)))
  (is (= "xxxxx" (env :aws-key)))
  (is (= 5000 (env :port))))

(deftest test-devtest
  (spit ".lein-env" (prn-str {:env-name "devtest"}))
  (reload)
  (is (= 5 (env :max-workers)))
  (is (= "c:/temp" (env :temp-dir)))
  (is (= false (env :async?)))
  (is (= false (env :google-analytics?)))
  (is (= "xxxxx" (env :aws-key)))
  (is (= 8090 (env :port))))

(deftest test-prodheroku
  (spit ".lein-env" (prn-str {:env-name "prodheroku"}))
  (reload)
  (use 'configuron.core :reload)
  (is (= 5 (env :max-workers)))
  (is (= "/tmp" (env :temp-dir)))
  (is (= true (env :async?)))
  (is (= true (env :google-analytics?)))
  (is (= "yyyyy" (env :aws-key)))
  (is (= 8090 (env :port))))

(deftest test-override
  (spit ".lein-env" (prn-str {:env-name "devweb"}))
  (reload)
  (binding [configuron.core/env (configuron.core/env-override {:max-workers 1})]
    (is (= 1 (env :max-workers)))))
