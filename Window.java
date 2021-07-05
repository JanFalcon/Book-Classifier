package com.categorizer.e_book;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//import javax.swing.SwingConstants;

public class Window implements ActionListener{
	
	public static int WIDTH = 884, HEIGHT = 561;
	
	private JFrame frame;
	private JPanel panel;
	private JLabel fpsLabel;
	private JLabel counterLabel;
	
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private Font font = new Font("TimesRoman", 2, 15);
	
	
	private JButton scanButton;
	private JButton clearButton;
	private JButton trainButton;
	
	protected Categorizer bookCategorizer;
	
//	Variables
	private String bookText = "";
	private String[] word;
	
//	Class
	private Main main;
	
	public Window(Main main) {
		this.main = main;
		setFrame();
		OpenFiles();
		main.start();
	}
	
	protected void OpenFiles() {
		bookCategorizer = new Categorizer(main, this);
	}
	
	protected void setFrame(){
		frame = new JFrame();	
		Components();
	}

	protected void Components() {
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		panel.setLayout(null);
		frame.add(panel);
		
		textArea = new JTextArea("Enter E-BOOK Here", 10, 10);
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		textArea.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 4));
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.WHITE);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.requestFocus();
		textArea.setFont(font);
		
		scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
		
		scrollPane.setBounds(40, 40, 810, 400);
		panel.add(scrollPane);
		
		fpsLabel = new JLabel("Update :");
		fpsLabel.setForeground(Color.WHITE);
		fpsLabel.setBounds(750, 455, 100, 50);
		panel.add(fpsLabel);
		
		counterLabel = new JLabel("Timer : ");
		counterLabel.setForeground(Color.WHITE);
		counterLabel.setBounds(750, 485, 100, 50);
		panel.add(counterLabel);
		
		scanButton = new JButton("Scan");
		scanButton.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 3));
		scanButton.setBackground(Color.CYAN);
		scanButton.setForeground(Color.BLACK);
		scanButton.setBounds(40, 470, 100, 50);
		scanButton.addActionListener(this);
		panel.add(scanButton);
		
		clearButton = new JButton("Clear");
		clearButton.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 3));
		clearButton.setBackground(Color.BLACK);
		clearButton.setForeground(Color.WHITE);
		clearButton.setBounds(150, 470, 100, 50);
		clearButton.addActionListener(this);
		panel.add(clearButton);
		
		trainButton = new JButton("Train");
		trainButton.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 3));
		trainButton.setBackground(Color.BLACK);
		trainButton.setForeground(Color.WHITE);
		trainButton.setBounds(640, 470, 100, 50);
		trainButton.addActionListener(this);
		panel.add(trainButton);
		
		addFrame();
	}
	
	protected void addFrame() {
		frame.setTitle("E-Book Categorizer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setPreferredSize(new Dimension(getXInsets(), getYInsets()));
		frame.setMaximumSize(new Dimension(getXInsets(), getYInsets()));
		frame.setMinimumSize(new Dimension(getXInsets(), getYInsets()));
//		frame.setSize(getXInsets(), getYInsets());
		frame.setLocationRelativeTo(null);

		System.out.println(getXInsets() + " " + getYInsets());
		
		frame.setResizable(false);
//		frame.setFocusable(true);
		frame.setVisible(true);
	}
	
	int getXInsets() {
		return WIDTH + frame.getInsets().left + frame.getInsets().right;
	}
	
	int getYInsets() {
		return HEIGHT + frame.getInsets().top + frame.getInsets().bottom;
	}	
	
//	Class Getters and Setters
	public Categorizer getCategorizer() {
		return bookCategorizer;
	}
	
//	Components Getters and Setters
	public JFrame getFrame() {
		return frame;
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public JLabel getFPSLabel() {
		return fpsLabel;
	}
	
	public JLabel getCounterLabel() {
		return counterLabel;
	}
	
	public JButton getScanButton() {
		return scanButton;
	}
	
	
//	Variable Getters and Setters

	public String getStringText() {
		return bookText;
	}

	public String[] getWord() {
		return word;
	}
	
//	LISTENERS
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == scanButton) {
			scanButton.setBackground(Color.RED);
			scanButton.setEnabled(false);
//			frame.setVisible(false);
			main.getProcessWindow().getFrame().setVisible(true);
			word = bookCategorizer.TokenizeString(textArea.getText().toString());
			main.estimatedTimeValue = word.length;
			main.setCategorize(true);
			
		}
		else if(e.getSource() == clearButton) {
			main.ClearTextArea();
			main.getProcessWindow().Clear();
			textArea.setText("Enter E-BOOK Here");
		}
		else if(e.getSource() == trainButton) {
			main.getTrainingWindow().getFrame().setVisible(true);
		}
	}
}
