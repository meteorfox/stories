(ns fake-website.stories)

;; we should be able to read these step definitions and immediately so
;; "oh yeah i understand this perfectly" and have full assurrance and
;; confidence in the test suite.

;; step definitions have to be THIN. not doing too much translation
;; between the DSL and the real code. otherwise it becomes useless.

(defn setup-database []
  )

(defn setup-product [mapping]
  )

(defn setup-visitor []
  )

(defn setup-customer
  ([attrs]
     )
  ([]
     ))

(defn setup-admin []
  )

(defn setup-paypal-payment [id amount]
  )





(defn add-to-cart [who title]
  )

(defn remove-from-cart [who title]
  )

(defn create-product [who title]
  )

(defn refund-payment [admin customer id]
  )



(defn products-in-cart [user]
  )

(defn redirected-to [user]
  )

(defn cart-has? [user title]
  )

(defn available-products []
  []
  )

(defn owned-products [user]
  []
  )
