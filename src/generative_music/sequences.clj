(ns generative-music.sequences
  (:require [generative-music.chord :refer (get-chord)]
            [generative-music.core :refer (guitar-song)]))

;; A pre-built sequence for some music.

(def major-scale
  [[0 (get-chord :C)]
   [500 (get-chord :D)]
   [1000 (get-chord :E)]
   [1500 (get-chord :F)]
   [2000 (get-chord :G)]
   [2500 (get-chord :A)]
   [3000 (get-chord :B)]
   [3500 (get-chord :C)]])

(defn play-major-scale []
  (guitar-song major-scale))
