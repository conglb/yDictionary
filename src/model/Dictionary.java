package model;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

import readdict.Stardict;

import static java.lang.System.*;

/**
 * init and manipulate dictionary
 */
public class Dictionary {
    private static final String PATHTONEWWORD = "cache/wordlist.txt";
    private static final String PATHTORECENTLYWORD = "cache/recently.txt";
	public ArrayList<String> wordslist = new ArrayList<String>();
	public ArrayList<String> recentlist = new ArrayList<>();
	public HashMap<String, String> newwords = new HashMap<>();
	private Stardict stardict = new Stardict();

    public Dictionary() {
        /**
         * read wordlist form  oxford dictionary
         */
        try {
            stardict.loadDictionary("res/dictionary/oxdict/en_vi.ifo", "res/dictionary/oxdict/en_vi.idx",
                    "res/dictionary/oxdict/en_vi.dict");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        stardict.mIndex.export(wordslist);

        /**
         * read modify dictionary history
         */
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
                        wordslist.add(word);
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
                        } else {newwords.put(word, mean);}
                        break;
                    default:
                        break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * read recently word list in cache
         */
        // init recently list
        //new Thread() {
        //    public void run() {
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
        //    }
        //}.start();
    }

    public void addRecentlyWord(String word) {
        new Thread() {
            public void run() {
                try(FileWriter fw = new FileWriter(PATHTORECENTLYWORD, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                {
                    out.println(word);
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
                try(FileWriter fw = new FileWriter(PATHTONEWWORD, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                {
                    out.println(1);
                    out.println(word);
                    out.println(meaning);
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
                try(FileWriter fw = new FileWriter(PATHTONEWWORD, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                {
                    out.println(2);
                    out.println(word);
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
                try(FileWriter fw = new FileWriter(PATHTONEWWORD, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                {
                    out.println(3);
                    out.println(word);
                    out.println(meaning);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void run() throws  IOException{
        System.out.println("1: tra tu");
        System.out.println("2: them tu");
        System.out.println("3: xoa tu");
        System.out.println("4: sua tu");
        System.out.println("5: wordlist");
        Scanner scanner = new Scanner(System.in);
        String word, meaning;
        while (true) {
            int type = scanner.nextInt();
            switch (type)
            {
                case 1:
                    word = scanner.next();
                    meaning = lookupWord(word);
                    if (meaning != null) {
                        System.out.println(meaning);
                    }
                    else {
                        System.out.println("Not found!");
                    }
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
                case 5:
                    for (String iter: wordslist)
                        System.out.println(iter);
                default:
                    break;
            }
        }
    }
}
