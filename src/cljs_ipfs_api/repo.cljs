(ns cljs-ipfs-api.repo
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
  [[repo.gc [[options] [callback]]]
   [repo.stat [[options] [callback]]]
   [repo.version [[callback]]]])
