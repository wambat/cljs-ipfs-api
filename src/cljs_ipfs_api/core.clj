(ns cljs-ipfs-api.core
  (:require [clojure.string :as string]
            [camel-snake-kebab.core :refer :all]))

(defn to-no-ns-sym [s]
  (map (comp symbol name) s))

(defn nil-patched-defns [f-name params]
  (let [patched-args (reduce #(concat %1 [(concat (last %1) %2)]) [] (filter vector? params))
        [oargs-before oargs-after] (map #(remove vector? %) (split-with (complement vector?) params))]
    (map (fn [pp]
           `([~@(to-no-ns-sym (concat oargs-before (rest pp) oargs-after))]
             (~(symbol (name f-name)) ~@(concat (to-no-ns-sym oargs-before)
                                                [nil]
                                                (to-no-ns-sym (rest pp))
                                                (to-no-ns-sym oargs-after)))))
         patched-args)))

(defn jsify-args [args]
  (map (fn [arg]
         (if (= arg 'callback)
           arg
           `(~'cljkk->js ~arg))) args))

(defn defsignature [[r-name f-params f-name ]]
  (let [api-call (string/split (str r-name) #"\.")
        f-name (if-not f-name
                 (->kebab-case (last api-call))
                 f-name)
        nil-patched-param-defs (nil-patched-defns f-name f-params)
        api-root (if (> (count api-call) 1)
                   `(aget ~'ipfs-inst ~@(butlast api-call))
                   `~'ipfs-inst)
        call `(let [~'args (remove nil? [~@(to-no-ns-sym (flatten f-params))])]
                (js-apply ~api-root ~'args))
        ]
    `(defn ~(symbol (name f-name))
       ~@nil-patched-param-defs
       ([~@(to-no-ns-sym (flatten f-params))]
        (~(symbol (name f-name))
         ~'@cljs-ipfs-api.core/*ipfs-instance*
         ~@(to-no-ns-sym (flatten f-params))))
       ([~'ipfs-inst ~@(to-no-ns-sym (flatten f-params))]
        ~(if (contains? (into #{}
                                (flatten f-params))
                          'callback)
           `(let [~'callback (~'wrap-callback ~'callback)]
              ~call)
           call)))))

(defmacro defsignatures [sigs]
  (let [defs (map defsignature sigs)]
    `(do
       ~@defs)))

(comment

  (defsignature '[add files.add [data [options] [callback]]])

  (macroexpand '(defsignatures [[files.add [data [options] [callback]]]
                                [files.addReadableStream [data [options] [callback]]]]))

  (macroexpand '(defsignatures [[object.patch.addLink [multihash DAGLink [options] [callback]]]]))
  (do
    (clojure.core/defn add-link
      ([multihash DAGLink]
       (add-link multihash
                 DAGLink
                 nil))
      ([multihash DAGLink callback]
       (add-link multihash
                 DAGLink
                 nil
                 callback))
      ([multihash DAGLink options callback]
       (add-link (clojure.core/deref cljs-ipfs-api.core/*ipfs-instance*)
                 multihash DAGLink options callback))
      ([ipfs-inst multihash DAGLink options callback]
       (clojure.core/let [callback (wrap-callback callback)]
         (. (clojure.core/aget ipfs-inst "object" "patch")
            (addLink (cljkk->js multihash)
                     (cljkk->js DAGLink)
                     (cljkk->js options)
                     callback))))))

  )
