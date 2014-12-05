import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Core GUI, gently building a swing GUI for the massive spice project.
 */
public class CoreGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel RightPanel = null;
	private JPanel ModePanel = null;
	private JPanel ModeViewPanel = null;
	private JPanel TodoPanel = null;
	private JPanel ConfigPanel = null;
	private Web_int WebInt = null;

	public CoreGUI() {
		/* *
		 * Constructor for the GUI.
		 */
		super("Massive spice, SVExchange giveaway helper");
		this.BuildCoreGUI();
		this.BuildModePanel();
		this.BuildTodoPanel();
		this.BuildConfigPanel();
	}
	
	public final void DrawRightLoginPanel() {
		/* *
		 * Draws login panel for the right block.
		 */
		RightPanel.removeAll();
		RightPanel.setLayout(new GridBagLayout());
		GridBagConstraints RightConstraints = new GridBagConstraints();
		JLabel LoginTitle = new JLabel("Log in to reddit:");
		RightConstraints.gridx = 0;
		RightConstraints.gridy = 0;
		RightConstraints.gridwidth = 2;
		this.RightPanel.add(LoginTitle,RightConstraints);
		JLabel LoginUsername = new JLabel("Username:");
		RightConstraints.gridx = 0;
		RightConstraints.gridy = 1;
		RightConstraints.gridwidth = 1;
		RightPanel.add(LoginUsername,RightConstraints);
		final JTextField Username = new JTextField(20);
		RightConstraints.gridx = 1;
		RightPanel.add(Username,RightConstraints);
		JLabel LoginPassword = new JLabel("Password:");
		RightConstraints.gridx = 0;
		RightConstraints.gridy = 2;
		RightPanel.add(LoginPassword,RightConstraints);
		final JPasswordField Password = new JPasswordField(20);
		RightConstraints.gridx = 1;
		RightPanel.add(Password,RightConstraints);
		JButton LoginButton = new JButton("Login");
		RightConstraints.gridx = 0;
		RightConstraints.gridy = 3;
		RightConstraints.gridwidth = 2;
		RightPanel.add(LoginButton,RightConstraints);
		ActionListener LoginAction = new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				String InputUsername = Username.getText();
				String InputPassword = Password.getPassword().toString();
				WebInt = new Web_int(InputUsername,InputPassword);
				BuildConfigPanel();
			}
		};
		Password.addActionListener(LoginAction);
		LoginButton.addActionListener(LoginAction);
		revalidate();
		repaint();
	}

	private void BuildModePanel() {
		/* *
		 * Draws the mode selection panel, the header of the left column.
		 */
		ModePanel.setLayout(new GridLayout(1,2));
		JButton Giveaways = new JButton("Giveaways");
		ModePanel.add(Giveaways);
		JButton HatchRequests = new JButton("<html><div align='center'>Hatch<br />requests</div></html>");
		ModePanel.add(HatchRequests);
		//JButton Logout = new JButton("???");
		//ModePanel.add(Logout);
	}
	
	private void BuildTodoPanel() {
		/* *
		 * Draws the todo list panel.
		 */
		TodoPanel.setLayout(new FlowLayout());
		JLabel TodoTitle = new JLabel("Todo list:",SwingConstants.CENTER);
		TodoPanel.add(TodoTitle,BorderLayout.NORTH);
	}
	
	private void BuildConfigPanel() {
		/* *
		 * Draws the config panel, at the bottom of the left column.
		 */
		ConfigPanel.removeAll();
		ConfigPanel.setLayout(new GridLayout(1,3));
		JButton ConfigMessage = new JButton("<html><div align='center'>Change<br />messages</div></html>");
		ConfigMessage.setMargin(new Insets(0,0,0,0));
		ConfigPanel.add(ConfigMessage);
		JButton NewGiveaway = new JButton("<html><div align='center'>New<br />giveaway</div></html>");
		NewGiveaway.setMargin(new Insets(0,0,0,0));
		ConfigPanel.add(NewGiveaway);
		String LoginMessage;
		JButton Logout;
		if(WebInt == null) {
			LoginMessage = "Login";
			Logout = new JButton(LoginMessage);
			Logout.addActionListener(new ActionListener() {
				public void actionPerformed(final ActionEvent e) {
					DrawRightLoginPanel();
				}
			});
		} else {
			LoginMessage = "Logout";
			Logout = new JButton(LoginMessage);
			Logout.addActionListener(new ActionListener() {
				public void actionPerformed(final ActionEvent e) {
					WebInt = null;
					BuildConfigPanel();
				}
			});
		}
		Logout.setMargin(new Insets(0,0,0,0));
		ConfigPanel.add(Logout);
	}
	
	private void BuildCoreGUI() {
		/* *
		 * Puts together the core framework for the GUI and lays out the JPanels within the window.
		 */
		final JPanel OuterPanel = new JPanel();
		OuterPanel.setLayout(new GridBagLayout());
		GridBagConstraints OuterConstraints = new GridBagConstraints();
		RightPanel = new JPanel();
		RightPanel.setOpaque(true);
		RightPanel.setBackground(Color.RED);
		OuterConstraints.fill = GridBagConstraints.BOTH;
		OuterConstraints.gridheight = 4;
		OuterConstraints.gridx = 1;
		OuterConstraints.gridy = 0;
		OuterConstraints.weightx = OuterConstraints.weighty = 1.0;
		OuterPanel.add(RightPanel, OuterConstraints);
		ModePanel = new JPanel();
		ModePanel.setOpaque(true);
		ModePanel.setBackground(Color.GREEN);
		ModePanel.setPreferredSize(new Dimension(200,50));
		OuterConstraints.fill = GridBagConstraints.VERTICAL;
		OuterConstraints.gridheight = 1;
		OuterConstraints.gridx = 0;
		OuterConstraints.gridy = 0;
		OuterConstraints.weightx = OuterConstraints.weighty = 0;
		OuterPanel.add(ModePanel, OuterConstraints);
		ModeViewPanel = new JPanel();
		ModeViewPanel.setOpaque(true);
		ModeViewPanel.setBackground(Color.YELLOW);
		ModeViewPanel.setPreferredSize(new Dimension(200,100));
		OuterConstraints.gridy = 1;
		OuterPanel.add(ModeViewPanel, OuterConstraints);
		TodoPanel = new JPanel();
		TodoPanel.setOpaque(true);
		TodoPanel.setBackground(Color.GRAY);
		OuterConstraints.gridy = 2;
		OuterConstraints.fill = GridBagConstraints.BOTH;
		OuterConstraints.weighty = 1.0;
		OuterPanel.add(TodoPanel, OuterConstraints);
		ConfigPanel = new JPanel();
		ConfigPanel.setOpaque(true);
		ConfigPanel.setBackground(Color.CYAN);
		ConfigPanel.setPreferredSize(new Dimension(200,50));
		OuterConstraints.gridy = 3;
		OuterConstraints.fill = GridBagConstraints.NONE;
		OuterConstraints.weighty = 0;
		OuterPanel.add(ConfigPanel, OuterConstraints);
		add(OuterPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(700, 500));
		pack();
		setVisible(true);
	}
	
	public static void main (final String[] args) {
		/* *
		 * Starts up the GUI.
		 */
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { new CoreGUI();}
		});
	}
}
