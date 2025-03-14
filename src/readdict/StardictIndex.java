package readdict;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StardictIndex {

    private Map<Character, ArrayList<StarDictIndexEntry>> mMap;

    public StardictIndex() {
        mMap = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            mMap.put(c, new ArrayList<>());
        }
    }

    public void export(ArrayList<String> target) {
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i=0; i<mMap.get(c).size(); i++) {
                //if (!(mMap.get(c).get(i).mWord.contains(" ")))
                    target.add(mMap.get(c).get(i).mWord);
            }
        }
    }

    public void addToIndex(String word, int offset, int length) {
        word = word.toLowerCase();
        Character firstCharacter = word.charAt(0);
        if (Character.isAlphabetic(firstCharacter)) {
            ArrayList<StarDictIndexEntry> list = mMap.get(firstCharacter);
            StarDictIndexEntry entry = new StarDictIndexEntry(word, offset,
                    length);
            list.add(entry);
        }
    }

    public StarDictIndexEntry lookupWord(String word) {
        word = word.toLowerCase();
        StarDictIndexEntry needle = new StarDictIndexEntry(word, 0, 0);
        Character firstCharacter = word.charAt(0);
        ArrayList<StarDictIndexEntry> list = mMap.get(firstCharacter);
        int index = Collections.binarySearch(list, needle);
        if (index < 0) {
            return null;
        }
        return list.get(index);
    }


    /**
     *
     */
    public static class StarDictIndexEntry implements
            Comparable<StarDictIndexEntry> {

        public StarDictIndexEntry(String word, int offset, int length) {
            this.mWord = word;
            this.mOffset = offset;
            this.mLength = length;
        }

        public final String mWord;
        public final int mOffset;
        public final int mLength;

        @Override
        public int compareTo(StarDictIndexEntry o) {
            return this.mWord.compareTo(o.mWord);
        }
    }
}
