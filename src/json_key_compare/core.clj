(ns json-key-compare.core
  (:require [clojure.string :as str]
            [clojure.data.json :as json])
  (:use [clojure.data])
  (:gen-class :main true))

(defn get-param
  [input index]
  (get (str/split input #" ") index))

(defn read-json-file
  [file-path]
  (json/read-str (slurp file-path)))

(defn write-json-file
  [data file-path]
  (spit file-path (json/write-str data)))

(defn diff-files
  [input]
  (clojure.data/diff
   (read-json-file (get-param input 0))
   (read-json-file (get-param input 1))))

(defn display-help
  []
  (println "Usage: jkc file1 file2 outputfile"))

(defn validate-input
  [input]
  (if (not (= (count (str/split input #" ")) 3))
    (do
      (display-help)
      false)
    true))

(defn -main
  ([] (display-help))
  ([input]
   (if (validate-input input)
     (write-json-file (first (diff-files input)) (get-param input 2)))))
