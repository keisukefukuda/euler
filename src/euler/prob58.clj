;; http://freak-da.hatenablog.com/entry/2015/05/30/162610
(ns euler.prob58
  (:require [clojure.math.numeric-tower :as math]
            [euler.numbers :refer :all]
            [cinfix.core :refer ($=)]))

(defn count-primes [nums]
  (count (filter is-prime? nums)))

(defn D [^long n] ;; 4n^2 + 4n + 1 = (2n+1)^2
  (if (zero? n) 1
      ($= "4*n*n + 4*n + 1")))

(defn C [^long n] ;; Cn = 4n^2 = 2n + 1
  (if (zero? n) 1
      ($= "4*n*n + 2*n + 1")))

(defn B [^long n] ;; 4n^2 + 1
  (if (zero? n) 1
      ($= "4*n*n + 1")))

(defn A [^long n] ;; 4n^2 - 2n + 1
  (if (zero? n) 1
      ($= "4*n*n - 2*n + 1")))

(defn answer [^double threshold]
  (loop [n 1 nums 5 nprms 0]
    (let [a (A n)
          b (B n)
          c (C n)
          d (D n)
          nprms (+ nprms (count-primes [a b c])) ; d is never prime
          ratio (double (/ nprms nums))]
      (if (and (> n 0)
               (< ratio threshold))
        (do
          (println n a b c d " [" nprms "/" nums "] " ratio)
          (let [answer (+ (* 2 n) 1)]
            (println "n = " n ", answer = " answer)
            answer))
        (recur (inc n) (+ nums 4) nprms)))))

(defn run [& args]
  (answer (if (empty? args)
            0.10
            (Double/parseDouble (first args)))))

