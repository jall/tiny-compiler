(ns tiny-compiler.parser
  (:require [clojure.string]))


(defn ->tree
  ([tokens]
    (->tree tokens []))
  ([tokens expressions]
   (let [[token & remainder] tokens]
     (cond
       (= (:type token) :form)
          (case (:value token)
             :brace-left (conj expressions {:type :call-expression
                                            ; Make the assumption the first thing in parens is a function identifier
                                            :name (:value (first remainder))
                                            :arguments (mapcat identity (remove nil? (map ->tree (map list (rest remainder)))))})

             :brace-right nil

             :else (throw (Exception. (str "Unknown form '" (:value token) "' passed to parser"))))

       (= (:type token) :int) (conj expressions {:type :int :value (:value token)})
     ))))

(defn parser [tokens]
  {:type :program :body (->tree tokens)})

; List of tokens
; if next token is left parens, recurse everything after that until right parens as
