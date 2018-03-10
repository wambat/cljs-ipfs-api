(ns cljs-ipfs-api.block
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
  [[block.get [cid [options] [callback]]]
   [block.put [block cid [callback]]]
   [block.stat [cid [options]]]])
