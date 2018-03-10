(ns cljs-ipfs-api.key
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
  [[key.gen [name [options] [callback]]]
   [key.list [[options] [callback]]]
   [key.rm [name [callback]]]
   [key.rename [oldName newName [callback]]]
   [key.export [name password [callback]]]
   [key.import [name pem password [callback]]]])
