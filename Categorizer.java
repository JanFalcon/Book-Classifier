package com.categorizer.e_book;

import java.io.File;
import java.util.ArrayList;
//import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Categorizer {
	
//	Files
	private File romanceFile;
	private File fantasyFile;
	private File scienceFictionFile;
	private File WesternFile;
	private File thrillerFile;
	private File mysteryFile;
	private File detectiveFile;
	private File dystopiaFile;
	
//	Training Files
	private File stopWordsFile;
	private File trainingFile;
	private File textDataFile;
	private File digitDataFile;
	private File textDigitDataFile;
	
//	File Scanners || File Readers
	private Scanner readStopWordsFile;
	private Scanner readTrainingDataFile;
	
//	File Writers
//	private FileWriter writeStopWordsFile;
	
//	Variables
	String delimiters = "\\s|\\t|\\n|,|;|\\.|\\?|!|–|:|@|\\[|\\]|\\(|\\)|\\{|\\}|_|\\*|\"";
	String[] word;
	
	
	public String removedStopWords = "";
	
	protected Window window;
	protected Main main;
	
	private ArrayList<String> genre = new ArrayList<String>();
	private ArrayList<String> repeatingWords = new ArrayList<String>();
	private String[] genres = new String[8];
	
	private float[] genreValues = {0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f};
	private float[] newGenreValues = {0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f};
	
	public Categorizer(Main main, Window window) {
		this.main = main;
		this.window = window;
		OpenFiles();
	}
	
	public String[] TokenizeString(String text) {
		word = text.split(delimiters);
		return word;
	}
	
	public String RemoveStopWords(String[] _word) {
		removedStopWords = "";
		try {
			readStopWordsFile = new Scanner(stopWordsFile);
			boolean stop = false;
			for(int x = 0; x < _word.length; x++) {
				stop = false;
				while(readStopWordsFile.hasNext()) {
					String stopWord = readStopWordsFile.next();
					if(_word[x].compareToIgnoreCase(stopWord) == 0) {
						stop = true;
						break;
					}
				}
				if(!stop) {
					removedStopWords += _word[x] + " ";
				}
			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Error Categorizer -> RemoveStopWords()");
		}
		finally {
			readStopWordsFile.close();
		}
		return removedStopWords;
	}
	
	public String ProcessTexts(String _word) {
		boolean stop = false;
		try {
			readStopWordsFile = new Scanner(stopWordsFile);
			while(readStopWordsFile.hasNext()) {
				String stopWord = readStopWordsFile.next();
				stop = false;
				if(_word.compareToIgnoreCase(stopWord) == 0) {
					main.getProcessWindow().getStopWordsTextArea().append(_word + "\n");
					stop = true;
					break;
				}
			}
			if(_word.compareTo("") != 0 && !stop) {
				main.getProcessWindow().getWordTextArea().append(_word + "\n");
			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Error Removing Stop Words");
		}
		finally {
			readStopWordsFile.close();
		}
		if(!stop) {
			return _word;
		}
		else {
			return "";
		}
	}
	

	public void TestTrainingData(String _word) {
		boolean recognized = false;
		try {
			readTrainingDataFile = new Scanner(trainingFile);
			String newWord = "";
			while(readTrainingDataFile.hasNext()) {
				newWord = readTrainingDataFile.nextLine();
				genres = newWord.split("\\s");
				if(_word.compareToIgnoreCase(genres[0]) == 0){
					recognized = true;
					break;
				}
			}
			if(_word.compareToIgnoreCase(genres[0]) == 0 && recognized) {
				genre.add(newWord);
				main.getProcessWindow().getRecognizedTextArea().append(_word + "\n");
			}
			else {
				main.getProcessWindow().getUnrecognizedTextArea().append(_word + "\n");
			}		
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Error C -> TesTrainingData()");
		}
		finally {
			readTrainingDataFile.close();
		}
		
		//ADJUST SLIDERS HERE
		if(recognized) {
			PrepareGenres(_word);
			recognized = false;			
		}
	}
	
	public void PrepareGenres(String _word) {
		boolean enter = true;
		for(int x = 0; x < repeatingWords.size(); x++) {
			String[] _genre = repeatingWords.get(x).split("\\s");
			if(_word.compareToIgnoreCase(_genre[0]) == 0) {
				int _counter = Integer.parseInt(_genre[1]);
				enter = false;
				CalculateGenres(_word, _counter);
				break;
			}
		}
		if(enter) {
			GetCounterValue(_word);
		}
	}
	
	public void GetCounterValue(String _word) {
		int _counter = 0;
		try {
			readTrainingDataFile = new Scanner(trainingFile);
			while(readTrainingDataFile.hasNext()) {
				String[] _genre = readTrainingDataFile.nextLine().split("\\s");
				if(_word.compareToIgnoreCase(_genre[0]) == 0) {
					_counter++;
				}
			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Unable to Get Counter Data\nSauce : GetCounterValue()");
		}
		finally {
			readTrainingDataFile.close();
		}
		repeatingWords.add(_word + " " + _counter);
		CalculateGenres(_word, _counter);
	}
	
	public void CalculateGenres(String _word, int _counter) {
		float[] _genreValues = {0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f};
		for(int x = 0; x < genreValues.length; x++) {
			genreValues[x] = 0f;
		}
		try {
			readTrainingDataFile = new Scanner(trainingFile);
			while(readTrainingDataFile.hasNext()) {
				String[] _genre = readTrainingDataFile.nextLine().split("\\s");
				if(_word.compareToIgnoreCase(_genre[0]) == 0) {
					for(int x = 0; x < newGenreValues.length; x++) {
						_genreValues[x] += Integer.parseInt(_genre[x + 1]);
					}
				}
			}
			for(int x = 0; x < _genreValues.length; x++) {
//				_genreValues[x] = newGenreValues[x];
				_genreValues[x] *= 0.01f;
				_genreValues[x] /= _counter;
				newGenreValues[x] += _genreValues[x];
			}
			
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Unable to Get Genre\nSauce : CalculateGenres()" + ee);
			ee.printStackTrace();
		}
		finally {
			readTrainingDataFile.close();
		}
		
		for(int x = 0; x < genreValues.length; x++) {
			genreValues[x] = newGenreValues[x];
			genreValues[x] /= genre.size();
			genreValues[x] *= 100f;
			genreValues[x] = Math.round(genreValues[x]);
		}
		
		main.getProcessWindow().getRomanceSlider().setValue((int)genreValues[0]);
		main.getProcessWindow().getFantasySlider().setValue((int)genreValues[1]);
		main.getProcessWindow().getScienceFictionSlider().setValue((int)genreValues[2]);
		main.getProcessWindow().getWesternSlider().setValue((int)genreValues[3]);
		main.getProcessWindow().getThrillerSlider().setValue((int)genreValues[4]);
		main.getProcessWindow().getMysterySlider().setValue((int)genreValues[5]);
		main.getProcessWindow().getDetectiveSlider().setValue((int)genreValues[6]);
		main.getProcessWindow().getDystopiaSlider().setValue((int)genreValues[7]);

		main.getProcessWindow().getRomanceTextField().setText(genreValues[0] + "%");
		main.getProcessWindow().getFantasyTextField().setText(genreValues[1] + "%");
		main.getProcessWindow().getScienceFictionTextField().setText(genreValues[2] + "%");
		main.getProcessWindow().getWesternTextField().setText(genreValues[3] + "%");
		main.getProcessWindow().getThrillerTextField().setText(genreValues[4] + "%");
		main.getProcessWindow().getMysteryTextField().setText(genreValues[5] + "%");
		main.getProcessWindow().getDetectiveTextField().setText(genreValues[6] + "%");
		main.getProcessWindow().getDystopiaTextField().setText(genreValues[7] + "%");
		
	}
	
	//	Genres
	//	Romance
	//	Fantasy
	//	Science Fiction
	//	Western
	//	Thriller
	//	Mystery
	//	Detective
	//	Dystopia
	
	protected void OpenFiles(){
		OpenStopWordsFile();
		OpenTrainingFile();
		
//		OpenTextDataFile();
//		OpenDigitDataFile();
//		OpenTextDigitDataFile();
		
//		OpenRomanceCorpus();
//		OpenFantasyCorpus();
//		OpenScienceFictionCorpus();
//		OpenWesternCorpus();
//		OpenThrillerCorpus();
//		OpenMysteryCorpus();
//		OpenDetectiveCorpus();
//		OpenDystopiaCorpus();
	}
	
	protected void OpenTrainingFile() {
		try {
			trainingFile = new File(getTrainingFileLocation());
			
			if(trainingFile.createNewFile()) {
				JOptionPane.showMessageDialog(null, "Files not found at : " + getTrainingFileLocation() + "\nCreating new Files...");
			}
//			else {
//				JOptionPane.showMessageDialog(null, "Files found at : " + getTrainingFileLocation() + "\nFiles allocated Succesfully...");
//			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Error Opening File\n Error 1");
		}
	}
	
	protected void OpenStopWordsFile() {
		try {
			stopWordsFile = new File(getStopWordsFileLocation());
			
			if(stopWordsFile.createNewFile()) {
				JOptionPane.showMessageDialog(null, "Files not found at : " + getStopWordsFileLocation() + "\nCreating new Files...");
			}
//			else {
//				JOptionPane.showMessageDialog(null, "Files found at : " + getStopWordsFileLocation() + "\nFiles allocated Succesfully...");
//			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Error Opening File\n Error 1");
		}
	}
	
	protected void OpenTextDataFile() {
		try {
			textDataFile = new File(getTextDataFileLocation());
			
			if(textDataFile.createNewFile()) {
				JOptionPane.showMessageDialog(null, "Files not found at : " + getTextDataFileLocation() + "\nCreating new Files...");
			}
//			else {
//				JOptionPane.showMessageDialog(null, "Files found at : " + getTextDataFileLocation() + "\nFiles allocated Succesfully...");
//			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Error Opening File\n Error 1");
		}
	}
	
	protected void OpenDigitDataFile() {
		try {
			digitDataFile = new File(getDigitDataFileLocation());
		
			if(digitDataFile.createNewFile()) {
				JOptionPane.showMessageDialog(null, "Files not found at : " + getDigitDataFileLocation() + "\nCreating new Files...");
			}
//			else {
//				JOptionPane.showMessageDialog(null, "Files found at : " + getDigitDataFileLocation() + "\nFiles allocated Succesfully...");
//			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Error Opening File\n Error 1");
		}
	}
	
	protected void OpenTextDigitDataFile() {
		try {
			textDigitDataFile = new File(getTextDigitDataFileLocation());
			
			if(textDigitDataFile.createNewFile()) {
				JOptionPane.showMessageDialog(null, "Files not found at : " + getTextDigitDataFileLocation() + "\nCreating new Files...");
			}
//			else {
//				JOptionPane.showMessageDialog(null, "Files found at : " + getTextDigitDataFileLocation() + "\nFiles allocated Succesfully...");
//			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Error Opening File\n Error 1");
		}
	}
	
	protected void OpenRomanceCorpus() {
		try {
			romanceFile = new File(getRomanceFileLocation());
			
			if(romanceFile.createNewFile()) {
				JOptionPane.showMessageDialog(null, "Files not found at : " + getRomanceFileLocation() + "\nCreating new Files...");
			}
//			else {
//				JOptionPane.showMessageDialog(null, "Files found at : " + getRomanceFileLocation() + "\nFiles allocated Succesfully...");
//			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Error Opening File\n Error 2");
		}
	}
	
	protected void OpenFantasyCorpus() {
		try {
			fantasyFile = new File(getFantasyFileLocation());
			
			if(fantasyFile.createNewFile()) {
				JOptionPane.showMessageDialog(null, "Files not found at : " + getFantasyFileLocation() + "\nCreating new Files...");
			}
//			else {
//				JOptionPane.showMessageDialog(null, "Files found at : " + getFantasyFileLocation() + "\nFiles allocated Succesfully...");
//			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Error Opening File\n Error 3");
		}
	}
	
	protected void OpenScienceFictionCorpus() {
		try {
			scienceFictionFile = new File(getScience_FictionFileLocation());
			
			if(scienceFictionFile.createNewFile()) {
				JOptionPane.showMessageDialog(null, "Files not found at : " + getScience_FictionFileLocation() + "\nCreating new Files...");
			}
//			else {
//				JOptionPane.showMessageDialog(null, "Files found at : " + getScience_FictionFileLocation() + "\nFiles allocated Succesfully...");
//			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Error Opening File\n Error 4");
		}
	}
	
	protected void OpenWesternCorpus() {
		try {
			WesternFile = new File(getWesternFileLocation());
			
			if(WesternFile.createNewFile()) {
				JOptionPane.showMessageDialog(null, "Files not found at : " + getWesternFileLocation() + "\nCreating new Files...");
			}
//			else {
//				JOptionPane.showMessageDialog(null, "Files found at : " + getWesternFileLocation() + "\nFiles allocated Succesfully...");
//			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Error Opening File\n Error 5");
		}
	}
	
	protected void OpenThrillerCorpus() {
		try {
			thrillerFile = new File(getThrillerFileLocation());
			
			if(thrillerFile.createNewFile()) {
				JOptionPane.showMessageDialog(null, "Files not found at : " + getThrillerFileLocation() + "\nCreating new Files...");
			}
//			else {
//				JOptionPane.showMessageDialog(null, "Files found at : " + getThrillerFileLocation() + "\nFiles allocated Succesfully...");
//			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Error Opening File\n Error 6");
		}
	}
	
	protected void OpenMysteryCorpus() {
		try {
			mysteryFile = new File(getMysteryFileLocation());
			
			if(mysteryFile.createNewFile()) {
				JOptionPane.showMessageDialog(null, "Files not found at : " + getMysteryFileLocation() + "\nCreating new Files...");
			}
//			else {
//				JOptionPane.showMessageDialog(null, "Files found at : " + getMysteryFileLocation() + "\nFiles allocated Succesfully...");
//			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Error Opening File\n Error 7");
		}
	}
	
	protected void OpenDetectiveCorpus() {
		try {
			detectiveFile = new File(getDetectiveFileLocation());
			
			if(detectiveFile.createNewFile()) {
				JOptionPane.showMessageDialog(null, "Files not found at : " + getDetectiveFileLocation() + "\nCreating new Files...");
			}
//			else {
//				JOptionPane.showMessageDialog(null, "Files found at : " + getDetectiveFileLocation() + "\nFiles allocated Succesfully...");
//			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Error Opening File\n Error 8");
		}
	}
	
	protected void OpenDystopiaCorpus() {
		try {
			dystopiaFile = new File(getDystopiaFileLocation());
			
			if(dystopiaFile.createNewFile()) {
				JOptionPane.showMessageDialog(null, "Files not found at : " + getDystopiaFileLocation() + "\nCreating new Files...");
			}
//			else {
//				JOptionPane.showMessageDialog(null, "Files found at : " + getDystopiaFileLocation() + "\nFiles allocated Succesfully...");
//			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Error Opening File\n Error 9");
		}
	}
	
	
//	Variable Getters and Setters
	
	public ArrayList<String> getGenre(){
		return genre;
	}
	
	public ArrayList<String> getRepeatingWords(){
		return repeatingWords;
	}
	
	public void setGenreValues(int newValue) {
		for(int x = 0; x < 8; x++) {
			genreValues[x] = newValue;
			newGenreValues[x] = newValue;
		}
	}
	
//	FILE GETTERS
	
	public String getTrainingFileLocation() {
		return System.getProperty("user.dir") + "\\TrainingData.txt";
	}
	
	public String getStopWordsFileLocation() {
		return System.getProperty("user.dir") + "\\StopWords.txt";
	}
	
	public String getTextDataFileLocation() {
		return System.getProperty("user.dir") + "\\TextData.txt";
	}
	
	
	public String getDigitDataFileLocation() {
		return System.getProperty("user.dir") + "\\DigitData.txt";
	}
	
	public String getTextDigitDataFileLocation() {
		return System.getProperty("user.dir") + "\\TextDigitData.txt";
	}
	
//	FILE CORPUS GETTERS
	
	public String getRomanceFileLocation() {
		return System.getProperty("user.dir") + "\\Romance.txt";
	}
	
	public String getFantasyFileLocation() {
		return System.getProperty("user.dir") + "\\Fantasy.txt";
	}
	
	public String getScience_FictionFileLocation() {
		return System.getProperty("user.dir") + "\\Science_Fiction.txt";
	}
	
	public String getWesternFileLocation() {
		return System.getProperty("user.dir") + "\\Western.txt";
	}
	
	public String getThrillerFileLocation() {
		return System.getProperty("user.dir") + "\\Thriller.txt";
	}
	
	public String getMysteryFileLocation() {
		return System.getProperty("user.dir") + "\\Mystery.txt";
	}
	
	public String getDetectiveFileLocation() {
		return System.getProperty("user.dir") + "\\Detective.txt";
	}
	
	public String getDystopiaFileLocation() {
		return System.getProperty("user.dir") + "\\Dystopia.txt";
	}
	
}
