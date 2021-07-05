package com.categorizer.e_book;


public class FilePractice {

	
	String delimiters = "\\s|\\t|\\n|,|;|\\.|\\?|!|-|:|@|\\[|\\]|\\(|\\)|\\{|\\}|_|\\*|/";
	String sample = " ";
	
	public static void main(String[] args) {
		String sampleText = "Run, Girl (If You Can) Chapter 1 - Hit and Run";

		FilePractice practice = new FilePractice();
		practice.SplitText(sampleText);
	}

	public void SplitText(String toSplit) {
		String words[] = toSplit.split(delimiters);
		
		for(int x = 0; x < words.length; x++) {
			if(!(words[x].compareTo("") == 0)) {
				System.out.println(words[x] + " ");
			}
		}
	}
	
	public String getPath() {
		return System.getProperty("file.seperator") + "";
	}
	
	public void fileHandling() {
	}
}
