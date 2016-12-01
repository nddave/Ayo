;; This is my canvas. This is my art.

;; Assignment information
;; ---
;; Class : Artificial Intelligence
;; Assignment : Develop upon an existing AI algorithm you worked on
;; Student : Nirman Dave

;; Program information
;; ---
;; Name : Ayo 
;; File : Prefcore
;; File Purpose : The Prefcore file is a database of people, choices, likes and their preferences.
;; Version : 1.0
;; Description : An artificially intelligent logic based algorithm that takes
;;					inputs about people to logically deduce their tastes, personality,
;;					likes, dislikes and eventually their significant other.
;; Language : Clojure

;; Defining the namespace and delaring the use of other files
(ns ayo.prefcore
	(:refer-clojure :exclude [==])
	(:use clojure.core.logic clojure.core.logic.pldb)
	(:use [clojure.tools.macro :as macro]))

;; Defining "song" as an item that exists in the word
(db-rel song x)
;; Similarly defining "travel"
(db-rel travel x)
;; And so on
(db-rel food x) ;; The x is the number of parameters that goven this viarable item
(db-rel crush x)

;; The choices database is a database of options available to the participants to chose from
(def choices
	(db [song "electronic"] ;; Song options
		[song "screaming"]
		[song "bright and happy"]
		[song "rap"]
		[song "pop"]

		[travel "chile"] ;; Travel options
		[travel "japan"]
		[travel "india"]
		[travel "ireland"]
		[travel "spain"]

		[food "salad"] ;; Food options
		[food "panini"]
		[food "burrito"]
		[food "burger"]
		[food "naan"]

		[crush "cute"] ;; Types of people one could be attracted to
		[crush "smart"]
		[crush "cool"]
		[crush "popular"]
		[crush "flirty"]))

;; Defining male and female humans
(db-rel boy x)
(db-rel girl x)

(def people
	(db [boy "john"] ;; Names of humans who are male
		[boy "charlie"]
		[boy "max"]
		[boy "armin"]
		[boy "gavin"]

		[girl "maria"] ;; Names of humans who are female
		[girl "sarah"]
		[girl "adva"]
		[girl "carly"]
		[girl "kaya"]))

;; Defining preferences item
(db-rel pref x y) ;; x is the name of the person, and y is their preference

(def prefs
	(db [pref "john" "electronic"] ;; So, John prefers electronic music
		[pref "john" "ireland"] ;; And wishes to visit Ireland sometime
		[pref "john" "burger"] ;; Also likes burgers, when it comes to food
		[pref "john" "cool"] ;; And is usually attracted to people who are "cool"

		[pref "charlie" "rap"] ;; Similarly, this is the profile for Charlie
		[pref "charlie" "spain"]
		[pref "charlie" "salad"]
		[pref "charlie" "cute"]

		[pref "max" "pop"] ;; And so on ...
		[pref "max" "japan"]
		[pref "max" "naan"]
		[pref "max" "flirty"]

		[pref "armin" "bright and happy"]
		[pref "armin" "chile"]
		[pref "armin" "burrito"]
		[pref "armin" "popular"]

		[pref "gavin" "screaming"]
		[pref "gavin" "india"]
		[pref "gavin" "panini"]
		[pref "gavin" "smart"]

		[pref "maria" "electronic"]
		[pref "maria" "india"]
		[pref "maria" "panini"]
		[pref "maria" "cool"]

		[pref "sarah" "screaming"]
		[pref "sarah" "japan"]
		[pref "sarah" "naan"]
		[pref "sarah" "cute"]

		[pref "adva" "bright and happy"]
		[pref "adva" "spain"]
		[pref "adva" "burrito"]
		[pref "adva" "smart"]

		[pref "carly" "rap"]
		[pref "carly" "chile"]
		[pref "carly" "burger"]
		[pref "carly" "flirty"]

		[pref "kaya" "pop"]
		[pref "kaya" "ireland"]
		[pref "kaya" "salad"]
		[pref "kaya" "popular"]))

;; Likes defines who like who
(db-rel likes x y) ;; x and y are the names of people who like each other

(def like
	(db [likes "john" "maria"] ;; John like Maria
		[likes "maria" "john"] ;; Maria also likes John

		[likes "charlie" "sarah"] ;; Charlie likes Sarah
		[likes "sarah" "gavin"] ;; But, Sarah likes Gavin

		[likes "max" "adva"]
		[likes "adva" "armin"]

		[likes "armin" "carly"]
		[likes "carly" "max"]

		[likes "gavin" "kaya"] ;; And Gavin likes kaya
		[likes "kaya" "charlie"])) ;; But Kaya likes Charlie 
