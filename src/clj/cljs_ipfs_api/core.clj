(ns cljs-ipfs-api.core
  (:require [clojure.string :as string]
            [camel-snake-kebab.core :refer :all]))

(defn to-no-ns-sym [s]
  (map (comp symbol name) s))

(defn nil-patched-defns [f-name params]
  (let [patched-args (reduce #(concat %1 [(concat (last %1) %2)]) [] (filter vector? params))
        [oargs-before oargs-after] (map #(remove vector? %) (split-with (complement vector?) params))]
    (map (fn [pp]
           `([~'ipfs-inst ~@(to-no-ns-sym (concat oargs-before (rest pp) oargs-after))]
             (~(symbol (name f-name)) ~'ipfs-inst ~@(concat (to-no-ns-sym oargs-before)
                                            [nil]
                                            (to-no-ns-sym (rest pp))
                                            (to-no-ns-sym oargs-after)))))
         patched-args)))

(defn defsignature [[r-name f-params f-name ]]
  (let [api-call (string/split (str r-name) #"\.")
        f-name (if-not f-name
                 (->kebab-case (last api-call))
                 f-name)
        nil-patched-param-defs (nil-patched-defns f-name f-params)
        api-root (if (> (count api-call) 1)
                   `(aget ~'ipfs-inst ~(first api-call))
                   `~'ipfs-inst)]
    `(defn ~(symbol (name f-name))
       ~@nil-patched-param-defs
       ([~'ipfs-inst ~@(to-no-ns-sym (flatten f-params))]
        (. ~api-root (~(symbol (last api-call)) ~@(to-no-ns-sym (flatten f-params))))))))

(defmacro defsignatures [sigs]
  (let [defs (map defsignature sigs)]
    `(do
       ~@defs)))

(comment

  (defsignature '[add files.add [data [options] [callback]]])

  (macroexpand '(defsignatures [[files.add [data [options] [callback]]]
                                [files.addReadableStream [data [options] [callback]]]]))

  )
