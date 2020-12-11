(ns clj.day1
  (:require [clojure.math.combinatorics :as c]
            [clojure.java.io :as io]))

(defn read-numbers [filename]
  (let [contents (slurp filename)
        lines (clojure.string/split-lines contents)]
    (map #(Integer/parseInt %) lines)))

(defn sum [nums] (reduce + nums))

(defn product [nums] (reduce * nums))

(defn subsets-summing-to
  [nums subset-size target]
  (let [subsets (c/combinations nums subset-size)]
    (first
      (filter #(= (sum %) target) subsets))))

(let [nums (read-numbers "C:\\Users\\james\\Desktop\\clojure\\advent-of-code-2020\\inputs\\day1_input.txt")
      target 2020
      pair (subsets-summing-to nums 2 target)
      triplet (subsets-summing-to nums 3 target)]
  (println "day1 part 1: pair = " pair ", product: " (product pair))
  (println "day1 part 2: triplet = " triplet ", product: " (product triplet)))