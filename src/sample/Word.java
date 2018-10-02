package sample;


public class Word implements Comparable<Word>{
	private String sound;
	private String mean;
	private String phienam;
	private String word;

	public Word(String word) {
	    this.word = word;
    }

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public String getMean() {
		return mean;
	}

	public void setMean(String mean) {
		this.mean = mean;
	}

	public String getPhienam() {
		return phienam;
	}

	public void setPhienam(String phienam) {
		this.phienam = phienam;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int compareTo(Word other) {
		return word.compareTo(other.word);
	}

	public boolean isEquivalent(Word other) {
		return word.equals(other.word);
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Word) {
			return this.isEquivalent((Word) obj);
		}
		return false;
	}

}

