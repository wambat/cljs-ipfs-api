(ns cljs-ipfs-api.files-test
  (:require-macros [cljs.test :refer [deftest testing is async]])
  (:require [cljs.test :as t]
            [taoensso.timbre :as timbre :refer-macros [log
                                                       trace
                                                       debug
                                                       info
                                                       warn
                                                       error
                                                       fatal
                                                       report]]
            [cljs-ipfs-api.core :as core]
            [cljs-ipfs-api.files :as files]))

(deftest add-test []
  (async done
         (let [fs (js/require "fs")
               ipfs (core/->ipfs)
               file (.createReadStream fs "/home/wambat/work/district0x/cljs-ipfs/test/resources/testfile.jpg")]
           (is (= (files/add ipfs file nil (fn [err files]
                                             (info ["DONE" err files])
                                             (done))))))))
