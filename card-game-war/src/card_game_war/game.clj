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
  (let [[p1-suit p1-rank] p1-card [p2-suit p2-rank] p2-card]
    (if >
      (.indexOf ranks p1-rank)
      (.indexOf ranks p2-rank))))

(defn play-game [p1-cards p2-cards])
