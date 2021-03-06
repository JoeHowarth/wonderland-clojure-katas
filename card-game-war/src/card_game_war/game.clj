(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

; return vector with player who won and vector of won cards
(defn play-round [p1-card p2-card]
  (let [p1-suit (.indexOf suits (p1-card 0))
        p1-rank (.indexOf ranks (p1-card 1))
        p2-suit (.indexOf suits (p2-card 0))
        p2-rank (.indexOf ranks (p2-card 1))]
    (cond (> p1-rank p2-rank) [1 [p1-card p2-card]]
          (< p1-rank p2-rank) [2 [p1-card p2-card]]
          :else (if (> p1-suit p2-suit)
                    [1 [p1-card p2-card]]
                    [2 [p1-card p2-card]]))))

(def ex_p1 [:club 5])
(def ex_p2 [:diamond 7])


(defn play-game
 ; used with shuffle-deck that returns a list of lists
 ([deck] (let [[p1-cards p2-cards] deck]
              (play-game p1-cards p2-cards)))
 ;
 ([p1-cards p2-cards]
  (let [[winner cards](play-round
                        (first p1-cards) (first p2-cards))]
    (if (= winner 1)
      (conj p1-cards cards)
      (conj p2-cards cards))
    (play-game p1-cards p2-cards))))

(defn cart [colls]
  (if (empty? colls)
    '(())
    (for [x (first colls)
          more (cart (rest colls))]
      (cons x more))))

(defn shuffle-deck
  ([] (shuffle-deck suits ranks))
  ([suits ranks]
   (partition 26 (shuffle (vec (cart [suits ranks]))))))
