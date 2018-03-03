(ns cljs-ipfs-api.core-test
  (:require-macros [cljs.test :refer [deftest testing is async]])
  (:require [cljs.test :as t]
            [cljs-ipfs-api.core :as core]))

#_(deftest test-test []
  (async done
         (is (= (core/mtest done) "Test"))))
