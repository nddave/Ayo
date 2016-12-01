;; This is my canvas. This is my art.

;; Assignment information
;; ---
;; Class : Artificial Intelligence
;; Assignment : Develop upon an existing AI algorithm you worked on
;; Student : Nirman Dave

;; Program information
;; ---
;; Name : Ayo 
;; File : Core
;; File Purpose : The core file provides a space for user interaction.
;; Version : 1.0
;; Description : An artificially intelligent logic based algorithm that takes
;;					inputs about people to logically deduce their tastes, personality,
;;					likes, dislikes and eventually their significant other.
;; Language : Clojure

;; Defining the namespace and delaring the use of other files
(ns ayo.core
	(:refer-clojure :exclude [==]) 
	(:use clojure.core.logic clojure.core.logic.pldb)
  	(:use [clojure.tools.macro :as macro])
  	[:use ayo.scorecore]
	[:use ayo.prefcore]
	[:use ayo.mutualcore]
	[:use ayo.hangcore]
	[:use ayo.personalitycore]) ;; Using core.logic and other files associated with the program

;; Main function "match" takes all different test results and displays then togather
(defn match[x y] ;; Here x and y is taken as male and female; so, x representing a boy's name and y representing a girl's
	(interests-score x y) ;; Updates the :interest-score key in the @match-map atom on scorecore.clj
	(mutual-score x) ;; Updates the :mutual-score key in the @match-map atom on scorecore.clj
	(hangout-score x y) ;; Updates the :hangout-score key in the @match-map atom on scorecore.clj
	(personality-score x y) ;; Updates the :personality-score key in the @match-map atom on scorecore.clj
	(println "\n======")
	(println (str "Checking match for: " x " and " y "."))
	(println "======\n")

	;; Printing results for personality test
	(println "== PERSONALITIES ==")
	(cond
		(= (same-type? x y) true)
		;; Defining if and else for varying sentence output
		(do (println (str "According to what I know, I believe that " x " and " y " have the same personalities, that is " (boy-type x) "."))
			(println (str "This means that the two can make great partners. And this increases their chances by 70%!\n")))
		:else
		(do (println (str "According to what I know, I believe that " x " and " y " have very different personalities."))
			(println (str "While " x " is " (boy-type x) ", " y " is " (girl-type y)". This difference lowers their chances by 10%.\n")))		
		)

	;; Printing results for mutual feelings test
	(println "== MUTUAL FEELINGS ==")
	(cond
		(= (mutual? x) true)
		(do (println (str "I found that " x " likes " (first (doall (boy-likes x))) " and " y " likes " (first (doall (girl-likes y))) "."))
			(println (str "Therefore, " x " and " y " have mutual feelings for each other. This increases their chances of having a good relationship by 50%.\n")))

		:else
		(do (println (str "I found that " x " likes " (first (doall (boy-likes x))) " but " y " likes " (first (doall (girl-likes y))) "."))
			(println (str "Therefore, " x " and " y " have no mutual feelings for each other. This lessens their chances by 50%.\n"))))

	;; Printing results for similartiy test
	(println "== SIMILAR INTERESTS ==")
	(cond
		(= (count (interests x y)) 0)
		(do (println (str "I found that " x " and " y " have no interests in common."))
			(println (str "This changes their chances of liking each other by 0%\n")))

		:else
		(do (println (str "I found that " x " and " y " have a few interests in common."))
			(println (str "This increases their chances of liking each other by " (* (/ (count (interests x y)) 4) 100) "%\n"))))

	;; Printing results for hangout activities test
	(println "== HANGOUT ACTIVITIES ==")
	(cond
		(= (count (hangout x y)) 0)
		(do (println (str "There are no activities whatsoever that " x " and " y " would like to do togather."))
			(println (str "This changes their chances of liking each other by 0%\n")))

		:else
		(do (println (str "I found that " x " and " y " can do some activites togather."))
			(println (str "Type of concerts they can go to: " (first (doall (song-match x y)))))
			(println (str "Places they can travel to: " (first (doall (travel-match x y)))))
			(println (str "Food they can enjoy togather: " (first (doall (food-match x y)))))
			(println (str "All of this, increases their chances of liking each other by " (* (/ (count (hangout x y)) 4) 100) "%\n"))))

	;; Printing out the final overall match result
	(println "== OVERALL MATCH ==")
	(println (str "The overall match percent for this couple is : " (overall-score) "%\n")))

