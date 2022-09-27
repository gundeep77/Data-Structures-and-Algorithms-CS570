import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {

    final Integer[] primes;
    Map<Long, ArrayList<String>> anagramTable = new HashMap<Long, ArrayList<String>>();
    Map<Character, Integer> letterTable = new HashMap<Character, Integer>();

    public Anagrams() {
        primes = new Integer[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
                89, 97, 101 };
        buildLetterTable();
    }

    private void buildLetterTable() {
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 26; i++) {
            letterTable.put(alphabets.charAt(i), primes[i]);
        }
    }

    private void addWord(String s) throws IllegalArgumentException {
        long hash_code = myHashCode(s);
        if (s.length() == 0 || s == null)
            throw new IllegalArgumentException("Input string can't be null or empty!");
        else {
            if (anagramTable.containsKey(hash_code))
                anagramTable.get(hash_code).add(s);
            else {
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(s);
                anagramTable.put(hash_code, temp);
            }
        }
    }

    private long myHashCode(String s) {
        s = s.toLowerCase();
        long hash_code = 1;
        for (int i = 0; i < s.length(); i++) {
            hash_code *= letterTable.get(s.charAt(i));
        }
        return hash_code;
    }

    private void processFile(String s) throws IOException {
        FileInputStream fstream = new FileInputStream(s);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while ((strLine = br.readLine()) != null) {
            this.addWord(strLine);
        }
        br.close();
    }

    private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {
        ArrayList<Map.Entry<Long, ArrayList<String>>> max_entries = new ArrayList<>();
        int max = 0;
        for (Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) {
            if (entry.getValue().size() > max) {
                max_entries.clear();
                max_entries.add(entry);
                max = entry.getValue().size();
            } else if (entry.getValue().size() == max)
                max_entries.add(entry);
        }
        return max_entries;
    }

    public static void main(String[] args) {
        Anagrams a = new Anagrams();
		final long startTime = System.nanoTime();
		try {
			a.processFile ("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries ();
		long hash_code = maxEntries.get(0).getKey();
		int length = maxEntries.get(0).getValue().size();
		final long estimatedTime = System.nanoTime() - startTime ;
		final double seconds = ((double)estimatedTime/1000000000);
		System.out.println("Elapsed Time : "+ seconds);
		System.out.println("Key of max anagrams: "+ hash_code);
		System.out.println("List of max anagrams: " + maxEntries.get(0).getValue());
		System.out.println("Length of list of max anagrams : "+ length);
    }
}
