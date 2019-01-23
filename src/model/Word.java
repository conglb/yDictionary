package model;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Word implements Comparable<Word>{

	private String word;

	public Word() {
		this.word = word;
    }

    public int compareTo(Word word) {
	    return word.word == this.word ? 0 : 1;
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

	public static String translate(String langFrom, String langTo, String text) throws IOException {
		// INSERT YOU URL HERE
		String urlStr = "https://script.google.com/macros/s/AKfycbyOk-UGlU1Z3gdH1ue9LV7cezo1bqdqnh8AVYC49Cq2t89_fdw/exec" +
				"?q=" + URLEncoder.encode(text, "UTF-8") +
				"&target=" + langTo +
				"&source=" + langFrom;
		URL url = new URL(urlStr);
		StringBuilder response = new StringBuilder();
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}

	public static void phatam(String langFrom, String langTo, String text) throws IOException{
		String urlStr = "http://translate.google.com/translate_tts?ie=UTF-8&"
				+ "tl=en" + "&q=" + text + "&client=tw-ob";
		URL url = new URL(urlStr);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.addRequestProperty("User-Agent", "Mozilla/5.0");
		InputStream audioSrc = con.getInputStream();
		DataInputStream read = new DataInputStream(audioSrc);
		OutputStream outputStream = new FileOutputStream(new File("res/sound/phatam.mp3"));
		byte[] buffer = new byte[1024];
		int len;
		while ((len = read.read(buffer)) > 0) {
			outputStream.write(buffer, 0, len);
		}
		outputStream.close();
	}

	public static void phatammp3() {
		MP3 mp3 = new MP3("res/sound/phatam.mp3");
		mp3.play();
	}
}

