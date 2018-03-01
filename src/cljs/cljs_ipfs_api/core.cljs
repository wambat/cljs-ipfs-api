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
  (new IpfsAPI "/ip4/127.0.0.1/tcp/5001"))

(defn init []
  (info "INIT"))
