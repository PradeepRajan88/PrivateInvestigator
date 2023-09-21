package com.codingChallenge.PrivateInvestigator.logic;

import com.codingChallenge.PrivateInvestigator.obj.SimilarGroup;
import java.util.*;

public class SimilarityLogic {

    private static final int TIMESTAMP_INDEX = 19;

    /** Iterates over the input lines and finds and groups similar lines. Also collects the changing words
     *
     * @param lines input lines
     * @return a Map in which key is the common pattern and value is a SimilarGroup object,
     * which holds the line numbers of the corresponding lines.
     */
    public static Map<String, SimilarGroup> findSimilar(List<String> lines) {
        final int size = lines.size();
        Map<String, SimilarGroup> similarGroupMap = new HashMap<>();

        for (int i = 0; i < size; i++) {
            String si = removeTimeStampPrefix(lines.get(i));

            for (int j = i; j < size; j++) {
                String sj = removeTimeStampPrefix(lines.get(j));
                StringTokenizer sti = new StringTokenizer(si, " ");
                StringTokenizer stj = new StringTokenizer(sj, " ");

                List<String> changingWords = findChangingWordsIfSimilar(sti, stj);
                if (changingWords != null && !changingWords.isEmpty()) {

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

    /** Two strings are considered similar if exactly one word differs between them.
     *  This method compares two strings and returns the two differing words if and only if the strings are similar.
     *  Returns empty list if the two strings are not similar.
     *
     * @param sti the tokenized string.
     * @param stj the other tokenized string.
     * @return changing words as a list, if the two strings are similar (exactly one word is different)
     */
    private static List<String> findChangingWordsIfSimilar(StringTokenizer sti, StringTokenizer stj) {
        List<String> changingWords = new ArrayList<>();
        boolean diffFound = false;
        // if word counts doesn't match, they are not similar
        if (sti.countTokens() == stj.countTokens()) {
            while (sti.hasMoreTokens()) {
                String iToken = sti.nextToken();
                String jToken = stj.nextToken();
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
            similarGroupMap.entrySet().forEach(entry -> {
                entry.getValue().getLineNumbers().forEach(integer -> {
                    outputLines.add(lines.get(integer));
                });
                outputLines.add("The changing word was: " + String.join(", ", entry.getValue().getChangingWords()));
            });
        }
        return outputLines;
    }

}
