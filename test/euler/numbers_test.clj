(ns euler.numbers-test
  (:require [clojure.test :refer :all]
            [euler.numbers :refer :all]))

(deftest test-is-prime?
  (reset-primes)

  (is (not (is-prime? 1)))
  (is (is-prime? 2))
  (is (is-prime? 3))
  (is (not (is-prime? 3)))
  
  ;; http://homepage2.nifty.com/hiranouchi/prime/
  (is (is-prime? 4133))
  (is (is-prime? 9931))

  ;; numbers that looks like prime but actually not
  (is (not (is-prime? 1001)))
  (is (not (is-prime? 527)))
  (is (not (is-prime? 11111))))


