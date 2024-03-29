(ns tiny-compiler.tokenizer
  (:require [clojure.string]))

(defn str->int [s]
  (if (re-matches #"^\d+$" s)
    (read-string s)))

(defn take-int [s]
  (when (Character/isDigit (char (first s)))
    (let [digits (re-find #"^\d+" s)
          remainder (subs s (count digits))]
      [remainder {:type :int :value (str->int digits)}])))

(def char->form {\( :brace-left
                \) :brace-right
                \+ :plus
                \- :minus
                \* :star
                \/ :slash})

(defn take-form [s]
  (if-let [form (char->form (char (first s)))]
    [(subs s 1) {:type :form :value form}]))

(defn take-error [expression]
  [nil {:error (str "Invalid symbol '" (first expression) "'")}])

(defn tokenizer [expression]
  (lazy-seq
    (let [expr (clojure.string/replace expression #"^\s+" "")]
      (if-not (clojure.string/blank? expr)
        (let [[remainder token]
               (or (take-int expr)
                   (take-form expr)
                   (take-error expr))
               ]
          (cons token
            (if-not (or (:error token) (nil? remainder))
              (tokenizer remainder))))))))
