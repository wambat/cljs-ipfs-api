(ns cljs-ipfs-api.name
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
  [[name.publish [addr [options callback]]]
   [name.resolve [addr [options callback]]]])
