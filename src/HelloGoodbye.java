import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class HelloGoodbye extends JFrame {
	/**
	 * This is a testing GUI, to teach me how to GUI.
	 */
	private static final long serialVersionUID = 1L;
//	private static final String HELLO = "Hello!";
//	private static final String GOODBYE = "Goodbye!";
//	private String message = HELLO;
	public HelloGoodbye() {
		super("Massive spice, SVExchange giveaway helper");
		final JPanel OuterPanel = new JPanel();
		OuterPanel.setLayout(new GridBagLayout());
		GridBagConstraints OuterConstraints = new GridBagConstraints();
		final JPanel LeftPanel = new JPanel();
		LeftPanel.setPreferredSize(new Dimension(200,400));
		LeftPanel.setOpaque(true);
		LeftPanel.setBackground(Color.BLUE);
		LeftPanel.setLayout(new GridBagLayout());
		OuterConstraints.fill = GridBagConstraints.BOTH;
		OuterConstraints.gridx = 0;
		OuterConstraints.gridy = 0;
		OuterPanel.add(LeftPanel, OuterConstraints);
		final JPanel RightPanel = new JPanel();
		RightPanel.setPreferredSize(new Dimension(400,400));
		RightPanel.setOpaque(true);
		RightPanel.setBackground(Color.RED);
		OuterConstraints.gridx = 1;
		OuterConstraints.gridy = 0;
		OuterPanel.add(RightPanel, OuterConstraints);
		GridBagConstraints LeftConstraints = new GridBagConstraints();
		final JPanel ModePanel = new JPanel();
		ModePanel.setOpaque(true);
		ModePanel.setBackground(Color.GREEN);
		LeftConstraints.fill = GridBagConstraints.BOTH;
		LeftConstraints.gridx = 0;
		LeftConstraints.gridy = 0;
		LeftPanel.add(ModePanel, LeftConstraints);
		final JPanel ModeViewPanel = new JPanel();
		ModeViewPanel.setOpaque(true);
		ModeViewPanel.setBackground(Color.YELLOW);
		LeftConstraints.gridy = 1;
		LeftPanel.add(ModeViewPanel, LeftConstraints);
		final JPanel TodoPanel = new JPanel();
		TodoPanel.setOpaque(true);
		TodoPanel.setBackground(Color.GRAY);
		LeftConstraints.gridy = 2;
		LeftPanel.add(TodoPanel, LeftConstraints);
		final JPanel ConfigPanel = new JPanel();
		ConfigPanel.setOpaque(true);
		ConfigPanel.setBackground(Color.CYAN);
		LeftConstraints.gridy = 3;
		LeftPanel.add(ConfigPanel, LeftConstraints);
		
		add(OuterPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	public static void main (final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { new HelloGoodbye();}
		});
	}
}
