/*
 * File: WordCount.java
 * Description: This program counts all words in a file, "moby.txt".
 * 		It then lists the top 40 most counted words. If a word is
 * 		a contraction, such as "it's", the "it" and "s" are 
 * 		counted separately.
 */

import java.util.*;
import java.io.*;

public class WordCount {
	// ==== MAIN ==== //
	public static void main(String[] args) throws IOException {
		// ==== CREATE VARIABLES AND INPUT SCANNER ==== //
		// Create TreeMap to track words (key), frequency (value)
		Map<String, Integer> count = new TreeMap<String, Integer>();
		// Create a scanner object to read words from file
		Scanner in = new Scanner(new File("moby.txt"));
		// delim scanner to punctuation and spaces 
		in.useDelimiter("[\\p{Punct}\\p{Space}]+");
		
		// ==== COLLECT WORDS FROM FILE AND COUNT THEM ==== //
		while (in.hasNext()) {
			Integer total = 0;
			// Save word from file into String s, and make lowerCase 
			String s = in.next().toLowerCase();
			// Save total times word used into total
			total = count.get(s);
			// If word hasn't been used yet, save as first time: 1
			if (total == null) {total = 1;}
			// If it has appeared before, add another time to the old total
			else {total += 1;}
			// Put the key, s, and it's total value into the TreeMap 
			count.put(s, total);
		}
		
		// ==== TRANSFER WORDS INTO AN ARRAY LIST AND SORT BY VALUE ==== //
		// Save all keys into a an ArrayList
		List<String> mapKeys = new ArrayList<String>(count.keySet());
		// Save all values into an ArrayList
		List<Integer> mapValues = new ArrayList<Integer>(count.values());
		// Create a TreeSet of the values
		TreeSet<Integer> sortedSet = new TreeSet<Integer>(mapValues);
		// Create a sorted object array
		Object[] sortedArray = sortedSet.toArray();
		Arrays.sort(sortedArray, Collections.reverseOrder());
		// ==== PRINT THE 40 MOST USED WORDS ==== //
		System.out.println("Rank\tWord\tFrequency");
		System.out.println("------------------------");
		for (int i = 0 ; i < 40; i++) {
			System.out.println((i+1) + ")\t" + mapKeys.get(mapValues.
					indexOf(sortedArray[i]))+"\t"+sortedArray[i]);
		}
		
		// ==== CLOSE FILE ==== //
		in.close();
	}		
}	
