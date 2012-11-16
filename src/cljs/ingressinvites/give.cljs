(ns ingressinvites.give
  (:require [goog.events :as ev]
            [goog.events.Event :as events]
            [goog.net.XhrIo :as xhr]
            [goog.dom :as dom]))

(defn add-invitation-callback [message]
  (set! (.-innerHTML (dom/getElement "thanks"))
        "<div class=\"alert\">Thank you.</div>"))

(defn get-dom-value
  [id]
  (.-value (dom/getElement id)))

(defn add-invitation
  [e]
  (xhr/send "/invitation/" add-invitation-callback "POST" (str "{\"invitation\":\"" (get-dom-value "invitation") "\"}"))

  (.preventDefault e))

(defn ^:export main []

  (ev/listen
   (dom/getElement "add-invitation-button")
   "click"
   add-invitation))
