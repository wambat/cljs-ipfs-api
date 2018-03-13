(ns cljs-ipfs-api.pubsub
  (:require [taoensso.timbre :as timbre :refer-macros [log
                                                       trace
                                                       debug
                                                       info
                                                       warn
                                                       error
                                                       fatal
                                                       report]]
            [cljs.core.async :refer [>! chan]]
            [cljs-ipfs-api.core :refer-macros [defsignatures]])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(defsignatures
  [[pubsub.subscribe [topic options handler callback]]
   [pubsub.unsubscribe [topic handler]]
   [pubsub.publish [topic data callback]]
   [pubsub.ls [topic callback]]
   [pubsub.peers [topic callback]]])
