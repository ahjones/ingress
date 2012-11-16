(ns ingressinvites.index
  (:require [goog.events :as ev]
            [goog.events.Event :as events]
            [goog.net.XhrIo :as xhr]
            [goog.dom :as dom]))

(defn add-email-callback [message]
  (set! (.-innerHTML (dom/getElement "thanks"))
        "<div class=\"alert\">Thanks: your email address has been registered.</div>"))

(defn get-dom-value
  [id]
  (.-value (dom/getElement id)))

(defn add-email
  [e]
  (xhr/send "/hopeful/" add-email-callback "POST" (str "{\"email\":\"" (get-dom-value "email") "\"}"))

  (.preventDefault e))

(defn ^:export main []

  (ev/listen
   (dom/getElement "add-email-button")
   "click"
   add-email))
