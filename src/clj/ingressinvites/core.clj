(ns ingressinvites.core
  (:require [compojure.core :as compojure]
            [compojure.route :as route]
            [cheshire.core :as cheshire]
            [rotary.client :as rotary]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]])
  (:gen-class))

(def cred {:access-key (env :aws-access-key)
           :secret-key (env :aws-secret-key)
           :endpoint "http://dynamodb.eu-west-1.amazonaws.com"})

(defn- get-body [req]
  (cheshire/parse-string (slurp (:body req)) true))

(defn- add-hopeful [req]
  (let [body (get-body req)
        id (.toString (java.util.UUID/randomUUID))
        email (:email body)
        time (System/currentTimeMillis)]
    (rotary/put-item cred "ingress-waiting" {"id" id "email" email "time" time})
    {:id id}))

(compojure/defroutes handler
  (compojure/POST "/hopeful/" [:as request]
                  {:status 201
                   :body (cheshire/generate-string (add-hopeful request))})
  (compojure/GET "/" [] {:status 301 :headers {"Location" "/index.html"}})
  (route/resources "/"))


(defn -main [port]
  (jetty/run-jetty handler {:port (Integer. port)}))
