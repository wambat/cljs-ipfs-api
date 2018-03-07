(ns cljs-ipfs-api.core
  (:require [taoensso.timbre :as timbre :refer-macros [log
                                                       trace
                                                       debug
                                                       info
                                                       warn
                                                       error
                                                       fatal
                                                       report]]
            [ipfs-api :as IpfsAPI]))

(def *ipfs-instance* (atom nil))

(defn init-ipfs [param]
  (let [i (new IpfsAPI param)]
    (reset! *ipfs-instance* i)
    i))

(defn init []
  (info "INIT"))
