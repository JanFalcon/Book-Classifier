package com.categorizer.e_book;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EbookGUI implements ActionListener{

	protected JPanel panel;
	
	protected Window window;
	
	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	public EbookGUI(Window window) {
		
		this.window = window;
		
		setPanel();
	}
	
	
	void setPanel() {
		panel = new JPanel();
//		window.getFrame().add(panel);
		panel.setBackground(Color.GREEN);
		panel.setLayout(null);
		panel.setFocusable(true);
		
		textArea = new JTextArea(10, 10);
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		textArea.setBorder(javax.swing.BorderFactory.createLineBorder(Color.GRAY, 3));
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.WHITE);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.requestFocus();
		
		panel.add(scrollPane);
		scrollPane.setBounds(40, 40, 400, 520);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}


}
