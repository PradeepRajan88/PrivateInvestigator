package com.codingChallenge.PrivateInvestigator.obj;

import java.util.HashSet;
import java.util.Set;

/**
 * POJO that represents a group of similar strings. Two strings are considered similar if exactly one word differs between them.
 * A SimilarGroup contains two or more strings (represented by their line numbers in the input). Also hold the corresponding changing words.
 */
public class SimilarGroup {

    private Set<Integer> lineNumbers;
    private Set<String> changingWords;

    public SimilarGroup() {
        lineNumbers = new HashSet<>();
        changingWords = new HashSet<>();
    }

    public Set<Integer> getLineNumbers() {
        return lineNumbers;
    }

    public Set<String> getChangingWords() {
        return changingWords;
    }

}