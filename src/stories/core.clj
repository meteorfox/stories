(ns stories.core
  (:require [speclj.core :refer :all]))

(defmacro deffeature [title & things]
  `(describe ~title
     ~@things))

(defmacro defbackground [& things]
  `(before
     ~@things))

(defmacro defstory [topic Given When Then]
  `(describe ~topic
     ~@(for [g (partition 2 (rest Given))]
         `(it (str "Given " ~@(interpose " " (map str g)))
            (should true)))
     ~@(for [w (rest When)]
         `(it (str "When " ~@(str w))
            (should true)))
     ~@(for [t (rest Then)]
         `(it (str "Then " ~(str t))
            (let [~@(rest Given)]
              ~@(rest When)
              (should ~t))))))
