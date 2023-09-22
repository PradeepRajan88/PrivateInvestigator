package com.codingChallenge.PrivateInvestigator.logic;

import com.codingChallenge.PrivateInvestigator.obj.SimilarGroup;

import java.util.*;

public class SimilarityLogic {

    private static final int TIMESTAMP_INDEX = 20;

    /**
     * Iterates over the input lines and finds and groups similar lines. Also collects the changing words.
     * Two strings are considered similar if exactly one word differs between them.
     * @param lines input lines
     * @return a Map in which key is the common pattern and value is a SimilarGroup object,
     * which holds the line numbers of the corresponding lines.
     */
    public static Map<String, SimilarGroup> findSimilar(List<String> lines) {
        final int size = lines.size();
        Map<String, SimilarGroup> similarGroupMap = new HashMap<>();

        // convert lines into arrays of words, so that don't have to tokenize the lines inside the nested for loop.
        List<String[]> arraysOfWords = new ArrayList<>();
        for (String line : lines) {
            String[] words = removeTimeStampPrefix(line).split(" ");
            arraysOfWords.add(words);
        }

        for (int i = 0; i < size; i++) {
            String[] iArray = arraysOfWords.get(i);

            for (int j = i + 1; j < size; j++) {
                String[] jArray = arraysOfWords.get(j);

                List<String> changingWords = findChangingWordsIfSimilar(iArray, jArray);
                if (!changingWords.isEmpty()) {

                    String si = removeTimeStampPrefix(lines.get(i));
                    String pattern = si.replace(changingWords.get(0), "*");
                    SimilarGroup sg = similarGroupMap.get(pattern);
                    if (sg == null) {
                        sg = new SimilarGroup();
                        similarGroupMap.put(pattern, sg);
                    }
                    sg.getLineNumbers().add(i);
                    sg.getLineNumbers().add(j);
                    sg.getChangingWords().addAll(changingWords);
                }
            }
        }
        return similarGroupMap;
    }

    /**
     * Two strings are considered similar if exactly one word differs between them.
     * This method compares two strings and returns the two differing words if and only if the strings are similar.
     * Returns empty list if the two strings are not similar.
     *
     * @param iArray a sentence as an array of words.
     * @param jArray another sentence as an array of words.
     * @return changing words as a list of size 2 if the two strings are similar (exactly one word is different),
     * or an empty list if the two strings are not similar.
     */
    private static List<String> findChangingWordsIfSimilar(String[] iArray, String[] jArray) {
        List<String> changingWords = new ArrayList<>();
        boolean diffFound = false;
        // if word counts doesn't match, they are not similar
        if (iArray.length == jArray.length) {
            for (int i = 0; i < iArray.length; i++) {
                String iToken = iArray[i];
                String jToken = jArray[i];
                if (!iToken.equals(jToken)) {
                    if (diffFound) {
                        // more than one difference means not similar.
                        return new ArrayList<>();
                    }
                    diffFound = true;
                    changingWords.add(iToken);
                    changingWords.add(jToken);
                }
            }
        }
        return changingWords;
    }

    public static String removeTimeStampPrefix(String line) {
        if (line != null && line.length() > TIMESTAMP_INDEX) {
            line = line.substring(TIMESTAMP_INDEX);
        }
        return line;
    }

    public static List<String> generateOutputLines(Map<String, SimilarGroup> similarGroupMap, List<String> lines) {
        List<String> outputLines = new ArrayList<>();
        if (similarGroupMap != null && lines != null) {
            similarGroupMap.forEach((key, value) -> {
                value.getLineNumbers().forEach(integer -> outputLines.add(lines.get(integer)));
                outputLines.add("The changing word was: " + String.join(", ", value.getChangingWords()));
            });
        }
        return outputLines;
    }

}
