;; This is my canvas. This is my art.

;; Assignment information
;; ---
;; Class : Artificial Intelligence
;; Assignment : Develop upon an existing AI algorithm you worked on
;; Student : Nirman Dave

;; Program information
;; ---
;; Name : Ayo 
;; File : Mutualcore
;; File Purpose : The Mutualcore file provides the code for interests and mutual liking tests.
;; Version : 1.0
;; Description : An artificially intelligent logic based algorithm that takes
;;					inputs about people to logically deduce their tastes, personality,
;;					likes, dislikes and eventually their significant other.
;; Language : Clojure

;; Defining the namespace and delaring the use of other files
(ns ayo.mutualcore
	(:refer-clojure :exclude [==])
	(:use clojure.core.logic clojure.core.logic.pldb)
  	(:use [clojure.tools.macro :as macro])
	[:use ayo.scorecore]
	[:use ayo.prefcore])

;; == Interests test ==
;;
;; The interests test simply looks at the intersection between the choices of the couple
;; Looks for overlaps in all of their tastes and choices
(defn interests[x y]
	(with-dbs [prefs people] ;; Using databases: prefs and people
		(run* [q] ;; Run* refers to all possible outcomes
			(all ;; All referes to satisfaction of both of the following conditions
				(pref x q)
				(pref y q)))))

;; Interests score converts the results into values that can be updates on the 
;; match-map hashmap
(defn interests-score[x y]
	(let [z (/ (count (interests x y)) 4)] ;; Creates value
		(swap! match-map assoc :interests-score (float z)))) ;; Updates to the match-map atom

;; == Mutual liking test ==
;;
;; The mutual liking test looks wheather both the people
;; being discusses like each other or not. If not, who else do they like
(defn boy-likes[x] ;; x is the boy's name, the function results with the name of every girl, the boy likes
	(with-dbs [like people]
		(run* [q] 
			(all 
				(likes x q)
				(boy x)))))

(defn girl-likes[x] ;; similarly for girl's name. Where x is the gir's name and the result is every boy's the girl likes
	(with-dbs [like people]
		(run* [q] 
			(all 
				(likes x q)
				(girl x)))))

;; The mutual? function asks if the liking is mutual
;; Meaning, is the result of the liking by the boy and the girl, mutual
(defn mutual?[x]
	(true? (= (first (girl-likes (first (boy-likes x)))) (str x)))) ;; the function returns true if the liking is mutual

;; The mutual-score coverts the liking into a value that can be added to the
;; :mutual-score key in @match-map atom.
(defn mutual-score[x]
	(cond
		(= (mutual? x) true) ;; If the liking is mutual then
		(swap! match-map assoc :mutual-score 0.5) ;; the score increases by 50%

		(= (mutual? x) false) ;; Else
		(swap! match-map assoc :mutual-score (- (get @match-map :mutual-score) 0.5)))) ;; the score decreases by 50%
		;; This means that there is more chance of a couple getting in a relationship if the liking is mutual.
