(ns tiny-compiler.parser-test
  (:require [clojure.test :refer :all]
            [tiny-compiler.parser :refer :all]))


(deftest parser-test
  (testing "parser returns a valid AST when given a valid sequence of tokens"
    (is (=
          (parser
            '({:type :form :value :brace-left}
              {:type :form :value :plus}
              {:type :int :value 1}
              {:type :int :value 1}
              {:type :form :value :brace-right}))

          {:type :program
           :body [
            {:type :call-expression
             :name :plus
             :arguments [{:type :int :value 1}
                         {:type :int :value 1}]}]}
          ))

    (is (=
          (parser
            '({:type :form :value :brace-left}
               {:type :form :value :plus}
               {:type :form :value :brace-left}
               {:type :form :value :star}
               {:type :int :value 2}
               {:type :int :value 3}
               {:type :form :value :brace-right}
               {:type :int :value 1}
               {:type :form :value :brace-right}))

          {:type :program
           :body [
                  {:type :call-expression
                   :name :plus
                   :arguments [{:type :call-expression
                                :name :star
                                :arguments [{:type :int :value 2}
                                            {:type :int :value 3}]}
                               {:type :int :value 1}]}]}
          ))

    ))
