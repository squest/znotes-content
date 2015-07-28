(ns znotes.routes.home
  (:require [znotes.layout :as layout]
            [compojure.core :refer [routes GET]]
            [ring.util.http-response :refer [ok]]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render
    "home.html"
    (let [ccs (-> "content/cc.edn"
                  io/resource
                  slurp
                  read-string)]
      {:ccs ccs})))

(defn content
  [filename]
  (layout/render (str "/contents/" filename ".html")
                 (let [ccs (-> "content/cc.edn"
                               io/resource
                               slurp
                               read-string)]
                   {:ccs ccs})))

(def home-routes
  (routes (GET "/" [] (home-page))
          (GET "/content/:filename"
               [filename]
            (content filename))))

