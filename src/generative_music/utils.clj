(ns generative-music.utils)

;; Running a function over some collection with reference to its index.
(defn foreach-index [fn things]
  (let [l (count things)]
    (loop [index 0]
      (when (< index l)
        (do
          (fn index (get things index))
          (recur (+ index 1)))))))
