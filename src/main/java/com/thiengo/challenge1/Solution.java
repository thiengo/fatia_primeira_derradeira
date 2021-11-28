/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thiengo.challenge1;

import com.thiengo.challenge1.strategies.fileloader.FileLoaderConcreteStrategyFromResourceImpl;
import com.thiengo.challenge1.strategies.fileloader.FileLoaderStrategy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author tiago
 */
public class Solution {

    private char[] string;
    private int length;
    private final List<String> substrings = new LinkedList<>();

    //File Load Strategy
    private final FileLoaderStrategy fileLoader
            = new FileLoaderConcreteStrategyFromResourceImpl();

    public void solution() throws IOException {
        try (InputStreamReader isr = new InputStreamReader(fileLoader.getFileAsInputStream("input")
                .orElseThrow(()
                        -> new IllegalArgumentException("File is not found")));
                BufferedReader br = new BufferedReader(isr);) {

            //Read parameters
            String line;
            while ((line = br.readLine()) != null) {
                //Chose where store data
                if (string == null) {
                    //store string to be parsed
                    string = line.replaceAll("[^a-zA-Z]+", "").toCharArray();
                } else {
                    //store substring lenght
                    length = Integer.valueOf(line);
                }
            }

            //explained by message
            if (string.length < length) {
                throw new RuntimeException("The string length is less than the "
                        + "substring length.");
            }

            //explained by message
            if (length < 1 || length > 1000) {
                throw new RuntimeException("The substring length must be greater "
                        + "than 0 and less or equal than 1000.");
            }

            //put substring in a list
            for (int p = 0; p <= (string.length - length); p++) {
                char[] temp = new char[length];
                for (int i = 0; i < length; i++) {
                    temp[i] = string[p + i];
                }
                substrings.add(String.valueOf(temp));
            }

            //Sort with library Collections
            //May be a merge sort 
            Collections.sort(substrings);

            //Print result
            System.out.println(substrings.get(0));
            System.out.println(substrings.get(substrings.size() - 1));
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Solution solution = new Solution();
        solution.solution();
    }

}
