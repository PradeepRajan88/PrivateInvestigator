package com.codingChallenge.PrivateInvestigator;

import com.codingChallenge.PrivateInvestigator.logic.SimilarityLogic;
import com.codingChallenge.PrivateInvestigator.obj.SimilarGroup;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static final String INPUT_FILE_NAME = "./inputFile.txt";
    public static final String OUTPUT_FILE_NAME = "./outputFile.txt";

    public static void main(String[] args) {

        try {
            List<String> lines = readInputFile();
            Map<String, SimilarGroup> similarGroupMap = SimilarityLogic.findSimilar(lines);
            List<String> outputLines = SimilarityLogic.generateOutputLines(similarGroupMap, lines);
            writeOutputFile(outputLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static List<String> readInputFile() throws FileNotFoundException {

        InputStream is = new FileInputStream(INPUT_FILE_NAME);
        if (is == null) return null;
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);
        return reader.lines().collect(Collectors.toList());
    }


    static void writeOutputFile(List<String> outputLines) throws IOException {
        FileWriter writer = new FileWriter(OUTPUT_FILE_NAME);
        for (String str : outputLines) {
            writer.write(str + System.lineSeparator());
            System.out.println(str);
        }
        writer.close();
    }
}
