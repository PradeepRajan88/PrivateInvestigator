# Private Investigator

## Overview

This is a plain Java solution written and tested with Java 17. Prior versions should word as well, but are not tested.
Maven is used to build the project, but there are no dependencies at this time.

## How to run

Build the source with Maven. Paste your input data in [inputFile.txt](inputFile.txt) in the root directory. Run [Main.java](src%2Fmain%2Fjava%2Fcom%2FcodingChallenge%2FPrivateInvestigator%2FMain.java) as a Java application. output will be written to [outputFile.txt](outputFile.txt) (also printed to the console).

## Time Complexity

The algorithm compares each unique string with every other unique string with a nested for loop which results in a time complexity of
O(n * n-1)/2. This is effectively quadratic O(n^2). My intuition is that this cannot be further reduced (unless we make assumptions about the structure of the strings).

However, Since people follow routines, it is logical to assume that the same event happens repeatedly. For example, "Naomi is getting into the car" can
happen again and again at different timestamps. In that case, we only need to consider it once in our comparison algorithm (provided we map the occurrences of that string in the input
for creating the output data). This optimization is done with the SimilarityLogic.sameEventIndexesMap() method. if we assume that elimination repetitions reduces n to log(n),
our time complexity becomes O(log(n)^2)

inside the nested for loop, the operation is a fail-fast string to string comparison, which can be considered constant time because the length of the strings is limited. We are actually doing a fail-fast word-word comparison, which is faster than character to character comparison because in a typical case we won't need to look at every character, and spaces are removed. 

## Space Complexity

Space complexity is O(n) because the largest dataset we use is the list<String> to hold the input data. everything else used are reduced data such as map of unique strings, map of line numbers and changing words, etc.


## Scaling 

If my assumption that events happens repeatedly is true, this solution scales ell. I tested with the 'inputFileLarge.txt' (contains 10,000 lines) and it runs in less than 100 milliseconds. However, if this assumption is wrong, this becomes O(n * n-1)/2 which doesn't scale well.

### Another possible optimization 

If the number of unique words in out data set is small (limited number of people doing limited number of things again and again), we can use a Map of word - serialNumber to reduce a sentence into an array of integers.
Then out comparison algorithm becomes int comparison (i == j) rather than String comparison using (iString.equals(jString)), which is faster. However, word order matters for us, so we cannot sort our integer array like many int array comparison algorithms.

## If I had more time

I would try to optimize further, using the about word-int Map, or if we can assume that the Strings have a fixed structure (for example Name + verb + noun), that's a possibility for optimization. If we can assume that phrases like "is", "at a", "the" follow fixed pattern, we can try to remove them from comparison algorithm.
Also, there is scope to make the code more modular and more readable.

It would be good to have unit tests for each function in the logic class.

If I convert this solution into a rest api which accepts the input and returns the output, the solution becomes more usable.

It is worth looking into various String Metrics algorithms, like Levenshtein distance, and their optimisations and dynamic programming approaches. probably we can adapt their character to character comparison into a fail-fast word-to-word comparison.



