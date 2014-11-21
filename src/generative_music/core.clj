(ns generative-music.core
  (:use [overtone.live]
        [overtone.inst.piano]))

;; Converting a note to hertz.
(defn note->hz [music-note]
  (midi->hz (note music-note)))

;; A synth meant to sound like a guitar.
(definst guitar [frequency 440 duration 10
                 h0 1 h1 0.5 h2 0.3 h3 0.25 h4 0.2 h5 0.16 h6 0.14]
  (let [harmonic-series [ 1  2  3  4  5  6  7]
        proportions     [h0 h1 h2 h3 h4 h5 h6]
        component (fn [harmonic proportion]
                    (* 1/2
                       proportion
                       (env-gen (perc 0.01 (* proportion duration)))
                       (sin-osc (* harmonic frequency))))
        whole (mix (map component harmonic-series proportions))]
    (detect-silence whole :action FREE)
    whole))

;; A macro to simulate strumming down on a guitar.
(defn strum-down [notes speed & [offset]]
  (let [origin (+ (now) (if offset
                          offset
                          0))
        l (count notes)]
    (loop [index 0]
      (if (>= index l)
        nil
        (do
          (at (+ origin (* speed index)) (guitar (get notes index)))
          (recur (+ index 1)))))))

;; A macro to simulate strumming up on a guitar.
(defn strum-up [notes speed & [offset]]
  (let [rnotes (into [] (reverse notes))
        origin (+ (now) (if offset
                          offset
                          0))
        l (count rnotes)]
    (loop [index 0]
      (if (>= index l)
        nil
        (do
          (at (+ origin (* speed index)) (guitar (get rnotes index)))
          (recur (+ index 1)))))))

;; Playing a chord on the guitar.
(defn guitar-chord [notes & [offset speed]]
  (let [offset* (if offset
                  offset
                  0)
        speed* (if speed
                 speed
                 8)]
    (strum-down notes speed* offset*)))

;; Playing a whole song on the guitar.
(defn guitar-song [song]
  (let [origin (now)
        per-chord (fn [[time notes]]
                    (guitar-chord notes time))]
    (map per-chord song)))
