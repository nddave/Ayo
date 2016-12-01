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

This will output the detials that are gained by evaluating "john" and "maria", through their likes, dislikes, tastes, etc. The code, for base case and ease of operating, uses 10 inbuild characters who have been assigned particular values of characteristics. You can checkout the list of characters in this program in the file [prefcore.clj](https://github.com/nddave). You can also edit the list of characters and their characteristics to get your desired output. You may also pull data of people using online libraries that allow to easily connect to social media. These 10 inbuilt characters can be found in the 'people' vector in the prefcore.clj file.

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
