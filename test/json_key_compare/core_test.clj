(ns json-key-compare.core-test
  (:require [clojure.test :refer :all]
            [json-key-compare.core :refer :all]))

(deftest validate-input-tests
  (testing "3 word input returns true"
    (is (= (validate-input "one two three") true)))

  (testing "2 word input returns false"
    (is (= (validate-input "one two") false)))

  (testing "3 word input does not print help message"
    (is (= (with-out-str (validate-input "one two three")) "")))

  (testing "short input prints help message"
    (is (= (with-out-str (validate-input "one two")) (with-out-str (display-help)))))

  (testing "long input prints help message"
    (is (= (with-out-str (validate-input "1 2 3 4 5")) (with-out-str (display-help))))))

(deftest get-param-tests
  (testing "returns file1 path when passed 0 index"
    (is (= (get-param "testFile1 testFile2 outputFile" 0) "testFile1")))
  (testing "returns file1 path when passed 0 index"
    (is (= (get-param "testFile1 testFile2 outputFile" 1) "testFile2")))
  (testing "returns file1 path when passed 0 index"
    (is (= (get-param "testFile1 testFile2 outputFile" 2) "outputFile"))))
