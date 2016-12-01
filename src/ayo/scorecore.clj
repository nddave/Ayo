;; This is my canvas. This is my art.

;; Assignment information
;; ---
;; Class : Artificial Intelligence
;; Assignment : Develop upon an existing AI algorithm you worked on
;; Student : Nirman Dave

;; Program information
;; ---
;; Name : Ayo 
;; File : Scorecore
;; File Purpose : The Scorecore description of an atom that governs the score and brings about
;;					gamificaion in personality mapping.
;; Version : 1.0
;; Description : An artificially intelligent logic based algorithm that takes
;;					inputs about people to logically deduce their tastes, personality,
;;					likes, dislikes and eventually their significant other.
;; Language : Clojure

;; Defining the namespace and delaring the use of other files
(ns ayo.scorecore
	(:refer-clojure :exclude [==])
	(:use clojure.core.logic clojure.core.logic.pldb)
	(:use [clojure.tools.macro :as macro]))

;; This atom is a map, since, clojure maps are the best.
(def match-map (atom (hash-map 
						:interests-score 0, ;; Score of interest, initially 0
						:mutual-score 0, ;; Score of mutual liking, initially 0
						:personality-score 0, ;; Score of personality match, initially 0
						:hangout-score 0))) ;; Count of hangout activities, initially 0

;; The overall-score takes the hashmap from the atom
;; and converts the scores into a finer probability
;; using simple probabilites formula: ((1 - p) * 100)
;; where p is the combined probabilities of a succesfull relationship 
;; that has arrived from all the tests; p = (p1 * p2 * p3 * p4)
(defn overall-score[]
	(let [is (get @match-map :interests-score)]
		(let [ms (get @match-map :mutual-score)]
			(let [ps (get @match-map :personality-score)]
				(let [hs (get @match-map :hangout-score)]
					(* (* is ms ps hs) 100))))))
