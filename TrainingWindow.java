package com.categorizer.e_book;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Hashtable;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.categorizer.ebook.EbookCategorizerMouseListener;

public class TrainingWindow implements ActionListener, ItemListener{

	
public static int WIDTH = 884, HEIGHT = 561;
	
	private JFrame frame;
	private JPanel panel;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JButton trainButton;
	private JButton clearButton;
	private JButton backButton;
	
	private JCheckBox[] checkBox;
	private ButtonGroup bg;
	
	protected Main main;
	public EbookCategorizerMouseListener eBookMouseListener;
	
	File file;
	FileWriter writeData;
	
	String[] trainingTextData;
	
	String genre = "";
	String delimiters = "\\s|\\t|\\n|,|;|\\.|\\?|!|–|:|@|\\[|\\]|\\(|\\)|\\{|\\}|_|\\*|\"";
	
	StringBuffer trainingBuffer;
	
	private Hashtable<String, String> hashTable;
	Hashtable<String, String> addedTable;
	Hashtable<String, Integer> counterTable;
	
	int sameWordsCounter = 0;
	
	boolean visible = false;
	
	double[] newDatasValue = new double[8];
	
	public TrainingWindow(Main main) {
		this.main = main;
		
//		eBookMouseListener = main.get
		
		frame = new JFrame("Training Window");
		OpenTrainingFile();
		addPanel();
		addFrame();

	}
	
	void addPanel() {
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);
		panel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 4));
		frame.add(panel);
		
		panelComponents();
	}
	
	void panelComponents() {
		textArea = new JTextArea("", 10, 10);
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		textArea.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 4));
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.WHITE);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.requestFocus();
//		textArea.setFont(font);
//		textArea.setEditable(false);
		textArea.addMouseListener(eBookMouseListener);
		
		scrollPane.setBounds(40, 40, 810, 400);
		panel.add(scrollPane);
		
		
		trainButton = new JButton("Train");
		trainButton.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 3));
		trainButton.setBackground(Color.BLACK);
		trainButton.setForeground(Color.WHITE);
		trainButton.setBounds(40, 470, 100, 50);
		trainButton.addActionListener(this);
		panel.add(trainButton);
		
		
		clearButton = new JButton("Clear");
		clearButton.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 3));
		clearButton.setBackground(Color.BLACK);
		clearButton.setForeground(Color.WHITE);
		clearButton.setBounds(150, 470, 100, 50);
		clearButton.addActionListener(this);
		panel.add(clearButton);
		
		backButton = new JButton("Back");
		backButton.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 3));
		backButton.setBackground(Color.BLACK);
		backButton.setForeground(Color.WHITE);
		backButton.setBounds(260, 470, 100, 50);
		backButton.addActionListener(this);
		panel.add(backButton);
		
		bg = new ButtonGroup();
		checkBox = new JCheckBox[8];
		for(int x = 0; x < checkBox.length; x++) {
			checkBox[x] = new JCheckBox();
			bg.add(checkBox[x]);
			
			checkBox[x].setForeground(Color.WHITE);
			checkBox[x].setBackground(Color.BLACK);
			checkBox[x].addItemListener(this);
		}
		
		checkBox[0].setBounds(370, 470, 115, 25);
		checkBox[1].setBounds(370, 495, 115, 25);
		checkBox[2].setBounds(485, 470, 115, 25);
		checkBox[3].setBounds(485, 495, 115, 25);
		checkBox[4].setBounds(600, 470, 115, 25);
		checkBox[5].setBounds(600, 495, 115, 25);
		checkBox[6].setBounds(715, 470, 115, 25);
		checkBox[7].setBounds(715, 495, 115, 25);
		
		for(int x = 0; x < checkBox.length; x++) {
			panel.add(checkBox[x]);
		}
		
		checkBox[0].setText("Romance");
		checkBox[1].setText("Fantasy");
		checkBox[2].setText("Science Fiction");
		checkBox[3].setText("Western");
		checkBox[4].setText("Thriller");
		checkBox[5].setText("Mystery");
		checkBox[6].setText("Detective");
		checkBox[7].setText("Dystopia");
		
	}
	
	void addFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setPreferredSize(new Dimension(getXInsets(), getYInsets()));
		frame.setMaximumSize(new Dimension(getXInsets(), getYInsets()));
		frame.setMinimumSize(new Dimension(getXInsets(), getYInsets()));
		frame.setLocationRelativeTo(null);

		frame.setResizable(false);
		frame.setVisible(visible);
	}

	int getXInsets() {
		return WIDTH + frame.getInsets().left + frame.getInsets().right;
	}
	
	int getYInsets() {
		return HEIGHT + frame.getInsets().top + frame.getInsets().bottom;
	}
	

	
	
	
	
	public void OpenTrainingFile() {
		file = new File(getTrainingDataFileLocation());
		try {
			if(file.createNewFile()) {
				JOptionPane.showMessageDialog(null, "Files not found at : " + getTrainingDataFileLocation() + "\nCreating new Files...");
			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Error OpeningTrainingFile");
		}
	}
	
	public void StartTraining() {
		trainingTextData = textArea.getText().split(delimiters);
		String newTrainingTextData = main.getCategorizer().RemoveStopWords(trainingTextData);
		trainingTextData = newTrainingTextData.split("\\s");
		for(int x = 0; x < trainingTextData.length; x++) {
//			TrainData(trainingTextData[x], file, true);
			NewTraining(trainingTextData[x], true);
		}
	}
	
	public void StartNewTraining() {
		
	}
	
	public void NewTraining(String _word, boolean _bool) {
		hashTable = new Hashtable<String, String>();
		addedTable = new Hashtable<String, String>();
		trainingBuffer = new StringBuffer();
		if(_word.compareTo("") != 0) {
			try {
				if(!addedTable.containsKey(_word)) {
					addedTable.put(_word, genre);
					counterTable.put(_word, ++sameWordsCounter);
					hashTable.put(_word, _word + "|" + genre + "\n");
				}
				else {
					sameWordsCounter = counterTable.get(_word);
					counterTable.put(_word, ++sameWordsCounter);
					
					ComputeNewData(_word, genre);
					ComputeNewData2(_word);
				}
			}
			catch(Exception ee) {
				JOptionPane.showMessageDialog(null, "Error Training Data");
			}
		}
	}
	
	public void ComputeNewData(String _key, String _value) {
		String[] oldDatas = addedTable.get(_key).split("\\s");
		String[] newDatas = _value.split("\\s");
		double[] oldDatasValue = new double[8];
		
		for(int x = 0; x < newDatasValue.length; x++) {
			oldDatasValue[x] = Double.parseDouble(oldDatas[x]);
			newDatasValue[x] = Double.parseDouble(newDatas[x]);
			newDatasValue[x] += oldDatasValue[x];
	
			trainingBuffer.append(newDatasValue[x] + " ");
		}
		
		addedTable.replace(_key, trainingBuffer.toString());
		trainingBuffer.delete(0, trainingBuffer.length());
	}
	
	public void ComputeNewData2(String _key) {
		String[] oldDatas = addedTable.get(_key).split("\\s");
		
		for(int x = 0; x < newDatasValue.length; x++) {
			newDatasValue[x] = Double.parseDouble(oldDatas[x]);
			newDatasValue[x] *= 0.01;
			newDatasValue[x] /= counterTable.get(_key);
			newDatasValue[x] *= 100;
			
			trainingBuffer.append(newDatasValue[x] + " ");
		}

		hashTable.put(_key, _key + "|" + trainingBuffer.toString());
		trainingBuffer.delete(0, trainingBuffer.length());

		
		System.out.println(hashTable);
	}
	
	public void TrainData2() {
		try {
			writeData = new FileWriter(file, true);
			Set<String> keys = hashTable.keySet();
			
			for(String key : keys) {
				writeData.write(hashTable.get(key) + "\n");
			}
			writeData.close();
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Error Training Data");
		}	
	}
	
	public void TrainData(String word, File file, boolean bool) {
		if(word.compareTo("") != 0) {
			try {
				writeData = new FileWriter(file, bool);
				writeData.write(word + "|" + genre + "\n");
				writeData.close();
			}
			catch(Exception ee) {
				JOptionPane.showMessageDialog(null, "Error Training Data");
			}			
		}
	}
	
	public String getTrainingDataFileLocation() {
		return System.getProperty("user.dir") + "\\TrainingData.txt";
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == trainButton) {
			StartTraining();
		}
		else if(e.getSource() == clearButton) {
			textArea.setText("");
		}
		else if(e.getSource() == backButton) {
			frame.setVisible(false);
			main.getWindow().getFrame().setVisible(true);
		}
	}

	public JFrame getFrame() {
		return frame;
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public Hashtable<String, String> getHashTable(){
		return hashTable;
	}
	
	public void itemStateChanged(ItemEvent e) {
		
		if(checkBox[0].isSelected()) {
			genre = "100 0 0 0 0 0 0 0";
		}
		else if(checkBox[1].isSelected()) {
			genre = "0 100 0 0 0 0 0 0";
		}
		else if(checkBox[2].isSelected()) {
			genre = "0 0 100 0 0 0 0 0";
		}
		else if(checkBox[3].isSelected()) {
			genre = "0 0 0 100 0 0 0 0";
		}
		else if(checkBox[4].isSelected()) {
			genre = "0 0 0 0 100 0 0 0";
		}
		else if(checkBox[5].isSelected()) {
			genre = "0 0 0 0 0 100 0 0";
		}
		else if(checkBox[6].isSelected()) {
			genre = "0 0 0 0 0 0 100 0";
		}
		else if(checkBox[7].isSelected()) {
			genre = "0 0 0 0 0 0 0 100";
		}
	}
}
