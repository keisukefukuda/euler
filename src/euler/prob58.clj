(ns euler.prob58
  (:require [clojure.math.numeric-tower :as math]
            [euler.numbers :refer :all]
            [chiara]))

(defn count-primes [nums]
  (count (filter is-prime? nums)))

(comment
  (let [n (count nums)]
    (loop [p 0 i 0]
      (if (>= i n)
        p
        (if (is-prime? (nth nums i))
          (recur (inc p) (inc i))
          (recur p (inc i)))))))

(defn D [^long n] ;; 4n^2 + 4n + 1 = (2n+1)^2
  (if (zero? n) 1
      (+ (* 4 (* n n))
         (* 4 n)
         1)))

(defn C [^long n] ;; Cn = 4n^2 = 2n + 1
  (if (zero? n) 1
      (+ (* 4 (* n n))
         (* 2 n)
         1)))

(defn B [^long n] ;; 4n^2 + 1
  (if (zero? n) 1
      (+ (* 4 (* n n))
         1)))

(defn A [^long n] ;; 4n^2 - 2n + 1
  (if (zero? n) 1
      (+ (* 4 (* n n))
         (* -2 n)
         1)))

(defn answer [^double threshold]
  (loop [n 1 nums 5 nprms 0]
    (let [a (A n)
          b (B n)
          c (C n)
          d (D n)
          nprms (+ nprms (count-primes [a b c])) ; d is never prime
          ratio (double (/ nprms nums))]
      ;; (if (or
      ;;      (< n 10)
      ;;      (zero? (mod n 300)))
      ;;   (println n a b c d " [" nprms "/" nums "] " ratio))
      (if (and (> n 0)
               (< ratio threshold))
        (do
          (println n a b c d " [" nprms "/" nums "] " ratio)
          (println "n = " n ", answer = " (+ (* 2 n) 1)))
        (recur (inc n) (+ nums 4) nprms)))))

(defn -main [& args]
  (time
   (answer (if (empty? args)
             0.10
             (Double/parseDouble (first args))))))

