(ns generative-music.core
  (:use [overtone.live]
        [overtone.inst.piano]))

;; Converting a note to hertz.
(defn note->hz [music-note]
  (midi->hz (note music-note)))

;; A synth meant to sound like a guitar.
(definst guitar [frequency 440 duration 15
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

;; Playing a frequency on the guitar specified by a note.
(defn guitar-note [note]
  (guitar (note->hz note)))

;; A quicker synonym for killing an instrument.
(defn k [n]
  (kill n))

;; A quicker synonym for killing everything.
(defn s []
  (stop))

;; Playing a bunch of piano in a row.
(defn play-piano [interval min max]
  (let [start (now)]
    (loop [curr min]
      (if (>= curr max)
        nil
        (do (at (+ start (* interval (- curr min))) (piano curr))
            (recur (+ curr 1)))))))

(definst foo [freq 220]
  (saw freq))
