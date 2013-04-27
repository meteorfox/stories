(ns stories.core
  (:require [speclj.core :refer :all])
  )

(defmacro defsuite [title & things]
  )

(defmacro defbackground [& things]
  )

(defmacro defstory [title as given upon then]
  ;; (prn (concat (rest given)))
  ;; `(describe ~title
  ;;    (it "TEST"
  ;;      (let [~@(concat (rest given))]
  ;;        (should= 3 4)))
  ;;    )
  )
