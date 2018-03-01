(ns cljs-ipfs-api.core
  (:require [taoensso.timbre :as timbre :refer-macros [log
                                                       trace
                                                       debug
                                                       info
                                                       warn
                                                       error
                                                       fatal
                                                       report]]
            [ipfs-api :as IpfsAPI]
            ))

(defn mtest []
  "Test")

(defn init []
  (info "INIT"))
