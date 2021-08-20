(ns tiny-compiler.core
  (:require [tiny-compiler.tokenizer :refer [tokenizer]]
            [tiny-compiler.parser :refer [parser]]))

(defn -main
  "All journeys start with a single step"
  []
  (let
    [input "(+ (* 2 3) 1)"
     tokens (tokenizer input)
     ast (parser tokens)]
    (println "Input")
    (println input)
    (println "Tokens")
    (println tokens)
    (println "AST")
    (println ast)))
