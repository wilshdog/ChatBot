package chat.view;

import chat.controller.ChatbotController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * The JPanel subclass for the chatbot project.
 * 
 * @author ewil1026
 * @version 21/11/17
 */
public class ChatPanel extends JPanel
{
	private ChatbotController appController;
	private JButton checkerButton;
	private JButton searchButton;
	private JButton saveButton;
	private JButton loadButton;
	private JButton tweetButton;
	private JTextArea chatArea;
	private JTextField inputField;
	private JButton chatButton;
	private SpringLayout appLayout;
	private JLabel infoLabel;
	//need data member for JScrollPane
	private JScrollPane chatScrollPane;

	public ChatPanel(ChatbotController appController)
	{
		super();
		this.appController = appController;
		// Initialize GUI data members
		chatArea = new JTextArea(10, 15);
		inputField = new JTextField(20);
		infoLabel = new JLabel("Type here to chat with chatbot");
		appLayout = new SpringLayout();
		appLayout.putConstraint(SpringLayout.SOUTH, infoLabel, -6, SpringLayout.NORTH, inputField);
		appLayout.putConstraint(SpringLayout.EAST, infoLabel, -10, SpringLayout.EAST, this);
		chatButton = new JButton("chat", new ImageIcon(getClass().getResource("/chat/view/images/ChatIcon.png")));
		appLayout.putConstraint(SpringLayout.EAST, chatButton, -152, SpringLayout.EAST, this);
		loadButton = new JButton("load", new ImageIcon(getClass().getResource("/chat/view/images/LoadIcon.png")));
		appLayout.putConstraint(SpringLayout.NORTH, loadButton, -6, SpringLayout.NORTH, inputField);
		saveButton = new JButton("save", new ImageIcon(getClass().getResource("/chat/view/images/SaveIcon.png")));
		appLayout.putConstraint(SpringLayout.NORTH, saveButton, -3, SpringLayout.NORTH, chatButton);
		appLayout.putConstraint(SpringLayout.EAST, saveButton, -21, SpringLayout.WEST, chatButton);
		tweetButton = new JButton("tweet", new ImageIcon(getClass().getResource("/chat/view/images/TwitterIcon.png")));
		appLayout.putConstraint(SpringLayout.NORTH, tweetButton, 6, SpringLayout.SOUTH, saveButton);
		appLayout.putConstraint(SpringLayout.WEST, tweetButton, 10, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.SOUTH, tweetButton, 178, SpringLayout.NORTH, saveButton);
		appLayout.putConstraint(SpringLayout.EAST, tweetButton, -403, SpringLayout.EAST, this);
		searchButton = new JButton("search", new ImageIcon(getClass().getResource("/chat/view/images/SearchIcon.png")));
		appLayout.putConstraint(SpringLayout.NORTH, searchButton, -3, SpringLayout.NORTH, chatButton);
		appLayout.putConstraint(SpringLayout.EAST, searchButton, 0, SpringLayout.EAST, this);
		checkerButton = new JButton("Checker");
		appLayout.putConstraint(SpringLayout.SOUTH, checkerButton, -7, SpringLayout.SOUTH, this);
		appLayout.putConstraint(SpringLayout.NORTH, inputField, 0, SpringLayout.NORTH, checkerButton);
		appLayout.putConstraint(SpringLayout.EAST, inputField, -4, SpringLayout.WEST, checkerButton);
		appLayout.putConstraint(SpringLayout.WEST, checkerButton, 361, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.EAST, checkerButton, -10, SpringLayout.EAST, this);
		
		//init the scroll pane
		chatScrollPane = new JScrollPane();
		appLayout.putConstraint(SpringLayout.WEST, loadButton, 0, SpringLayout.WEST, chatScrollPane);
		
		//call new helper method
		setupScrollPane();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupScrollPane()
	{
		chatScrollPane.setViewportView(chatArea);
		chatScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	
	

	private void setupPanel()
	{
		this.setBackground(Color.ORANGE);
		this.setLayout(appLayout);
		this.add(chatButton);
		this.add(searchButton);
		this.add(saveButton);
		this.add(loadButton);
		this.add(tweetButton);
		this.add(inputField);
		this.add(chatScrollPane);
		this.add(checkerButton);
		this.add(infoLabel);
		chatArea.setEnabled(false);
		chatArea.setEditable(false);
	}

	private void setupLayout()
	{
		appLayout.putConstraint(SpringLayout.NORTH, chatButton, 6, SpringLayout.SOUTH, chatScrollPane);
		appLayout.putConstraint(SpringLayout.NORTH, chatScrollPane, 20, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.WEST, chatScrollPane, 25, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.EAST, chatScrollPane, -25, SpringLayout.EAST, this);
	}

	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String userText = inputField.getText();
				String displayText = appController.interactWithChatbot(userText);
				chatArea.append(displayText);
				inputField.setText("");
			}

		});

		checkerButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String userText = inputField.getText();
				String displayText = appController.useCheckers(userText);
				chatArea.append(displayText);
				inputField.setText("");

			}

		});
	}
	
	
	
	
	
	

}
