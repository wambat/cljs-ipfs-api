(ns cljs-ipfs-api.core
  (:require [taoensso.timbre :as timbre :refer-macros [log
                                                       trace
                                                       debug
                                                       info
                                                       warn
                                                       error
                                                       fatal
                                                       report]]
            [ipfs-api :as IpfsAPI]))

(defn ->ipfs []
  (new IpfsAPI "/ip4/127.0.0.1/tcp/5001"))

(defn mtest [on-ok]
  #_(let [ipfs (new IpfsAPI "/ip4/127.0.0.1/tcp/5001")
        fs (js/require "fs")
        ;; reader (new js/FileReader)
        ]
    ;; (.files )
    ;;[ipfs reader]
    (.add (.-files ipfs)
          ;; (clj->js {"path" "/tmp/testfile.jpg"
          ;;           "content" "test/resources/testfile.jpg"})
          (.createReadStream fs "/home/wambat/work/district0x/cljs-ipfs/test/resources/testfile.jpg")

          (fn [err files]
            (info ["done!"
                   err
                   files])
            (on-ok)))))

(defn init []
  (info "INIT"))
