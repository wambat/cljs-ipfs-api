(ns cljs-ipfs-api.stats
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
  [[stats.bitswap [[callback]]]
   [stats.bw [[options] [callback]]]
   [stats.repo [[options] [callback]]]])
