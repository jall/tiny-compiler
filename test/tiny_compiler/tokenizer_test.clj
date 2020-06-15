(ns tiny-compiler.tokenizer-test
  (:require [clojure.test :refer :all]
            [tiny-compiler.tokenizer :refer :all]))

(deftest tokenizer-valid-input
  (testing "tokenizer returns tokens for valid input"
    (is (= (tokenizer "(+ 1 1)") '({:symbol :brace-left} {:symbol :plus} {:int 1} {:int 1} {:symbol :brace-right})))))
