(ns stories.core
  (:require [speclj.core :refer :all]))

(defmacro defsuite [title & things]
  `(describe ~title
     ~@things))

(defmacro defbackground [& things]
  `(before
     ~@things))

(defmacro defstory [title Given When Then]
  `(describe ~title
     (let [~@(rest Given)]
       ~@(rest When)
       ~@(for [then (rest Then)]
           `(it ~(str then)
              (should ~then))))))
