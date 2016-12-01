;; This is my canvas. This is my art.

;; Assignment information
;; ---
;; Class : Artificial Intelligence
;; Assignment : Develop upon an existing AI algorithm you worked on
;; Student : Nirman Dave

;; Program information
;; ---
;; Name : Ayo 
;; File : Hangcore
;; File Purpose : The Hangcore file ontains the code that determines what activities could a couple do together.
;; Version : 1.0
;; Description : An artificially intelligent logic based algorithm that takes
;;					inputs about people to logically deduce their tastes, personality,
;;					likes, dislikes and eventually their significant other.
;; Language : Clojure

;; Defining the namespace and delaring the use of other files
(ns ayo.hangcore
	(:refer-clojure :exclude [==])
	(:use clojure.core.logic clojure.core.logic.pldb)
  	(:use [clojure.tools.macro :as macro])
	[:use ayo.scorecore]
	[:use ayo.prefcore])

;; Song-all outputs the common songs likes by the couple
(defn song-all[x y z] ;; x is the boy, y is the girl and z is an open variable
	(all
		(pref x z)
		(pref y z)
		(song z)))

;; Song-match uses the databases and outputs the results of
;; commonly liked songs between the couple.
(defn song-match[x y]
	(with-dbs [people prefs choices]
		(run* [q]
			(song-all x y q))))

;; Similarly for travel choices
(defn travel-all[x y z]
	(all
		(pref x z)
		(pref y z)
		(travel z)))

(defn travel-match[x y]
	(with-dbs [people prefs choices]
		(run* [q]
			(travel-all x y q))))

;; Similarly for food choices
(defn food-all[x y z]
	(all
		(pref x z)
		(pref y z)
		(food z)))

(defn food-match[x y]
	(with-dbs [people prefs choices]
		(run* [q]
			(food-all x y q))))

;; What sets this apart from interests is that Hangcore only discusses the "activities"
;; a couple can do. While the Mutualcore talks about everything a couple has in common.

;; All the hangout activites a couple could do togather
(defn hangout[x y] ;; x is the name of the boy, y is the name of the girl
	(with-dbs [people prefs choices]
		(run* [q]
			(conde ;; unlike (all), (conde) in core.logic is used to represent "OR"; for example
				((song-all x y q)) ;; the couple can go to a concert togather OR
				((travel-all x y q)) ;; they can go travel to a place OR
				((food-all x y q)))))) ;; they can go out try new food

;; The hangout-score takes a head count of all the possible hanging out activites
;; and inputs them into the :hangout-score key for the @match-map atom.
(defn hangout-score[x y]
	(let [z (/ (count (hangout x y)) 4)]
	(swap! match-map assoc :hangout-score (float z))))
