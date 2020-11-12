(ns tiny-compiler.tokenizer-test
  (:require [clojure.test :refer :all]
            [tiny-compiler.tokenizer :refer :all]))


(deftest tokenizer-test
  (testing "tokenizer returns a sequence of tokens given valid input"
    (is (= (tokenizer "(+ 1 1)") '({:type :form :value :brace-left}
                                   {:type :form :value :plus}
                                   {:type :int :value 1}
                                   {:type :int :value 1}
                                   {:type :form :value :brace-right})))))


(deftest take-int-test
  (testing "take-int returns sane output"
    (is (= (take-int "234") ["" {:type :int :value 234}]))
    (is (= (take-int "1 + 0") [" + 0" {:type :int :value 1}]))
    (is (= (take-int "001 + 002") [" + 002" {:type :int :value 1}]))
    (is (= (take-int "876 + 152") [" + 152" {:type :int :value 876}])))

  (testing "take-int returns nil when the next tokens are not ints"
    (is (= (take-int "(+ 1 1)") nil))
    (is (= (take-int "(((+)))") nil))))


(deftest take-form-test
  (testing "take-form recognises all the symbols it should"
    (is (= (take-form "(") ["" {:type :form :value :brace-left}]))
    (is (= (take-form ")") ["" {:type :form :value :brace-right}]))
    (is (= (take-form "+") ["" {:type :form :value :plus}]))
    (is (= (take-form "-") ["" {:type :form :value :minus}]))
    (is (= (take-form "/") ["" {:type :form :value :slash}]))
    (is (= (take-form "*") ["" {:type :form :value :star}])))

  (testing "take-form returns sane output"
    (is (= (take-form "(+ 1 1)") ["+ 1 1)" {:type :form :value :brace-left}])))

  (testing "take-form returns nil when the next tokens are not symbols"
    (is (= (take-form "123") nil))))
