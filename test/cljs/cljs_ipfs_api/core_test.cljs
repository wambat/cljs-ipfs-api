(ns cljs-ipfs-api.core-test
  (:require-macros [cljs.test :refer [deftest testing is]])
  (:require [cljs.test :as t]
            [cljs-ipfs-api.core :as core]))

(deftest test-test []
  (is (= (core/mtest) "Test")))
