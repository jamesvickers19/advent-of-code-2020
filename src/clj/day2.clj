(ns clj.day2)

(defn parse-range [r]
  (map #(Integer/parseInt %) (.split r "-")))

;; (parse-password-line "1-3 a: abcde")
;; -> {:min 1 :max 3 :letter \a :password "abcde"}
(defn parse-password-line [line]
  (let [[range-str letter password] (.split line " ")
        [min max] (parse-range range-str)
        letter (first (.replace letter ":" ""))]
    {:min min
     :max max
     :letter letter
     :password password}))

(defn password-valid-by-counts?
  [{:keys [min max letter password]}]
  (let [occurrences (count (filter #{letter} password))]
    (<= min occurrences max)))

(defn password-valid-by-indices?
  [{:keys [min max letter password]}]
  (let [index-matches? #(= letter (nth password (dec %)))] ; dec because not zero-indexed
    (Boolean/logicalXor (index-matches? min) (index-matches? max))))

(defn num-valid-passwords [valid-fn file]
  (let [contents (slurp file)
        lines (clojure.string/split-lines contents)
        password-lines (map parse-password-line lines)]
    (count (filter valid-fn password-lines))))

(let [input "inputs\\day2_input.txt"]
  (println "Part 1: valid passwords:"
           (num-valid-passwords password-valid-by-counts? input))
  (println "Part 2: valid passwords:"
           (num-valid-passwords password-valid-by-indices? input)))