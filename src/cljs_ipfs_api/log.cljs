(ns cljs-ipfs-api.log
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
  [[log.ls [[callback]]]
   [log.tail [[callback]]]
   [log.level [subsystem level [options callback]]]])
