(ns cljs-ipfs-api.bootstrap
  (:require [taoensso.timbre :as timbre :refer-macros [log
                                                       trace
                                                       debug
                                                       info
                                                       warn
                                                       error
                                                       fatal
                                                       report]]
            [cljs-ipfs-api.core :refer-macros [defsignatures]]
            [cljs-ipfs-api.utils :refer [wrap-callback]]))

(defsignatures
  [[bootstrap.list]
   [bootstrap.add]
   [bootstrap.rm]])
