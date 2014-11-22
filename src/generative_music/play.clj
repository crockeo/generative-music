(ns generative-music.play
  (:use overtone.live))

;; Playing a given tone for a given duration on a synth.
(defn play-tone [synth & [offset frequency duration]]
  (let [offset* (if offset
                  offset
                  0)

        frequency* (if frequency
                     frequency
                     440)

        duration* (if duration
                    duration
                    1)]
    (at (+ offset* (now)) (synth frequency* duration*))))
