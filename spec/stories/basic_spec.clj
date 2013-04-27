(ns stories.basic-spec
  (:require [stories.core :refer :all]
            [fake-website.stories :refer :all]))

(defsuite "carts"

  (defbackground
    (create-product {:title "product #1"})
    (create-product {:title "product #2"}))

  (defstory "just visiting"
    (As visitor)
    (When (add-to-cart "product #1"))
    (Then (= [] cart-contents)
          (= :sign-in (redirected-to))))

  (defstory "adding to your cart"
    (As customer)
    (When (add-to-cart "product #1"))
    (Then (cart-has? "product #1")
          (not (cart-has? "product #2"))))

  (defstory "removing from your cart"
    (As customer)
    (Given cart ["product #1" "product #2"])
    (When (remove-from-cart "product #1"))
    (Then (cart-has? "product #2")
          (not (cart-has? "product #1"))))

  (defstory "creating products"
    (As admin)
    (When (create-product {:title "product #3"}))
    (Then (= (map :title available-products)
             #{"product #1", "product #2", "product #3"})))

  )

(defsuite "refunds"

  (defbackground
    (create-product {:title "product #1"})
    (create-order {:title "product #2"})
    )

  (defstory "admins can refund payments"
    (Given payment-1 (setup-paypal-payment 200)
           order-1 (setup-order {:products ["product #1" "product #2"]
                                 :payments [payment-1]})
           customer (setup-customer {:orders [order-1]})
           admin (setup-admin))
    (When (refund-payment customer "product #1"))
    (Then (has-product? customer "product #2")
          (not (has-product "product #1"))))

  )
