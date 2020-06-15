(ns tiny-compiler.core
  (:require [tiny-compiler.tokenizer :refer [tokenizer]]))

(defn -main
  "All journeys start with a single step"
  []
  (let
    [input "(+ 1 1)"
     tokens (tokenizer input)]
    (println "Input")
    (println input)
    (println "Tokens")
    (println tokens)))
