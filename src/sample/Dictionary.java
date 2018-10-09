package sample;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import readdict.Stardict;

import static java.lang.System.*;

public class Dictionary {
    private static final String PATHTODICT = "wordlist.txt";
	private String name;
	private ArrayList<Word> words = new ArrayList<Word>();
	public ArrayList<String> wordslist = new ArrayList<String>();
    private byte accurate[] = new byte[10000];

	public Dictionary()
            throws FileNotFoundException, IOException {
        File inputFile = new File(PATHTODICT);
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //File outputFile = new File(PATHTODICT);
        //FileOutputStream outputStream = new FileOutputStream(outputFile);
        while (true) {
            String w = bufferedReader.readLine();
            while (w != null) {
                Word word = new Word(w);
                words.add(word);
                w = bufferedReader.readLine();
            }
            Collections.sort(words);
            break;
        }
    }
    public Dictionary(Stardict stardict) {
	    stardict.mIndex.export(wordslist);
	    //System.out.println(wordslist.get(200));
	    /*
	    for (int i=0; i<wordslist.size(); i++) {
	        System.out.println(wordslist.get(i));
        }
        */
    }
/*
    public ArrayList<Word> hint(String tu) {
        for (Word word: words) {
            int n = word.getWord();
            int pre1 = 0;
            int pre2 = 0;
            int cur = 0;
            for (int i=0; i<n; i++) {

            }
        }
    }
*/
	public void lookUp(String tu) {
	    Word word = new Word(tu);
	    int vitri = words.indexOf(word);
	    if (vitri >= 0 && vitri < words.size()) {
	        System.out.println("Nghia cua " + tu + ": " + words.get(vitri).getMean());
        }
	}
/*
	public void themTu(String tu) {
	    Word word = new Word(tu);

    }
*/

    public void show() {
	    System.out.println(words.size());
	    for (Word word: words) {
	        System.out.println(word.getWord());
        }
    }

}
