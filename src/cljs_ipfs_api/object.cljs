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
  [[object.new [[template] [callback]]]
   [object.put [obj [options] [callback]]]
   [object.get [multihash [options] [callback]]]
   [object.data [multihash [options] [callback]]]
   [object.links [multihash [options] [callback]]]
   [object.stat [multihash [options] [callback]]]
   [object.patch.addLink [multihash DAGLink [options callback]] patch-add-link]
   [object.patch.rmLink [multihash DAGLink [options callback]] patch-rm-link]
   [object.patch.appendData [multihash data [options callback]] patch-append-data]
   [object.patch.setData [multihash data [options callback]] patch-set-data]])
