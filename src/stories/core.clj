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
     ~@(for [then (rest Then)]
         `(it ~(str then)
            (let [~@(rest Given)]
              ~@(rest When)
              (should ~then))))))
