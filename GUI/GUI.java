package GUI;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;

import src.Question;
import src.Run;
import src.UserInput;

import java.awt.event.*;
import java.io.IOException;

public class GUI implements ActionListener {
	
	UserInput ui;
	
	JFrame frame;
	JPanel panel;
	JButton button;
	JTextArea MainDialogueArea;
	JTextField UserTextField;
	JScrollPane scroll;
	
	String userString;
	Question question;
	static boolean inputbool = false;
	
	
	public GUI(){
		frame = new JFrame();
		
		setFrame();
		setPanel();
		setUserText();
		setButton();
		
		frame.add(UserTextField, BorderLayout.WEST);
		frame.add(button, BorderLayout.EAST);
		frame.add(scroll, BorderLayout.NORTH);
		
		frame.setVisible(true);
		ui = new UserInput();
		setUserText("");
		
	}
	public void actionPerformed(ActionEvent e) {
		String user = UserTextField.getText();
		if(user.length()>0) {
			setUserText(user);
			ui.setInput(user);
			MainDialogueArea.append("\nUser: "+user);
			UserTextField.setText(""); //clears text field
		}
		else {
			setBotOutput(getErrorMessage());
		}
	}
	
	public void setUserText(String user) {
		userString = user;
	}
	public String getUserText() {
		return userString;
	}
	public void setBotOutput(Question q) {
		String bot = q.getQuestion();
		MainDialogueArea.append("\nTech-Bot: "+bot);
	}
	public void setBotOutput(String output) {
		MainDialogueArea.append("\nTech-Bot: "+output);
	}
	
	public void stopBotOutput() {
		MainDialogueArea.append("");
	}
	
	public boolean getInputBool() {
		return (getUserText() != null);
	}
	private void setFrame() {
		frame.setTitle("Chatbot.exe");
		
		frame.setSize(600,600);
		frame.setLayout(new BorderLayout());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}
	private void setPanel() {
		MainDialogueArea = new JTextArea();
		MainDialogueArea.setEditable(false);
		DefaultCaret caret = (DefaultCaret)MainDialogueArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scroll = new JScrollPane(MainDialogueArea);
		scroll.setPreferredSize(new Dimension(550,500)); 
	}
	private void setUserText() {
		UserTextField = new JTextField();
		UserTextField.setPreferredSize(new Dimension(475,60));	
		UserTextField.addActionListener(this);
	}
	private void setButton() {
		button = new JButton("Send");
		button.setPreferredSize(new Dimension(100,40));
		button.addActionListener(this);
	}
	
	public String getErrorMessage() {
		int i = (int) (Math.random()*5);
		switch(i) {
		case 0: return "Did not understand that, please try again.";
		case 1: return "Input invalid, please try again.";
		case 2: return "I couldn't seem to understand that, could you try again?";
		case 3: return "I didn't get that, try again please.";
		case 4: return "Error 404: Valid Input not found.";
		}
		return "";
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		GUI gui = new GUI();
		Run run = new Run(gui);
		gui.MainDialogueArea.append("Tech-Bot: Hello, I am Tech-bot. I will be assisting you today.");
		run.initialize();
		run.runLoop();
		
	}
	public void exit() throws InterruptedException {
		MainDialogueArea.append("\nSystem Exiting");
		Thread.sleep(3000);
		System.exit(0);
	}
	public JTextComponent getTextField() {
		return UserTextField;
	}
}