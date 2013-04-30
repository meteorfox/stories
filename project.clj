(defproject stories "0.1.2"
  :description "Freeform BDD lib for Clojure"
  :url "https://github.com/sdegutis/stories"
  :license {:name "MIT" :url "http://opensource.org/licenses/MIT"}

  ;; TODO: i have no idea how many of these speclj things we actually need.

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [hyperion/hyperion-api "3.6.0"]
                 [speclj "2.5.0"]]

  :profiles {:dev {:dependencies [[speclj "2.5.0"]]
                   :resource-paths ["dev/"]
                   :test-paths ["spec/"]}}

  :plugins [[speclj "2.5.0"]]

  )
