(ns alphabet-cipher.coder)
(apply require clojure.main/repl-requires)

(defn alpha_to_int [str]
  (for [x (map int str)] (- x (int \a))))

(defn ints_to_strings [ints]
  (apply str
    (for [x ints]
      (char (+ x (int \a))))))

(defn match-up [keyword message]
  (map vector
      (alpha_to_int message)
      (cycle (alpha_to_int keyword))))

(defn coder [f str1 str2]
  (ints_to_strings
    (for [x (match-up str1 str2)]
      (mod (reduce f x) 26))))

(defn min_sub [str]
  (loop [i 1]
    (if (= (subs str 0 i) (subs str i (* 2 i)))
      (subs str 0 i)
      (recur (inc i)))))

(defn encode [keyword message]
  (coder + keyword message))

(defn decode [keyword message]
  (coder - keyword message))

(defn decipher [cipher message]
  (min_sub (coder - message cipher)))
