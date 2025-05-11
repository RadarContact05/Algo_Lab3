import java.util.Scanner;

public class WordCount {
    public static void main(String[] args) {

        // create a map to hold word counts, with key being the word (String) and value being the word-count (int)
        Map<String, Integer> counts = new Map<>();

        // take standard input from user
        Scanner scanner = new Scanner(System.in);

        // Loop through all items 
        while (scanner.hasNext()) {
            String key = scanner.next();           // check each input and assign it to items
            // make it not case-sensitive
            String word = key.toLowerCase().replace(".", "");      

            // use key as the word key
            if (counts.contains(word)) {
                counts.put(word, counts.get(word) + 1);         // increase count of word is already in map
            } else {
                // Otherwise, add the word to the map with an initial count of 1
                counts.put(word, 1);
            }
        }
        scanner.close();

        for (String w : counts.keys()) {
            System.out.println(w + " " + counts.get(w));
        }
    }
}