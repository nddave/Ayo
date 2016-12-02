# Ayo

![alt tag](https://raw.githubusercontent.com/nddave/Ayo/master/Ayo!.png)

An artificially intelligent logic based algorithm that takes inputs about people to logically deduce their tastes, personality, likes, dislikes and eventually their significant other.

# Getting started

The Ayo is quick and easy to setup. Assuming you have the following downloaded, installed and running.

* Clojure
* Leiningen

After this, download the Ayo.zip file, and run it with REPL.

The quick function you may want to start with is the 'match' function, where match takes in two arguments. Each of the arguments are strings that represent names of two individuals who need to be paired. 

> ayo.core=> (match "john" "maria")

This will output the detials that are gained by evaluating "john" and "maria", through their likes, dislikes, tastes, etc. The code, for base case and ease of operating, uses 10 inbuild characters who have been assigned particular values of characteristics. You can checkout the list of characters in this program in the file [prefcore.clj](https://github.com/nddave/Ayo/blob/master/src/ayo/prefcore.clj).

```clojure
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
```

You can also edit the list of characters and their characteristics to get your desired output. You may also pull data of people using online libraries that allow to easily connect to social media.

The code sets basic rules for the AI to build upon. These rules are then inferred using logic programming to make further deductions.

```clojure
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
```

You can find all project code files [here](https://github.com/nddave/Ayo/tree/master/src/ayo).

# License information ![License: CC BY 4.0](https://img.shields.io/badge/License-CC%20BY%204.0-lightgrey.svg)

This work is licensed under a [Creative Commons Attribution 4.0 International License](https://creativecommons.org/licenses/by/4.0/). 

Program is created by [Nirman Dave](http://www.nirmandave.com) as a form of assignment for *Artificial Intelligence CS263* course at *Hampshire College, Amherst MA* under *Professor Lee Spector*.
