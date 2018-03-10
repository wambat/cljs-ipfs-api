(ns cljs-ipfs-api.config
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
  [[config.get [[key callback]]]
   [config.set [key value [callback]]]
   [config.replace [config [callback]]]])
