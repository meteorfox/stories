(ns fake-website.stories
  (:require [hyperion.api :refer :all]
            [hyperion.memory :refer [new-memory-datastore]]))

;; we should be able to read these step definitions and immediately so
;; "oh yeah i understand this perfectly" and have full assurrance and
;; confidence in the test suite.

;; step definitions have to be THIN. not doing too much translation
;; between the DSL and the real code. otherwise it becomes useless.


;; test setup

(defn setup-database []
  (set-ds! (new-memory-datastore)))

(defn setup-product [mapping]
  (save (merge {:kind :product} mapping)))

(defn setup-visitor []
  (save {:kind :user :cart []}))

(defn get-product [title]
  (find-by-kind :product
                :filters [[:= :title title]]))

(defn setup-customer
  ([attrs]
     (save (merge {:kind :user
                   :signed-in true
                   :cart []} attrs)))
  ([]
     (save {:kind :user
            :signed-in true
            :cart []})))

(defn setup-admin []
  (save {:kind :user :is-admin true}))

(defn setup-paypal-payment [id amount]
  (save {:kind :paypal-payment :id id :amount amount}))


;; user actions

(defn add-to-cart [who title]
  (if (:signed-in who)
    (save who :cart (conj (get-product title) (:cart who)))))

(defn remove-from-cart [who title]
  (if (:signed-in who)
    (let [product (get-product title)]
      (save who :cart (remove #{product} (:cart who))))))

(defn create-product [who title]
  )

(defn refund-payment [admin customer id]
  )


;; user predicates

(defn products-in-cart [user]
  (:cart (reload user)))

(defn redirected-to [user]
  )

(defn cart-has? [user title]
  (some #{title} (map :title (:cart (reload user)))))

(defn available-products []
  (find-by-kind :product))

(defn owned-products [user]
  []
  )
