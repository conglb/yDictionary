package sample;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

import readdict.Stardict;

import static java.lang.System.*;

public class Dictionary {
    private static final String PATHTONEWWORD = "wordlist.txt";
    private static final String PATHTORECENTLYWORD = "recently.txt";
	public ArrayList<String> wordslist = new ArrayList<String>();
	public ArrayList<String> recentlist = new ArrayList<>();
	public HashMap<String, String> newwords = new HashMap<>();
	private Stardict stardict = new Stardict();

    public Dictionary() {
        // init wordlist
        try {
            stardict.loadDictionary("oxdict/en_vi.ifo", "oxdict/en_vi.idx",
                    "oxdict/en_vi.dict");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        stardict.mIndex.export(wordslist);
        // init newword, deleteword list
        try {
            File inputFile = new File(PATHTONEWWORD);
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                String type = bufferedReader.readLine(), word, mean;
                switch (type) {
                    case "1":
                        word = bufferedReader.readLine();
                        mean = bufferedReader.readLine();
                        newwords.put(word, mean);
                        break;
                    case "2":
                        word = bufferedReader.readLine();
                        wordslist.remove(word);
                        break;
                    case "3":
                        word = bufferedReader.readLine();
                        mean = bufferedReader.readLine();
                        if (newwords.containsKey(word)) {
                            newwords.replace(word, mean);
                        } else newwords.put(word, mean);
                        break;
                    default:
                        break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // init recently list
        new Thread() {
            public void run() {
                try {
                    File inputFile = new File(PATHTORECENTLYWORD);
                    FileReader fileReader = new FileReader(inputFile);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    while (bufferedReader.ready()) {
                        String word = bufferedReader.readLine();
                        recentlist.add(0, word);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void addRecentlyWord(String word) {
        new Thread() {
            public void run() {
                try {
                    File outputFile = new File(PATHTORECENTLYWORD);
                    FileWriter fileWriter = new FileWriter(outputFile, true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    PrintWriter printWriter = new PrintWriter(bufferedWriter);
                    printWriter.println(word);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                recentlist.add(word);
            }
        }.start();
    }

    public String lookupWord(String word) throws IOException{
        if (newwords.containsKey(word)) {
            return newwords.get(word);
        }
        return stardict.lookupWord(word);

    }

    public void addWord(String word, String meaning) {
        new Thread() {
            public void run() {
                wordslist.add(word);
                newwords.put(word, meaning);
                try {
                    File outputFile = new File(PATHTONEWWORD);
                    FileWriter fileWriter = new FileWriter(outputFile, true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    PrintWriter printWriter = new PrintWriter(bufferedWriter);
                    printWriter.println("1");
                    printWriter.println(word);
                    printWriter.println(meaning);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void deleteWord(String word) {
        new Thread() {
            public void run() {
                wordslist.remove(word);
                try {
                    File outputFile = new File(PATHTONEWWORD);
                    FileWriter fileWriter = new FileWriter(outputFile, true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    PrintWriter printWriter = new PrintWriter(bufferedWriter);
                    printWriter.println("2");
                    printWriter.println(word);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    public void modifyWord(String word, String meaning) {
        new Thread() {
            public void run() {
                if (newwords.containsKey(word)) {
                    newwords.replace(word, meaning);
                } else newwords.put(word, meaning);
                try {
                    File outputFile = new File(PATHTONEWWORD);
                    FileWriter fileWriter = new FileWriter(outputFile, true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    PrintWriter printWriter = new PrintWriter(bufferedWriter);
                    printWriter.println("3");
                    printWriter.println(word);
                    printWriter.println(meaning);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void run() throws  IOException{
        Scanner scanner = new Scanner(System.in);
        String word, meaning;
        while (true) {
            int type = scanner.nextInt();
            switch (type)
            {
                case 1:
                    word = scanner.next();
                    System.out.println(lookupWord(word));
                case 2:
                    word = scanner.next();
                    meaning = scanner.next();
                    addWord(word, meaning);
                case 3:
                    word = scanner.next();
                    deleteWord(word);
                case 4:
                    word = scanner.next();
                    meaning = scanner.nextLine();
                    modifyWord(word, meaning);
            }
        }
    }
}
