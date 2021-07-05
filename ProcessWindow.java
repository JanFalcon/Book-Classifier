package com.categorizer.e_book;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ProcessWindow implements ActionListener, ChangeListener{

	public static int WIDTH = 834, HEIGHT = 561;
	
	boolean visible = false;
	
//	Components
	private JFrame frame;
	protected JPanel panel;
	protected JPanel panel1;
	
	JLabel romanceLabel;
	JLabel fantasyLabel;
	JLabel scienceFictionLabel;
	JLabel westernLabel;
	JLabel thrillerLabel;
	JLabel mysteryLabel;
	JLabel detectiveLabel;
	JLabel dystopiaLabel;
	
	private JSlider romanceSlider;
	private JSlider fantasySlider;
	private JSlider scienceFictionSlider;
	private JSlider westernSlider;
	private JSlider thrillerSlider;
	private JSlider mysterySlider;
	private JSlider detectiveSlider;
	private JSlider dystopiaSlider;
	
	private JTextField romanceTextField;
	private JTextField fantasyTextField;
	private JTextField scienceFictionTextField;
	private JTextField westernTextField;
	private JTextField thrillerTextField;
	private JTextField mysteryTextField;
	private JTextField detectiveTextField;
	private JTextField dystopiaTextField;
	
	private JLabel wordCounterLabel;
	
	private JSlider wordCounterSlider;
	private JTextField wordCounterTextField;
	
	protected JLabel estimateTime;
	private JLabel estimatedTime;
	
	protected JLabel wordLabel;
	protected JLabel stopWordsLabel;
	protected JLabel recognizedLabel;
	protected JLabel unrecognizedLabel;
	
	private JScrollPane wordScrollPane;
	private JTextArea wordTextArea;
	
	private JScrollPane stopWordsScrollPane;
	private JTextArea stopWordsTextArea;

	private JScrollPane recognizedScrollPane;
	private JTextArea recognizedTextArea;
	
	private JScrollPane unrecognizedScrollPane;
	private JTextArea unrecognizedTextArea;
	
	protected int xSomething = 240;
	protected int ySomething = 55;
	
	
	Font font = new Font("Arial", 1, 12);
	
//	Class
	
	protected Main main;
	
	public static void main(String[] args) {
		new ProcessWindow();
	}
	
	public ProcessWindow() {
		visible = true;
		setFrame();
	}
	
	public ProcessWindow(Main main) {
		this.main = main;
		setFrame();
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
		
		panel1 = new JPanel();
		panel1.setBackground(Color.BLACK);
		panel1.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		panel1.setLayout(null);
		panel1.setBounds(10, 12, 813, 260);
		panel.add(panel1);
		
		romanceLabel = new JLabel("Romance");
		romanceLabel.setForeground(Color.RED);
		romanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		romanceLabel.setFont(font);
		romanceLabel.setBounds(10, 8, 340, 20);
		panel1.add(romanceLabel);
		
		fantasyLabel = new JLabel("Fantasy");
		fantasyLabel.setForeground(Color.GREEN);
		fantasyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fantasyLabel.setFont(font);
		fantasyLabel.setBounds(10, 68, 340, 20);
		panel1.add(fantasyLabel);
		
		scienceFictionLabel = new JLabel("Science Fiction");
		scienceFictionLabel.setForeground(Color.CYAN);
		scienceFictionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scienceFictionLabel.setFont(font);
		scienceFictionLabel.setBounds(10, 128, 340, 20);
		panel1.add(scienceFictionLabel);
		
		westernLabel = new JLabel("Western");
		westernLabel.setForeground(Color.ORANGE);
		westernLabel.setHorizontalAlignment(SwingConstants.CENTER);
		westernLabel.setFont(font);
		westernLabel.setBounds(10, 188, 340, 20);
		panel1.add(westernLabel);
		
		thrillerLabel = new JLabel("Thriller");
		thrillerLabel.setForeground(Color.YELLOW);
		thrillerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		thrillerLabel.setFont(font);
		thrillerLabel.setBounds(WIDTH/2, 8, 340, 20);
		panel1.add(thrillerLabel);
		
		mysteryLabel = new JLabel("Mystery");
		mysteryLabel.setForeground(Color.PINK);
		mysteryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mysteryLabel.setFont(font);
		mysteryLabel.setBounds(WIDTH/2, 68, 340, 20);
		panel1.add(mysteryLabel);
		
		detectiveLabel = new JLabel("Detective");
		detectiveLabel.setForeground(Color.BLUE);
		detectiveLabel.setHorizontalAlignment(SwingConstants.CENTER);
		detectiveLabel.setFont(font);
		detectiveLabel.setBounds(WIDTH/2, 128, 340, 20);
		panel1.add(detectiveLabel);
		
		dystopiaLabel = new JLabel("Dystopia");
		dystopiaLabel.setForeground(Color.MAGENTA);
		dystopiaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dystopiaLabel.setFont(font);
		dystopiaLabel.setBounds(WIDTH/2, 188, 340, 20);
		panel1.add(dystopiaLabel);
		
//		TextArea
		romanceTextField = new JTextField();
		romanceTextField.setBackground(Color.BLACK);
		romanceTextField.setForeground(Color.WHITE);
		romanceTextField.setEditable(false);
		romanceTextField.setHorizontalAlignment(SwingConstants.CENTER);
		romanceTextField.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		romanceTextField.setBounds(340, 18, 50, 25);
		panel1.add(romanceTextField);
		
		fantasyTextField = new JTextField();
		fantasyTextField.setBackground(Color.BLACK);
		fantasyTextField.setForeground(Color.WHITE);
		fantasyTextField.setEditable(false);
		fantasyTextField.setHorizontalAlignment(SwingConstants.CENTER);
		fantasyTextField.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		fantasyTextField.setBounds(340, 78, 50, 25);
		panel1.add(fantasyTextField);
		
		scienceFictionTextField = new JTextField();
		scienceFictionTextField.setBackground(Color.BLACK);
		scienceFictionTextField.setForeground(Color.WHITE);
		scienceFictionTextField.setEditable(false);
		scienceFictionTextField.setHorizontalAlignment(SwingConstants.CENTER);
		scienceFictionTextField.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		scienceFictionTextField.setBounds(340, 138, 50, 25);
		panel1.add(scienceFictionTextField);
		
		westernTextField = new JTextField();
		westernTextField.setBackground(Color.BLACK);
		westernTextField.setForeground(Color.WHITE);
		westernTextField.setEditable(false);
		westernTextField.setHorizontalAlignment(SwingConstants.CENTER);
		westernTextField.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		westernTextField.setBounds(340, 198, 50, 25);
		panel1.add(westernTextField);
		
		thrillerTextField = new JTextField();
		thrillerTextField.setBackground(Color.BLACK);
		thrillerTextField.setForeground(Color.WHITE);
		thrillerTextField.setEditable(false);
		thrillerTextField.setHorizontalAlignment(SwingConstants.CENTER);
		thrillerTextField.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		thrillerTextField.setBounds(750, 18, 50, 25);
		panel1.add(thrillerTextField);
		
		mysteryTextField = new JTextField();
		mysteryTextField.setBackground(Color.BLACK);
		mysteryTextField.setForeground(Color.WHITE);
		mysteryTextField.setEditable(false);
		mysteryTextField.setHorizontalAlignment(SwingConstants.CENTER);
		mysteryTextField.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		mysteryTextField.setBounds(750, 78, 50, 25);
		panel1.add(mysteryTextField);
		
		detectiveTextField = new JTextField();
		detectiveTextField.setBackground(Color.BLACK);
		detectiveTextField.setForeground(Color.WHITE);
		detectiveTextField.setEditable(false);
		detectiveTextField.setHorizontalAlignment(SwingConstants.CENTER);
		detectiveTextField.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		detectiveTextField.setBounds(750, 138, 50, 25);
		panel1.add(detectiveTextField);
		
		dystopiaTextField = new JTextField();
		dystopiaTextField.setBackground(Color.BLACK);
		dystopiaTextField.setForeground(Color.WHITE);
		dystopiaTextField.setEditable(false);
		dystopiaTextField.setHorizontalAlignment(SwingConstants.CENTER);
		dystopiaTextField.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		dystopiaTextField.setBounds(750, 198, 50, 25);
		panel1.add(dystopiaTextField);
		
//		Sliders
		romanceSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		romanceSlider.setMajorTickSpacing(10);
		romanceSlider.setPaintTicks(true);
		romanceSlider.addChangeListener(this);
		romanceSlider.setBackground(Color.BLACK);
		romanceSlider.setForeground(Color.WHITE);
//		romanceSlider.setEnabled(false);
//		romanceSlider.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		romanceSlider.setBounds(5, 29, 330, 25);
		panel1.add(romanceSlider);
		
		fantasySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		fantasySlider.setMajorTickSpacing(10);
		fantasySlider.setPaintTicks(true);
		fantasySlider.addChangeListener(this);
		fantasySlider.setBackground(Color.BLACK);
		fantasySlider.setForeground(Color.WHITE);
		fantasySlider.setEnabled(false);
//		fantasySlider.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		fantasySlider.setBounds(5, 89, 330, 25);
		panel1.add(fantasySlider);
		
		scienceFictionSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		scienceFictionSlider.setMajorTickSpacing(10);
		scienceFictionSlider.setPaintTicks(true);
		scienceFictionSlider.addChangeListener(this);
		scienceFictionSlider.setBackground(Color.BLACK);
		scienceFictionSlider.setForeground(Color.WHITE);
		scienceFictionSlider.setEnabled(false);
//		scienceFictionSlider.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		scienceFictionSlider.setBounds(5, 149, 330, 25);
		panel1.add(scienceFictionSlider);
		
		westernSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		westernSlider.setMajorTickSpacing(10);
		westernSlider.setPaintTicks(true);
		westernSlider.addChangeListener(this);
		westernSlider.setBackground(Color.BLACK);
		westernSlider.setForeground(Color.WHITE);
		westernSlider.setEnabled(false);
//		westernSlider.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		westernSlider.setBounds(5, 209, 330, 25);
		panel1.add(westernSlider);
		
		thrillerSlider = new JSlider(JSlider.HORIZONTAL,0, 100, 0);
		thrillerSlider.setMajorTickSpacing(10);
		thrillerSlider.setPaintTicks(true);
		thrillerSlider.addChangeListener(this);
		thrillerSlider.setBackground(Color.BLACK);
		thrillerSlider.setForeground(Color.WHITE);
		thrillerSlider.setEnabled(false);
//		thrillerSlider.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		thrillerSlider.setBounds(415, 29, 330, 25);
		panel1.add(thrillerSlider);
		
		mysterySlider = new JSlider(JSlider.HORIZONTAL,0, 100, 0);
		mysterySlider.setMajorTickSpacing(10);
		mysterySlider.setPaintTicks(true);
		mysterySlider.addChangeListener(this);
		mysterySlider.setBackground(Color.BLACK);
		mysterySlider.setForeground(Color.WHITE);
		mysterySlider.setEnabled(false);
//		mysterySlider.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		mysterySlider.setBounds(415, 89, 330, 25);
		panel1.add(mysterySlider);
		
		detectiveSlider = new JSlider(JSlider.HORIZONTAL,0, 100, 0);
		detectiveSlider.setMajorTickSpacing(10);
		detectiveSlider.setPaintTicks(true);
		detectiveSlider.addChangeListener(this);
		detectiveSlider.setBackground(Color.BLACK);
		detectiveSlider.setForeground(Color.WHITE);
		detectiveSlider.setEnabled(false);
//		detectiveSlider.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		detectiveSlider.setBounds(415, 149, 330, 25);
		panel1.add(detectiveSlider);
		
		dystopiaSlider = new JSlider(JSlider.HORIZONTAL,0, 100, 0);
		dystopiaSlider.setMajorTickSpacing(10);
		dystopiaSlider.setPaintTicks(true);
		dystopiaSlider.addChangeListener(this);
		dystopiaSlider.setBackground(Color.BLACK);
		dystopiaSlider.setForeground(Color.WHITE);
		dystopiaSlider.setEnabled(false);
//		dystopiaSlider.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		dystopiaSlider.setBounds(415, 209, 330, 25);
		panel1.add(dystopiaSlider);
		
//		Bottom
		wordCounterLabel = new JLabel("Word Count : ");
		wordCounterLabel.setForeground(Color.WHITE);
		wordCounterLabel.setBounds(18, ySomething + 210, 300, 50);
		wordCounterLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(wordCounterLabel);
		
		wordCounterSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		wordCounterSlider.setMajorTickSpacing(10);
		wordCounterSlider.setPaintTicks(true);
		wordCounterSlider.addChangeListener(this);
		wordCounterSlider.setBackground(Color.BLACK);
		wordCounterSlider.setForeground(Color.WHITE);
		wordCounterSlider.setEnabled(false);
		wordCounterSlider.setBounds(15, ySomething + 245, 430, 50);
		panel.add(wordCounterSlider);
		
		wordCounterTextField = new JTextField();
		wordCounterTextField.setBackground(Color.BLACK);
		wordCounterTextField.setForeground(Color.WHITE);
		wordCounterTextField.setEditable(false);
		wordCounterTextField.setHorizontalAlignment(SwingConstants.LEFT);
		wordCounterTextField.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		wordCounterTextField.setBounds(453, ySomething + 260, 175, 25);
		panel.add(wordCounterTextField);

		estimateTime = new JLabel("Estimated Time of Completion");
		estimateTime.setForeground(Color.WHITE);
		estimateTime.setHorizontalAlignment(SwingConstants.CENTER);
		estimateTime.setBounds(630, ySomething + 210, 200, 50);
		panel.add(estimateTime);
		
		estimatedTime = new JLabel("00:00:00");
		estimatedTime.setForeground(Color.WHITE);
		estimatedTime.setHorizontalAlignment(SwingConstants.CENTER);
		estimatedTime.setBounds(630, ySomething + 245, 200, 50);
		panel.add(estimatedTime);
		
//		TextAreas================================
		
//		Words
		wordTextArea = new JTextArea("", 10, 10);
		wordScrollPane = new JScrollPane(wordTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		wordTextArea.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		wordTextArea.setBackground(Color.BLACK);
		wordTextArea.setForeground(Color.WHITE);
		wordTextArea.setLineWrap(true);
		wordTextArea.setWrapStyleWord(true);
		wordTextArea.setEditable(false);
//		stopWordsTextArea.setFont(font);
		
		wordScrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
		
		wordScrollPane.setBounds(15, ySomething + 295, 235, 180);
		panel.add(wordScrollPane);
		
		wordLabel = new JLabel("Words");
		wordLabel.setForeground(Color.WHITE);
		wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		wordLabel.setBounds(15, ySomething + 465, 235, 50);
		panel.add(wordLabel);
		
//		Stop Words
		stopWordsTextArea = new JTextArea("", 10, 10);
		stopWordsScrollPane = new JScrollPane(stopWordsTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		stopWordsTextArea.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		stopWordsTextArea.setBackground(Color.BLACK);
		stopWordsTextArea.setForeground(Color.WHITE);
		stopWordsTextArea.setLineWrap(true);
		stopWordsTextArea.setWrapStyleWord(true);
		stopWordsTextArea.setEditable(false);
//		stopWordsTextArea.setFont(font);
		
		stopWordsScrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
		
		stopWordsScrollPane.setBounds(xSomething + 20, ySomething + 295, 180, 180);
		panel.add(stopWordsScrollPane);
		
		stopWordsLabel = new JLabel("Stop Words");
		stopWordsLabel.setForeground(Color.WHITE);
		stopWordsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stopWordsLabel.setBounds(xSomething + 20, ySomething + 465, 180, 50);
		panel.add(stopWordsLabel);
		
//		Recognized Words
		recognizedTextArea = new JTextArea("", 10, 10);
		recognizedScrollPane = new JScrollPane(recognizedTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		recognizedTextArea.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		recognizedTextArea.setBackground(Color.BLACK);
		recognizedTextArea.setForeground(Color.WHITE);
		recognizedTextArea.setLineWrap(true);
		recognizedTextArea.setWrapStyleWord(true);
		recognizedTextArea.setEditable(false);
//		recognizedTextArea.setFont(font);
		
		recognizedScrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
		
		recognizedScrollPane.setBounds(xSomething + 210, ySomething + 295, 180, 180);
		panel.add(recognizedScrollPane);
		
		recognizedLabel = new JLabel("Recognized Words");
		recognizedLabel.setForeground(Color.WHITE);
		recognizedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		recognizedLabel.setBounds(xSomething + 210, ySomething + 465, 180, 50);
		panel.add(recognizedLabel);
		
//		unrecognized Words
		unrecognizedTextArea = new JTextArea("", 10, 10);
		unrecognizedScrollPane = new JScrollPane(unrecognizedTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		unrecognizedTextArea.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 2));
		unrecognizedTextArea.setBackground(Color.BLACK);
		unrecognizedTextArea.setForeground(Color.WHITE);
		unrecognizedTextArea.setLineWrap(true);
		unrecognizedTextArea.setWrapStyleWord(true);
		unrecognizedTextArea.setEditable(false);
//		unrecognizedTextArea.setFont(font);
		
		unrecognizedScrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
		
		unrecognizedScrollPane.setBounds(xSomething + 400, ySomething + 295, 180, 180);
		panel.add(unrecognizedScrollPane);
		
		unrecognizedLabel = new JLabel("Unrecognized Words");
		unrecognizedLabel.setForeground(Color.WHITE);
		unrecognizedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		unrecognizedLabel.setBounds(xSomething + 400, ySomething + 465, 180, 50);
		panel.add(unrecognizedLabel);
		addFrame();
	}
	
	protected void addFrame() {
		frame.setTitle("Categorizing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setPreferredSize(new Dimension(getXInsets(), getYInsets()));
		frame.setMaximumSize(new Dimension(getXInsets(), getYInsets()));
		frame.setMinimumSize(new Dimension(getXInsets(), getYInsets()));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(visible);
	}
	
	public void Clear() {
		wordTextArea.setText("");
		stopWordsTextArea.setText("");
		recognizedTextArea.setText("");
		unrecognizedTextArea.setText("");
	}
	
	int getXInsets() {
		return WIDTH + frame.getInsets().left + frame.getInsets().right;
	}
		
	int getYInsets() {
		return HEIGHT + frame.getInsets().top + frame.getInsets().bottom;
	}	
		
	public JFrame getFrame() {
		return frame;
	}
	
	public JLabel getWordCounter() {
		return wordCounterLabel;
	}
	
	
	public JSlider getwordCounterSlider() {
		return wordCounterSlider;
	}
	
	public JSlider getRomanceSlider() {
		return romanceSlider;
	}
	
	public JSlider getFantasySlider() {
		return fantasySlider;
	}
	
	public JSlider getScienceFictionSlider() {
		return scienceFictionSlider;
	}
	
	public JSlider getWesternSlider() {
		return westernSlider;
	}
	
	public JSlider getThrillerSlider() {
		return thrillerSlider;
	}
	
	public JSlider getMysterySlider() {
		return mysterySlider;
	}
	
	public JSlider getDetectiveSlider() {
		return detectiveSlider;
	}
	
	public JSlider getDystopiaSlider() {
		return dystopiaSlider;
	}
	
	public JTextField getRomanceTextField() {
		return romanceTextField;
	}
	
	public JTextField getFantasyTextField() {
		return fantasyTextField;
	}
	
	public JTextField getScienceFictionTextField() {
		return scienceFictionTextField;
	}
	
	public JTextField getWesternTextField() {
		return westernTextField;
	}
	
	public JTextField getThrillerTextField() {
		return thrillerTextField;
	}
	
	public JTextField getMysteryTextField() {
		return mysteryTextField;
	}
	
	public JTextField getDetectiveTextField() {
		return detectiveTextField;
	}
	
	public JTextField getDystopiaTextField() {
		return dystopiaTextField;
	}
	
	public JTextField getWordCounterTextField() {
		return wordCounterTextField;
	}
	
	public JLabel getEstimatedTime() {
		return estimatedTime;
	}
	
	public JTextArea getWordTextArea() {
		return wordTextArea;
	}
	
	public JTextArea getStopWordsTextArea() {
		return stopWordsTextArea;
	}
	
	public JTextArea getRecognizedTextArea() {
		return recognizedTextArea;
	}
	
	public JTextArea getUnrecognizedTextArea() {
		return unrecognizedTextArea;
	}

	public void actionPerformed(ActionEvent e) {
		
	}

	public void stateChanged(ChangeEvent e) {
//		System.out.println("CHANGING");
	}
	
}
