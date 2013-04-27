(ns stories.core
  (:require [speclj.core :refer :all])
  )

(defmacro defstory [title as given upon then]
  ;; (prn (concat (rest given)))
  ;; `(describe ~title
  ;;    (it "TEST"
  ;;      (let [~@(concat (rest given))]
  ;;        (should= 3 4)))
  ;;    )
  )
