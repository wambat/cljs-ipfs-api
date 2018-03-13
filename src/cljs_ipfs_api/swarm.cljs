(ns cljs-ipfs-api.swarm
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
  [[swarm.addrs [[callback]]]
   [swarm.connect [addr [callback]]]
   [swarm.disconnect [addr [callback]]]
   [swarm.peers [[opts] [ callback]]]])
