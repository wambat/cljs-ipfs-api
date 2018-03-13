(ns cljs-ipfs-api.dag
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
  [[dag.put [dagNode options callback]]
   [dag.get [cid [path] [options] callback]]
   [dag.tree [cid [path] [options] callback]]])
