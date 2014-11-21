(ns generative-music.chord
  (:use overtone.live))

;; The standard tuning for a guitar from low to high.
(def guitar-notes
  [:E2 :A2 :D3 :G3 :B3 :E4])

;; Determining the frequency of a string at a certain fret.
(defn fretted [string fret]
  (midi->hz (note (+ fret (note (get guitar-notes string))))))

;; Generating the form for a sharp note.
(defn -generate-sharp-note [start]
  [(fretted 0 start)
   (fretted 1 (+ start 2))
   (fretted 2 (+ start 2))
   (fretted 3 (+ start 1))
   (fretted 4 start)
   (fretted 5 start)])

;; A map of chords.
(def chords
  {:C  [(fretted 1 3)
        (fretted 2 2)
        (fretted 3 0)
        (fretted 4 1)
        (fretted 5 0)]

   :C# (-generate-sharp-note 9)

   :D  [(fretted 2 0)
        (fretted 3 2)
        (fretted 4 3)
        (fretted 5 2)]

   :D# (-generate-sharp-note 11)

   :E  [(fretted 0 0)
        (fretted 1 2)
        (fretted 2 2)
        (fretted 3 1)
        (fretted 4 0)
        (fretted 5 0)]

   :F  [(fretted 2 3)
        (fretted 3 2)
        (fretted 4 1)
        (fretted 5 1)]

   :F# (-generate-sharp-note 2)

   :G  [(fretted 0 3)
        (fretted 1 0)
        (fretted 2 0)
        (fretted 3 0)
        (fretted 4 2)
        (fretted 5 3)]

   :G# (-generate-sharp-note 4)

   :A  [(fretted 1 0)
        (fretted 2 2)
        (fretted 3 2)
        (fretted 4 2)
        (fretted 5 0)]

   :A# (-generate-sharp-note 6)

   :B  [(fretted 0 7)
        (fretted 1 9)
        (fretted 2 9)
        (fretted 3 8)
        (fretted 4 7)
        (fretted 5 7)]
   })

;; Getting a chord from the chords.
(defn get-chord [c]
  (get chords c))
