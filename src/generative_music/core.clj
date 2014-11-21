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

;; The standard tuning for guitar notes from high (E4) to low (E2)
(def guitar-notes
  [:E4 :B3 :G3 :D3 :A2 :E2])

;; Determining the frequency of a string at a certain fret.
(defn fretted [string fret]
  (midi->hz (note (+ fret (note string)))))

;; Playing a chord on the guitar.
(defn guitar-chord [notes]
  (map guitar notes))

;; Doing some testing.
(defn test-notes []
  (println (inc (note :E2)))
  (println (note :G2)))

;; A standard G-major chord.
(def g-chord
  [(fretted :E2 3)
   (fretted :D3 0)
   (fretted :G3 0)
   (fretted :B3 0)
   (fretted :A2 2)
   (fretted :E4 3)])

;; A standard E-major chord.
(def e-chord
  [(fretted :E2 0)
   (fretted :A2 2)
   (fretted :D3 2)
   (fretted :G3 1)
   (fretted :B3 0)
   (fretted :E4 0)])
