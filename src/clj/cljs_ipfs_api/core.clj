(ns cljs-ipfs-api.core
  (:require [clojure.string :as string]))

(def signatures
  [['add
    'files.add
    ['data ['options] ['callback]]]])

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

(defmacro defsignature [[f-name r-name f-params]]
  (let [nil-patched-param-defs (nil-patched-defns f-name f-params)
        api-call (string/split (str r-name) #"\.")
        api-root (if (> (count api-call) 1)
                   `(aget ~'ipfs-inst ~(first api-call))
                   `~'ipfs-inst)]
    `(defn ~(symbol (name f-name))
       ~@nil-patched-param-defs
       ([~'ipfs-inst ~@(to-no-ns-sym (flatten f-params))]
        (. ~api-root (~(symbol (last api-call)) ~@(to-no-ns-sym (flatten f-params))))))))

(comment

  (macroexpand `(defsignature [add files.add [data [options] [callback]]]))

  )
