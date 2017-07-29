(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.set :as set]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))
; stategy:
; find words with 1 different letter
; find words 1 letter closer to target words
; recurse on coll

(defn sameLetter [acc lvec]
  (if (apply = lvec)
    acc
    (+ 1 acc)))

;; number of chars different in w1 and w2
(defn numDiff [w1 w2]
  (reduce sameLetter 0 (map vector w1 w2)))

(defn candidates [w1 links dict]
  (filter
    #(= 1 (numDiff w1 %1)) ;; find all 1 letter different words
    (set/difference (set dict) (set links)))) ; don't backtrack

(defn words-sameLen [w1]
  (filter
    #(= (count w1) (count %1))
    words))


(defn doublets
  ([w1 w2]
   (into []
     (doublets w1 w2 [] (words-sameLen w1)))) ; if no coll, call with empty
  ([w1 w2 links dict]
   (if (= w1 w2)
    (into links [w1])
    (some
      #(doublets %1 w2 (into links [w1]) dict)
      (candidates w1 links dict)))))

; (defn doublets
;   ([w1 w2]
;    (doublets w1 w2 [])) ; if no coll, call with empty
;   ([w1 w2 links]
;    (if (= w1 w2)
;     (into links [w1])
;     (loop [cands (candidates w1 links) cand (first cands)]
;       (if (> (count cands) 0)
;         (let [res (doublets cand w2 (into links [w1]))]
;           (if (= [] res)
;             (recur (rest cands) (first (rest cands)))
;             res))
;         [])))))
