(ns cljs-ipfs-api.stats
  (:require [taoensso.timbre :as timbre :refer-macros [log
                                                       trace
                                                       debug
                                                       info
                                                       warn
                                                       error
                                                       fatal
                                                       report]]
            [cljs-ipfs-api.core :refer-macros [defsignatures]]))

(defsignatures
  [[stats.bitswap [[callback]]]
   [stats.bw [[options] [callback]]]
   [stats.repo [[options] [callback]]]])
