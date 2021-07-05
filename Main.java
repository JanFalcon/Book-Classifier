package com.categorizer.e_book;

import java.awt.Color;
import java.text.DecimalFormat;

public class Main implements Runnable{
	
	private final double UPDATE_CAP = 1.0 / 60.0;
	boolean running = false;
	
	private Thread thread;
	private Window window;
	private ProcessWindow processWindow;
	private TrainingWindow trainingWindow;
	DecimalFormat df = new DecimalFormat("####0.00");
	
	protected int renderCounter = 0;
	protected int counter = 0;
	
	float estimatedTimeValue = 0.0f;
	protected int xCounter = 0;
//	protected int yCounter = 0;

	double before = 0;
	double after = 0;
	
	int fps = 0;
	
	private boolean categorize = false;
	private boolean training = false;
	
	public static void main(String[] args) {
		new	Main();
	}
	
	public Main() {
		trainingWindow = new TrainingWindow(this);
		processWindow = new ProcessWindow(this);
		window = new Window(this);
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		try {
			thread.join();
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
	}
	
	public void run() {
		boolean render = false;
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0f;
		double unprocessedTime = 0;
		double passedTime = 0;
		double frameTime = 0;
		
		int frames = 0;
		fps = 0;
		
		while(running) {
			render = false;
			firstTime = System.nanoTime() / 1000000000.0f;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			frameTime += passedTime;
			
			while(unprocessedTime >= UPDATE_CAP) {
				render = true;
				//TODO: Update
				if(frameTime >= 1) {
					frameTime = 0;
					fps = frames;
					frames = 0;
//					System.out.println("FPS : " + fps);
					window.getFPSLabel().setText("FPS : " + fps);
					window.getCounterLabel().setText("Timer : " + ++counter);
				}
				Update();
				unprocessedTime -= UPDATE_CAP;
			}
			
			if(render) {
				//TODO: Render
				Render();
				frames++;
			}
		}
	}
	
	private void Update() {
		before = System.currentTimeMillis();
		if(categorize) {
			//Process The Words
			if(xCounter < window.getWord().length) {
				processWindow.getWordCounter().setText("Word Count : " + xCounter + " / " + window.getWord().length);
				processWindow.getEstimatedTime().setText(ComputeEstimatedTime(xCounter, (int)estimatedTimeValue));
				processWindow.getWordCounterTextField().setText(df.format(WordPercentage(xCounter, window.getWord().length)) + "% completed");
				processWindow.getwordCounterSlider().setValue((int)WordPercentage(xCounter, window.getWord().length));
				if(window.getWord()[xCounter].compareTo("") != 0) {
					String genre = window.getCategorizer().ProcessTexts(window.getWord()[xCounter]);
		 			processWindow.getWordTextArea().setCaretPosition(processWindow.getWordTextArea().getText().length());
					processWindow.getStopWordsTextArea().setCaretPosition(processWindow.getStopWordsTextArea().getText().length());

					if(genre.compareToIgnoreCase("") != 0) {
						getCategorizer().TestTrainingData(genre);
						processWindow.getRecognizedTextArea().setCaretPosition(processWindow.getRecognizedTextArea().getText().length());
						processWindow.getUnrecognizedTextArea().setCaretPosition(processWindow.getUnrecognizedTextArea().getText().length());
					}
				}
				
				xCounter++;
			}
			else {
				ClearTextArea();
			}
		}
		after = System.currentTimeMillis();
		
//		System.out.println(after - before);
		if(training) {
			//Training
			if(xCounter < trainingWindow.getTextArea().getText().length()) {
				
			}
		}
	}
	
	private void Render() {
		if(categorize) {
			processWindow.getWordCounter().setText("Word Count : " + xCounter + " / " + window.getWord().length);
			processWindow.getEstimatedTime().setText(ComputeEstimatedTime(xCounter, (int)estimatedTimeValue));

		}
	}
	
	float WordPercentage(int currentValue, int maxValue) {
		return ((float)(currentValue + 1)/ (float)maxValue) * 100.0f;
	}
	
	String ComputeEstimatedTime(int currentValue, int maxValue) {
		float newEstimatedTimeValue = 0.0f;
		String[] newTime;
		newEstimatedTimeValue = ((float)maxValue - (float)currentValue) / fps;
		
		if(newEstimatedTimeValue <= 0) {
			estimatedTimeValue = 0.0f;
			return "00:00";
		}
	
		newTime = df.format(newEstimatedTimeValue).split("\\.");
		return (newTime.length != 3)? newTime[0] + ":" + newTime[1] : newTime[0] + ":" + newTime[1] + ":" + newTime[2];
	}
	
	public void ClearTextArea() {
		processWindow.getWordCounter().setText("Word Count : " + xCounter + " / " + window.getWord().length);
		processWindow.getEstimatedTime().setText(ComputeEstimatedTime(xCounter, (int)estimatedTimeValue));
//		processWindow.getWordCounterTextField().setText(df.format(WordPercentage(xCounter, window.getWord().length)) + "% completed");
		processWindow.getwordCounterSlider().setValue((int)WordPercentage(xCounter, window.getWord().length));
		
		
		window.getScanButton().setBackground(Color.CYAN);
		window.getScanButton().setEnabled(true);
		categorize = false;
		xCounter = 0;
		window.getCategorizer().getGenre().clear();
		window.getCategorizer().getRepeatingWords().clear();
		for(int x = 0; x < 8; x++) {
			window.getCategorizer().setGenreValues(0);
		}
	}
	
//	Class Getters and Setters
	public Window getWindow() {
		return window;
	}
	
	public ProcessWindow getProcessWindow() {
		return processWindow;
	}

	public TrainingWindow getTrainingWindow() {
		return trainingWindow;
	}
	
	public Categorizer getCategorizer() {
		return window.getCategorizer();
	}
	
//	Variables Getters And Setters
	public void setCategorize(boolean bool) {
		categorize = bool;
	}
	
}
