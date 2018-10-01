package sample;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class Dictionary {
    private static final String PATHTODICT = "wordlist.txt";
	private String name;
	private ArrayList<Word> words;

	public Dictionary()
            throws FileNotFoundException, IOException{
        File inputFile = new File(PATHTODICT);
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        File outputFile = new File(PATHTODICT);
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        while (true) {
            String w = bufferedReader.readLine();
            Word word = new Word(w);
            words.add(word);
            if (w == null) break;
        }
    }

	public void search(String word) {
		
	}

	public void themTu(String tu) {
	    Word word = new Word(tu);

    }

}
