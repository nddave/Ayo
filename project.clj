;; This is my canvas. This is my art.

;; Assignment information
;; ---
;; Class : Artificial Intelligence
;; Assignment : Develop upon an existing AI algorithm you worked on
;; Student : Nirman Dave

;; Program information
;; ---
;; Name : Ayo 
;; File : Project
;; File Purpose : The Project file defines the dependencies and other information.
;; Version : 1.0
;; Description : An artificially intelligent logic based algorithm that takes
;;          inputs about people to logically deduce their tastes, personality,
;;          likes, dislikes and eventually their significant other.
;; Language : Clojure

(defproject ayo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
  				[org.clojure/core.logic "0.8.10"]
  				[org.clojure/tools.macro "0.1.2"]]
  :main ^:skip-aot ayo.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
