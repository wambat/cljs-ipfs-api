(set-env!
 :source-paths    #{"src/cljs"}
 ;;:resource-paths  #{"resources"}
 ;; :npm-deps {}
 :dependencies '[[org.clojure/clojure "1.9.0-alpha17"]

                 ;;ENV
                 [com.taoensso/timbre "4.10.0"] 
                 ;; [mount "0.1.11"]
                 ;; [org.clojure/core.async "0.3.443"]

                 ;;DATA
                 ;; [com.taoensso/encore "2.91.0"]
                 ;; [org.clojure/core.cache "0.6.3"]
                 ;; [org.clojure/core.memoize "0.5.6" :exclusions [org.clojure/core.cache]]
                 ;;CLJS
                 [org.clojure/clojurescript "1.9.946"]
                 [cljs-node-io "0.5.0"]

                 ;;DEV
                 [doo "0.1.8" :scope "test"]
                 [samestep/boot-refresh "0.1.0" :scope "test"]
                 [adzerk/boot-cljs          "2.1.4"  :scope "test"];;:exclusions [org.clojure/clojurescript]
                 [adzerk/boot-cljs-repl     "0.3.3"      :scope "test"]
                 [adzerk/boot-reload        "0.5.2"      :scope "test"]
                 ;; [pandeiro/boot-http        "0.8.3"      :scope "test"]
                 [com.cemerick/piggieback   "0.2.1"      :scope "test"]
                 [org.clojure/tools.nrepl   "0.2.12"     :scope "test"]
                 [weasel                    "0.7.0"      :scope "test"]
                 [crisptrutski/boot-cljs-test "0.3.5-SNAPSHOT" :scope "test"]
                 ;; [org.martinklepsch/boot-garden "1.3.2-0" :scope "test"]
                 [binaryage/dirac "1.1.3" :scope "test"]
                 [powerlaces/boot-cljs-devtools "0.2.0" :scope "test"]
                 [binaryage/devtools "0.9.4"]
                 [degree9/boot-npm "1.9.0" :scope "test"]
                 ])

(require
 '[samestep.boot-refresh :refer [refresh]]
 '[adzerk.boot-cljs      :refer [cljs]]
 '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
 '[adzerk.boot-reload    :refer [reload]]
 ;; '[pandeiro.boot-http    :refer [serve]]
 '[crisptrutski.boot-cljs-test :refer [test-cljs]]
 ;; '[org.martinklepsch.boot-garden :refer [garden]]
 '[powerlaces.boot-cljs-devtools :refer [cljs-devtools dirac]]
 '[degree9.boot-npm :as npm]
 )

(deftask npm-deps
  "Install npm deps to node_modules."
  []
  (npm/npm :install ["ipfs-api@latest"]
           :cache-key ::cache))

(deftask cljs-env []
  (task-options! cljs {:compiler-options {:target :nodejs
                                          :install-deps true
                                          :npm-deps {:ipfs-api "18.1.1"}}})
  identity)

(deftask build []
  (comp (speak)
     ;; (npm-deps)
     (cljs-env)
     (cljs)))

(deftask run []
  (comp
   (watch)
   (refresh)
   (cljs-repl)
   ;; (reload)
   ;; (build)
   ))


(deftask production []
  (task-options! cljs {:optimizations :advanced}
                 ;; garden {:pretty-print false}
                 )
  identity)

(deftask development []
  (task-options! cljs {:optimizations :none}
                 reload {;;:on-jsload 'cljs-ipfs.core/init
                         :asset-path "public"})
  identity)

(deftask dev
  "Simple alias to run application in development mode"
  []
  (comp (development)
     (run)))


(deftask testing []
  (set-env! :source-paths #(conj % "test/cljs"))
  identity)

;;; This prevents a name collision WARNING between the test task and
;;; clojure.core/test, a function that nobody really uses or cares
;;; about.
(ns-unmap 'boot.user 'test)

(deftask test []
  (comp (testing)
     (cljs-env)
     (test-cljs :js-env :node;;:phantom
                :exit?  true)))

(deftask auto-test []
  (comp (testing)
     (watch)
     (cljs-env)
     (test-cljs :js-env :node;;:phantom
                )))
