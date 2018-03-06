(ns cljs-ipfs-api.core
  (:require [taoensso.timbre :as timbre :refer-macros [log
                                                       trace
                                                       debug
                                                       info
                                                       warn
                                                       error
                                                       fatal
                                                       report]]
            [ipfs-api :as IpfsAPI]
            ))

(def signatures
  [['add
    'ipfs.files.add
    ['data ['options] ['callback] 'mordata]]]
  ;; ipfs.files.add(data, [options], [callback]). Alias to ipfs.add.
  ;; ipfs.files.addReadableStream([options])
  ;; ipfs.files.addPullStream([options])
  ;; ipfs.files.cat(ipfsPath, [options], [callback]). Alias to ipfs.cat.
  ;; ipfs.files.catReadableStream(ipfsPath, [options])
  )

(defn nil-patched-defns [f-name params]
  (let [patched-args (reduce #(concat %1 [(concat (last %1) %2)]) [] (filter vector? params))
        [oargs-before oargs-after] (map #(remove vector? %) (split-with (complement vector?) params))]
    (map (fn [pp]
           `([~@(concat oargs-before (rest pp) oargs-after)]
             (~f-name ~@(concat oargs-before [nil] (rest pp) oargs-after))))
         patched-args)))

(defn defsignature [[f-name r-name f-params]]
  (let [nil-patched-param-defs (nil-patched-defns f-name f-params)]
    `(defn ~f-name
       ~@nil-patched-param-defs
       ([~@(flatten f-params)]
        ))))

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
