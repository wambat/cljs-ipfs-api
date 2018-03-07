(ns cljs-ipfs-api.files
  (:require [taoensso.timbre :as timbre :refer-macros [log
                                                       trace
                                                       debug
                                                       info
                                                       warn
                                                       error
                                                       fatal
                                                       report]]
            [ipfs-api :as IpfsAPI]
            [cljs-ipfs-api.core :refer-macros [defsignatures]]))

(defsignatures
  [[add files.add [data [options] [callback]]]
   [addReadableStream files.addReadableStream [data [options] [callback]]]
   [addPullStream files.addPullStream [[options]]]
   [cat files.cat [ipfs-path [options] [callback]]]
   [catReadableStream files.catReadableStream (ipfsPath [options])]])
