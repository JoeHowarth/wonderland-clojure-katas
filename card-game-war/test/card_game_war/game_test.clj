(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))

(defn winner-helper [p1-card p2-card]
  (get (play-round p1-card p2-card) 0))

;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (= 2 (winner-helper [:spade 4] [:heart 6]))))
  (testing "queens are higher rank than jacks"
    (is (= 2 (winner-helper [:spade :jack] [:heart :queen]))))
  (testing "kings are higher rank than queens"
    (is (= 2 (winner-helper [:spade :queen] [:heart :king]))))
  (testing "aces are higher rank than kings"
    (is (= 1 (winner-helper [:spade :ace] [:heart :king]))))
  (testing "if the ranks are equal, clubs beat spades"
    (is (= 1 (winner-helper [:club :king] [:spade :king]))))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (= 2 (winner-helper [:club 5] [:diamond 5]))))
  (testing "if the ranks are equal, hearts beat diamonds"
    (is (= 1 (winner-helper [:heart :king] [:diamond :king])))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"))
