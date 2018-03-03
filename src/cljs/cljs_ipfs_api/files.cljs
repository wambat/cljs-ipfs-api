(ns cljs-ipfs-api.files
  (:require [taoensso.timbre :as timbre :refer-macros [log
                                                       trace
                                                       debug
                                                       info
                                                       warn
                                                       error
                                                       fatal
                                                       report]]
            [ipfs-api :as IpfsAPI]))

(defn add [ipfs data options callback]
  (.add (.-files ipfs)
        data
        options
        callback))
