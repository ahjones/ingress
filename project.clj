(defproject ingressinvites "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.0"]
                 [cheshire "4.0.3"]
                 [rotary "0.2.8"]
                 [ring/ring-jetty-adapter "1.1.0"]]
  :source-paths ["src/clj"]
  :plugins [[lein-cljsbuild "0.2.9"]
            [lein-ring "0.7.1"]]
  :cljsbuild {:builds [{:source-path "src/cljs"
                        :compiler {:output-to "resources/public/ingress.js"
                                   :optimizations :simple}}]}
  :ring {:handler ingressinvites.core/handler}
  :main ingressinvites.core)
