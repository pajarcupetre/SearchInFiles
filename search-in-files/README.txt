Implementation details:
1. Hold the data about words from a File in a FileDictionary(filename, HashSet of Words)
Using HashSet of words, we can do a fast check if word is contained in file
2. Hold all the files data in a Dictionary(Seq of FileDictionary)
3. For all inputs we compute the ranking in the following way:
-> empty input(only separators) | empty input file => 0
-> all words found 100
-> found_words/total_needed_words*100 +
(found_words+1)/total_needed * found_words/total_words_from_file*50(to take in account when in file we have less words than needed)

Things to discuss:
How we select the separators:
SplitWords trait contains two separate logics:
1. splitIntoWords - using each separator as normal (default split used)
Array(',', ';', '?', '!', '<' , '>', ' ', ':', '{',  '"', '}', '[', ']', '(', ')' ,'\\', '/', '\t', '.')
This will imply some issues on '.'. We can have things like C.I.A will be seen as three words (C I A)
2. splitIntoWordsWithAbbreviations
-> remove '.' from separators and for each split word check if contains '.'
    We assume if the '.' it is in the end that can be and ending sentence and split on '.' or take full word if not
Another potential discussion on ',' on 2,4,5-t (2,4,5-Trichlorophenoxyacetic )


The project is using sbt as build tool.
Can be run in the following ways:
1. run tests
sbt test
2. make package and run from jar
sbt package
scala  -classpath  target/scala-2.11/searchinfiles_2.11-0.1.0-SNAPSHOT.jar search.SimpleSearch inputDir
3. sbt run
sbt "run inputDir"
