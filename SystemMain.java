package com.categorizer.e_book;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;

public class SystemMain {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SystemMain window = new SystemMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SystemMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 911, 541);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 250, 250));
		panel.setBounds(0, 0, 895, 67);
		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("E-BOOK CATEGORIZER");
		label.setIcon(new ImageIcon("C:\\Users\\Irish\\eclipse-workspace\\CategorizerProject\\icons8-book-50.png"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(204, 204, 51));
		label.setFont(new Font("Meiryo", Font.BOLD, 37));
		label.setBounds(0, 11, 895, 57);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(0, 67, 181, 436);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(205, 92, 92));
		panel_2.setBounds(179, 67, 716, 435);
		frame.getContentPane().add(panel_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(45, 60, 460, 319);
		panel_2.add(textArea);
		
		JButton btnNewButton = new JButton("Select File");
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0)  {
			
				JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			    chooser.setMultiSelectionEnabled(true);
			    int returnValue = chooser.showOpenDialog(frame);
			    
			    
			    if(returnValue == JFileChooser.APPROVE_OPTION) {
				    FileWriter fileWrite = null;
					try {
						fileWrite = new FileWriter("output.txt");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					BufferedWriter bufferedWrite = new BufferedWriter(fileWrite);
			    	File[] files = chooser.getSelectedFiles();
			    	for(int i=0; i < files.length; i++) {
							try {
								bufferedWrite.write(files[i].getName());
								bufferedWrite.newLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    	}
					try {
						bufferedWrite.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
			   } else {
				   System.out.print("No file Selectd");
			   }
			    
			//=====================================================//
			    
			Path path = Paths.get(System.getProperty("user.dir"))
						.resolve("output.txt");
			
			BufferedReader buffread = null;
			try {
				buffread = new BufferedReader(new FileReader(path.toFile()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Set<String> wordsOfArticle = new HashSet<>();
			
			String lines = null;
			
			try {
				lines = buffread.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int totalLines = 0;
			int totalWords = 0;
			
			while(lines != null) 
			{
				System.out.println("Processing Line " + totalLines + ": " + lines);
				
				if(!lines.trim().equals("")) 
				{
					String [] words = lines.split(" ");
					totalWords += words.length;
					
					for(String word : words) {
						String cleanedUpWord = word.replace("txt", "")
								.replace(".", "");
						
						
						wordsOfArticle.add(cleanedUpWord);
					}
				}
				totalLines++;
				
				try {
					lines = buffread.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			System.out.println(wordsOfArticle);
			System.out.println();
			System.out.println("How many unique words: " + wordsOfArticle.size());
			System.out.println("Total number of Lines:" + totalLines);
			System.out.println("Total number of Words:" + totalWords);
			System.out.println(); 
			    
			//==========================================================//
			   
			System.out.println("HashSet contains: " + wordsOfArticle);
			String[] array = new String[wordsOfArticle.size()];
			wordsOfArticle.toArray(array);
			
			System.out.println("Array Elements: ");
			
			for(int i=0; i<array.length; i++) {
				System.out.println(array[i]);
			}
			
			}
		});
		
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Irish\\eclipse-workspace\\CategorizerProject\\icons8-file-50.png"));
		btnNewButton.setForeground(new Color(0, 128, 128));
		btnNewButton.setBounds(526, 75, 184, 37);
		panel_2.add(btnNewButton);
		
		JButton btnSciencefiction = new JButton("Science-Fiction");
		btnSciencefiction.setHorizontalAlignment(SwingConstants.LEFT);
		btnSciencefiction.setIcon(new ImageIcon("C:\\Users\\Irish\\eclipse-workspace\\CategorizerProject\\icons8-physics-40.png"));
		btnSciencefiction.setForeground(new Color(0, 128, 128));
		btnSciencefiction.setBounds(526, 167, 184, 37);
		panel_2.add(btnSciencefiction);
		
		JButton btnRomancemystery = new JButton("Romance-Mystery");
		btnRomancemystery.setHorizontalAlignment(SwingConstants.LEFT);
		btnRomancemystery.setIcon(new ImageIcon("C:\\Users\\Irish\\eclipse-workspace\\CategorizerProject\\icons8-love-book-50.png"));
		btnRomancemystery.setForeground(new Color(0, 128, 128));
		btnRomancemystery.setBounds(526, 220, 184, 37);
		panel_2.add(btnRomancemystery);
		
		JButton btnRomancefantasy = new JButton("Romance-Fantasy");
		btnRomancefantasy.setHorizontalAlignment(SwingConstants.LEFT);
		btnRomancefantasy.setIcon(new ImageIcon("C:\\Users\\Irish\\eclipse-workspace\\CategorizerProject\\icons8-fantasy-40.png"));
		btnRomancefantasy.setForeground(new Color(0, 128, 128));
		btnRomancefantasy.setBounds(526, 268, 184, 37);
		panel_2.add(btnRomancefantasy);
		
		JButton btnComedydrama = new JButton("Comedy-Drama");
		btnComedydrama.setHorizontalAlignment(SwingConstants.LEFT);
		btnComedydrama.setIcon(new ImageIcon("C:\\Users\\Irish\\eclipse-workspace\\CategorizerProject\\icons8-theatre-mask-40.png"));
		btnComedydrama.setForeground(new Color(0, 128, 128));
		btnComedydrama.setBounds(526, 316, 184, 37);
		panel_2.add(btnComedydrama);
		
		
	}
	
	
}

