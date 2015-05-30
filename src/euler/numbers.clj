(ns euler.numbers
  (:import [java.util.Collections])
  (:require [clojure.math.numeric-tower :as math]))

(def ^:private primes (ref [2]))
(def ^:private lastp  (ref (last @primes)))

(defn reset-primes []
  (dosync
   (ref-set primes [2])
   (ref-set lastp (last @primes))))

;; http://tnoda-clojure.tumblr.com/post/26436769664/sieve-of-erathosthenes-in-clojure
(defn primes'
  ([^long n]
   (let [not-prime (doto (boolean-array (inc n))
                     (aset 0 true)
                     (aset 1 true))
         primes (long-array (inc n))]
     (loop [p (int 0), i (int 2)]
       (if (<= i n)
         (if (aget ^booleans not-prime i)
           (recur p (inc i))
           (do
             (aset ^longs primes p i)
             (loop [j (* 2 i)]
               (when (<= j n)
                 (aset ^booleans not-prime j true)
                 (recur (+ j i))))
             (recur (inc p) (inc i))))
         (take p primes))))))

(defn is-prime? [^long n]
  (let [sq (-> n math/sqrt long)]
    (cond
      (= n 1) false
      (= n 2) true
      :else
      (loop [i 3]
        (cond
          (> i sq) true
          (zero? (mod n i)) false
          :else (recur (+ i 2)))))))
  
(comment
(defn is-prime? [^long n]
  (if (< @lastp n)
    (dosync
     (let [prms (apply vector (doall (primes' (* n 10))))]
       (ref-set primes prms)
       (ref-set lastp (last prms)))))
  ;;(some #{n} @primes))
  (>= (java.util.Collections/binarySearch @primes n compare) 0))
)
