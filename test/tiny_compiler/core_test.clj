(ns tiny-compiler.core-test
  (:require [clojure.test :refer :all]
            [tiny-compiler.core :refer :all]))

(deftest tokenizer-valid-input
  (testing "tokenizer returns tokens for valid input"
    (is (= (tokenizer "(+ 1 1)") '({:symbol :brace-left} {:symbol :plus} {:int 1} {:int 1} {:symbol :brace-right})))))
