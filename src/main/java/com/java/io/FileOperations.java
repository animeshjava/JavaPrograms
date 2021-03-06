package com.java.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <b>Problem description:</b> From a given file.<br>
 * 1. List down all the words and number of their occurrences.<br>
 * 2. List the top most repeated words upto given limit.<br>
 * 2.a. If two words have same number of occurrences, arrange in word sorting
 * order
 * 
 * @author Paramesh
 *
 */
public class FileOperations {

    public static Map<String, Integer> listWordsWithOccurance(String filePath) throws FileNotFoundException, IOException {

        File file = new File(filePath);
        String inputLine = null;
        Map<String, Integer> wordsMap = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {

            while ((inputLine = bufferedReader.readLine()) != null) {
                String[] words = inputLine.split("[ \n\t\r.,;:!?(){}]");

                for (String word : words) {
                    // .toLowerCase for Case insensitive result.
                    String key = word.toLowerCase();
                    if (key.length() > 0) {
                        if (wordsMap.get(key) == null) {
                            wordsMap.put(key, 1);
                        } else {
                            int value = wordsMap.get(key);
                            value++;
                            wordsMap.put(key, value);
                        }
                    }
                }
            }
        } catch (Exception error) {
           throw error;
        }
        return wordsMap;
    }

    /**
     * @param map
     *            = All Words in map
     * @param noOfTopElements
     *            = How many top elements you want to print? If n=1 it will
     *            print highest occurrence word. If n=2 it will print top 2
     *            highest occurrence words.
     * @return list of Strings
     */
    public static List<String> filterMaxOccurance(Map<String, Integer> map, int noOfTopElements) {

        List<Entry<String, Integer>> entryList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            entryList.add(entry);
        }

        Collections.sort(entryList, new Comparator<Entry<String, Integer>>() {

            @Override
            public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
                // For ascending order
                // int compare = Integer.compare(entry1.getValue(),
                // entry2.getValue());
                // For descending order
                int compare = Integer.compare(entry2.getValue(), entry1.getValue());
                return compare != 0 ? compare : entry1.getKey().compareTo(entry2.getKey());
            }

        });

        List<String> list = new ArrayList<>();
        for (Entry<String, Integer> entry : entryList.subList(0, noOfTopElements)) {
            list.add(entry.getKey() + " : " + entry.getValue());
        }
        return list;
    }
}