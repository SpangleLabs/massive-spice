import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

//import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JPanel;
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
		final JPanel RightPanel = new JPanel();
		RightPanel.setOpaque(true);
		RightPanel.setBackground(Color.RED);
		OuterConstraints.fill = GridBagConstraints.BOTH;
		OuterConstraints.gridheight = 4;
		OuterConstraints.gridx = 1;
		OuterConstraints.gridy = 0;
		OuterConstraints.weightx = OuterConstraints.weighty = 1.0;
		OuterPanel.add(RightPanel, OuterConstraints);
		final JPanel ModePanel = new JPanel();
		ModePanel.setOpaque(true);
		ModePanel.setBackground(Color.GREEN);
		ModePanel.setPreferredSize(new Dimension(200,50));
		OuterConstraints.fill = GridBagConstraints.VERTICAL;
		OuterConstraints.gridheight = 1;
		OuterConstraints.gridx = 0;
		OuterConstraints.gridy = 0;
		OuterConstraints.weightx = OuterConstraints.weighty = 0;
		OuterPanel.add(ModePanel, OuterConstraints);
		final JPanel ModeViewPanel = new JPanel();
		ModeViewPanel.setOpaque(true);
		ModeViewPanel.setBackground(Color.YELLOW);
		ModeViewPanel.setPreferredSize(new Dimension(200,100));
		OuterConstraints.gridy = 1;
		OuterPanel.add(ModeViewPanel, OuterConstraints);
		final JPanel TodoPanel = new JPanel();
		TodoPanel.setOpaque(true);
		TodoPanel.setBackground(Color.GRAY);
		TodoPanel.setPreferredSize(new Dimension(200,100));
		OuterConstraints.gridy = 2;
		OuterConstraints.weighty = 1.0;
		OuterPanel.add(TodoPanel, OuterConstraints);
		final JPanel ConfigPanel = new JPanel();
		ConfigPanel.setOpaque(true);
		ConfigPanel.setBackground(Color.CYAN);
		ConfigPanel.setPreferredSize(new Dimension(200,50));
		OuterConstraints.gridy = 3;
		OuterConstraints.weighty = 0;
		OuterPanel.add(ConfigPanel, OuterConstraints);
		add(OuterPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(700, 500));
		pack();
		setVisible(true);
	}
	public static void main (final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { new HelloGoodbye();}
		});
	}
}
