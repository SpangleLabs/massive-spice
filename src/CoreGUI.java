import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

import java.awt.GridLayout;


import javax.swing.JButton;
//import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JPanel;
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

	public CoreGUI() {
		/* *
		 * Constructor for the GUI.
		 */
		super("Massive spice, SVExchange giveaway helper");
		this.BuildCoreGUI();
		this.BuildConfigPanel();
	}
	
	private void BuildConfigPanel() {
		ConfigPanel.setLayout(new GridLayout(1,3));
		JButton ConfigMessage = new JButton("Config msg");
		ConfigPanel.add(ConfigMessage);
		JButton NewGiveaway = new JButton("New giveaway");
		ConfigPanel.add(NewGiveaway);
		JButton Logout = new JButton("Logout");
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
