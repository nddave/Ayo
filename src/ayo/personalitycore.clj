;; This is my canvas. This is my art.

;; Assignment information
;; ---
;; Class : Artificial Intelligence
;; Assignment : Develop upon an existing AI algorithm you worked on
;; Student : Nirman Dave

;; Program information
;; ---
;; Name : Ayo 
;; File : Personalitycore
;; File Purpose : The Personalitycore file ontains the code that determines individual people’s personality based on their choices. 
;; 					This code is largely governed by a set of logic rules; and most of the inferences here are made by the computer. 
;; Version : 1.0
;; Description : An artificially intelligent logic based algorithm that takes
;;					inputs about people to logically deduce their tastes, personality,
;;					likes, dislikes and eventually their significant other.
;; Language : Clojure

;; Defining the namespace and delaring the use of other files
(ns ayo.personalitycore
	(:refer-clojure :exclude [==])
	(:use clojure.core.logic clojure.core.logic.pldb)
  	(:use [clojure.tools.macro :as macro])
	[:use ayo.scorecore]
	[:use ayo.prefcore])

;; It is inferred that one's personality is largely determined by the music they like to listen
;; Based on this theory, the rules of personality are set as follows:
;;
;; 1. melancholic people like electronic music
;; 2. choleric people like screaming and rap music
;; 3. phlegmatic people like bright and happy music
;; 4. sanguime people like pop music
;;
;; The databse below defines boys' and girls' choices
;; so the the AI can best define which people have the same personalities.
(defn personality-rules[x]
	(macro/symbol-macrolet [_ (lvar)] ;; Using a macrolet allows a single instruction of "_" to automatically expand into "(lvar)"
		(all
			(membero [_ _ "melancholic" "electronic"] x) ;; There are 4 lvars; each represent a unique definition.
			(membero [_ _ "choleric" "screaming"] x) ;;	_boy-name_ _girl-name_ _personality_ _song_
			(membero [_ _ "phlegmatic" "bright and happy"] x) ;; membero is a feature of core.logic that allows to define one element as a member of a set
			(membero [_ _ "choleric" "rap"] x)
			(membero [_ _ "sanguine" "pop"] x)
			(membero ["john" _ _ "electronic"] x)
			(membero ["charlie" _ _ "rap"] x)
			(membero ["max" _ _ "pop"] x)
			(membero ["armin" _ _ "bright and happy"] x)
			(membero ["gavin" _ _ "screaming"] x)
			(membero [_ "maria" _ "electronic"] x)
			(membero [_ "carly" _ "rap"] x)
			(membero [_ "kaya" _ "pop"] x)
			(membero [_ "adva" _ "bright and happy"] x)
			(membero [_ "sarah" _ "screaming"] x))))

;; prsn-nodes is a representation of all possible combinations that could be made using
;; the rules defined above
(defn prsn-nodes[x] ;; The x allows use to moderate the iterations of combinations we want the computer to make
	(run x [q]
		(personality-rules q)))

;; This breaks down the result from prsn-node into different sets
(defn prsn-node[x]
	(nth (read-string (str (first (prsn-nodes 1)))) x))

;; The sets are examined element by element
;; Is the element mentioning a boy?
(defn boy-node[x]
	(nth (prsn-node x) 0))

;; Is the element mentioning a girl?
(defn girl-node[x]
	(nth (prsn-node x) 1))

;; What is the personality type the element is refereing to
(defn type-node[x]
	(nth (prsn-node x) 2))

;; You can run each of the functions above seperately to get a clear picture about the results
;; and how these results are processed.

;; Deducing the personality type of the boy
(defn boy-type[x] ;; x is the name of the boy in string
	(loop [i 0]
		(when (< i 6)
			(cond
				(= (str (boy-node i)) (str x))
				(type-node i)
				:else
				(recur (inc i))))))

;; Deducing the personality type of the girl
(defn girl-type[x] ;; x is the name of the girl in string
	(loop [i 0]
		(when (< i 6)
			(cond
				(= (str (girl-node i)) (str x))
				(type-node i)
				:else
				(recur (inc i))))))

;; A test to check if the boy and the girl's personality type is the same
(defn same-type?[x y]
	(true? (= (boy-type x) (girl-type y)))) ;; If the personalities are the same, the test results true

;; The personality-score converts a person's personality into a value
;; that can be added to the :personality-score key of the @match-map hashmap
(defn personality-score[x y]
	(cond
		(= (same-type? x y) true) ;; If the personality is the same, then
		(swap! match-map assoc :personality-score 0.7) ;; The chances of the couple having a better relation is raised by 70%
		:else ;; Else
		(swap! match-map assoc :personality-score 0))) ;; The chances remain unchanged



