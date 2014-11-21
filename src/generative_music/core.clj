(ns generative-music.core
  (:use [overtone.live]
        [overtone.inst.piano]))

;; COMES IN STRONK
(definst trem [freq 440 depth 10 rate 6 length 3]
  (* 0.3
     (line:kr 0 10 length FREE)
     (line:kr 1 0 length FREE)
     (saw (+ freq (* depth (sin-osc:kr rate))))))

;; The scale to use when modeling a guitar.
(def guitar-harmonics
  [1
   (/ 1 2)
   (/ 1 3)
   (/ 1 4)
   (/ 1 5)
   (/ 1 6)
   (/ 1 7)])

;; Converting a note to hertz.
(defn note->hz [music-note]
  (midi->hz (note music-note)))

;; Playing a given note on the guitar.
(definst guitar [note 60]
  (* (line:kr 0 1 2 FREE)
     (saw (midicps note))))

;; A quicker synonym for killing an instrument.
(defn k [n]
  (kill n))

;; A quicker synonym for killing everything.
(defn s
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
