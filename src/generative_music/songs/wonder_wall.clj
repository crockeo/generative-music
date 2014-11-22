(ns generative-music.songs.wonder-wall
  (:require [generative-music.chord :refer [fretted]])
  (:use [overtone.live]))

;; wonder-wall.clj
;;
;;  This is a file to specify the chords and timing required to play Wonder Wall
;;  by Oasis.

;; Specifying all of the chords used by Wonder Wall.
(def -chords
  {:F#m7 [(fretted 0 2)
          (fretted 1 0)
          (fretted 2 2)
          (fretted 3 2)
          (fretted 4 2)
          (fretted 5 0)]

   :A [(fretted 1 0)
       (fretted 2 2)
       (fretted 3 2)
       (fretted 4 2)
       (fretted 5 0)]

   :Esus4 [(fretted 0 0)
           (fretted 1 2)
           (fretted 2 2)
           (fretted 3 2)
           (fretted 4 0)
           (fretted 5 0)]

   :B7sus4 [(fretted 1 2)
            (fretted 2 2)
            (fretted 3 2)
            (fretted 4 0)
            (fretted 5 0)]

   :Dadd9 [(fretted 1 5)
           (fretted 2 4)
           (fretted 3 2)
           (fretted 4 5)
           (fretted 5 2)]

   :A/G# [(fretted 0 4)
          (fretted 1 4)
          (fretted 2 2)
          (fretted 3 2)
          (fretted 4 5)
          (fretted 5 5)]})

;; The sequence and timing of the chords.
(def wonder-wall
  nil)

;; Playing Wonder Wall.
(defn play-wonder-wall []
  (guitar-song wonder-wall))
