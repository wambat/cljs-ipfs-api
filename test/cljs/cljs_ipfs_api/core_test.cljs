(ns cljs-ipfs-api.core-test
  (:require-macros [cljs.test :refer [deftest testing is async]])
  (:require [cljs.test :as t]
            [cljs-ipfs-api.core :as core]))

(deftest defnils-test []
  (is (= (core/nil-patched-defns 'test ['data ['options] ['callback] ['quack] 'mordata]) nil)))

(deftest defsignature-test []
  (is (= (core/defsignature (first core/signatures)) '(defn add))))
