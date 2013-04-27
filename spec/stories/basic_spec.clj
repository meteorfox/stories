(ns stories.basic-spec
  (:require [stories.core :refer :all]
            [fake-website.stories :refer :all]))

(defsuite "carts"

  (defbackground
    (setup-database)
    (setup-product {:title "product #1" :price 200})
    (setup-product {:title "product #2" :price 300}))

  (defstory "just visiting"
    (Given larry (setup-visitor))
    (When (add-to-cart larry "product #1"))
    (Then (= [] (products-in-cart larry))
          (= :sign-in (redirected-to larry))))

  (defstory "adding to your cart"
    (Given larry (setup-customer))
    (When (add-to-cart larry "product #1"))
    (Then (cart-has? larry "product #1")
          (not (cart-has? larry "product #2"))))

  (defstory "removing from your cart"
    (Given larry (setup-customer {:cart [(get-product "product #1")
                                         (get-product "product #2")]}))
    (When (remove-from-cart larry "product #1"))
    (Then (cart-has? larry "product #2")
          (not (cart-has? larry "product #1"))))

  (defstory "per-person carts"
    (Given larry (setup-customer)
           junior (setup-customer))
    (When (add-to-cart larry  "product #1")
          (add-to-cart junior "product #2"))
    (Then (cart-has? larry  "product #1")
          (cart-has? junior "product #2")
          (not (cart-has? larry  "product #2"))
          (not (cart-has? junior "product #1"))))

  (defstory "creating products"
    (Given bob (setup-admin))
    (When (create-product bob {:title "product #3"}))
    (Then (= (map :title (available-products))
             #{"product #1", "product #2", "product #3"})))

  )

(defsuite "refunds"

  (defbackground
    (setup-database)
    (setup-product {:title "product #1" :price 200})
    (setup-product {:title "product #2" :price 300}))

  (defstory "admins can refund payments"
    (Given customer (setup-customer {:products ["product #1" "product #2"]
                                     :payments [(setup-paypal-payment "abc" 500)]})
           admin (setup-admin))
    (When (refund-payment admin customer "abc"))
    (Then (= (owned-products customer)
             [])))

  )
