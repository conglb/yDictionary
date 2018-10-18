package sample;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

import readdict.Stardict;

import static java.lang.System.*;

public class Dictionary {
    private static final String PATHTODICT = "wordlist.txt";
	private String name;
	//private ArrayList<Word> words = new ArrayList<Word>();
	public ArrayList<String> wordslist = new ArrayList<String>();
	public ArrayList<String> favorlist = new ArrayList<>();
	public ArrayList<String> recentlist = new ArrayList<>();
	public HashMap<String, String> newwords = new HashMap<>();
    private byte accurate[] = new byte[10000];

	public Dictionary()
            throws FileNotFoundException, IOException {
        File inputFile = new File(PATHTODICT);
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (true) {
            String w = bufferedReader.readLine();
            while (w != null) {
                w = bufferedReader.readLine();
            }
            break;
        }
    }
    public Dictionary(Stardict stardict, String recentlyPath, String newWords) throws FileNotFoundException {
	    stardict.mIndex.export(wordslist);
	    /*
	    new Thread() {
	        public void run() {
	            try {
                    File inputFile = new File(favoritePath);
                    FileReader fileReader = new FileReader(inputFile);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String w = bufferedReader.readLine();
                    while (w != null) {
                        favorlist.add(w);
                        w = bufferedReader.readLine();
                    }
                }
                catch (IOException e) {
	                e.printStackTrace();
                }
            }
        }.start();
	    */
	    new Thread() {
	        public void run() {
	            try {
                    File inputFile = new File(recentlyPath);
                    FileReader fileReader = new FileReader(inputFile);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String w = bufferedReader.readLine();
                    while (w != null) {
                        recentlist.add(w);
                        w = bufferedReader.readLine();
                    }
                }
                catch (IOException e) {
	                e.printStackTrace();
                }
            }
        }.start();
    }

    public void addWord(String word, String meaning) {
        wordslist.add(word);
        newwords.put(word, meaning);
    }

    public void deleteWord(String word) {
	    wordslist.remove(word);
    }

}
