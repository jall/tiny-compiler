(ns tiny-compiler.tokenizer-test
  (:require [clojure.test :refer :all]
            [tiny-compiler.tokenizer :refer :all]))


(deftest tokenizer-test
  (testing "tokenizer returns a sequence of tokens given valid input"
    (is (= (tokenizer "(+ 1 1)") '({:symbol :brace-left} {:symbol :plus} {:int 1} {:int 1} {:symbol :brace-right})))))


(deftest take-int-test
  (testing "take-int returns sane output"
    (is (= (take-int "234") ["" {:int 234}]))
    (is (= (take-int "1 + 0") [" + 0" {:int 1}]))
    (is (= (take-int "001 + 002") [" + 002" {:int 1}]))
    (is (= (take-int "876 + 152") [" + 152" {:int 876}])))

  (testing "take-int returns nil when the next tokens are not ints"
    (is (= (take-int "(+ 1 1)") nil))
    (is (= (take-int "(((+)))") nil))))


(deftest take-sym-test
  (testing "take-sym recognises all the symbols it should"
    (is (= (take-sym "(") ["" {:symbol :brace-left}]))
    (is (= (take-sym ")") ["" {:symbol :brace-right}]))
    (is (= (take-sym "+") ["" {:symbol :plus}]))
    (is (= (take-sym "-") ["" {:symbol :minus}]))
    (is (= (take-sym "/") ["" {:symbol :slash}]))
    (is (= (take-sym "*") ["" {:symbol :star}])))

  (testing "take-sym returns sane output"
    (is (= (take-sym "(+ 1 1)") ["+ 1 1)" {:symbol :brace-left}])))

  (testing "take-sym returns nil when the next tokens are not ints"
    (is (= (take-sym "123") nil))))
