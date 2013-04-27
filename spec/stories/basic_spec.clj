(ns stories.basic-spec
  (:require [stories.core :refer :all]))


;; step definitions are the backbone. their trustworthiness
;; correlates to how clear/simple/straight-forward they are.


(defsuite "carts"

  (defbackground
    (create-product {:title "product #1"})
    (create-product {:title "product #2"}))

  (defstory "just visiting"
    (as :visitor)
    (given (not-logged-in))
    )

  (defstory "adding to your cart"
    (as :customer)
    (given (logged-in))
    (upon (add-to-cart "product #1"))
    (then (cart-has? "product #1")
          (not (cart-has? "product #2"))))

  (defstory "removing from your cart"
    (as :customer)
    (given (logged-in)
           (has-in-cart ["product #1" "product #2"]))
    (upon (remove-from-cart "product #1"))
    (then (cart-has? "product #2")
          (not (cart-has? "product #1"))))

  (defstory "creating products"
    (as :admin)
    (given (logged-in))
    (upon (view-operations))
    (then (can-see-reports?)
          (can-see-reports?)))

  )
