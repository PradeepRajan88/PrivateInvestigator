package com.codingChallenge.PrivateInvestigator.obj;

import java.util.HashSet;
import java.util.Set;

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