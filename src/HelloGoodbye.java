import java.awt.BorderLayout;
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
	private static final String HELLO = "Hello!";
	private static final String GOODBYE = "Goodbye!";
	private String message = HELLO;
	public HelloGoodbye() {
		super("HelloGoodbye");
		final JLabel aLabel = new JLabel(message, SwingConstants.CENTER);
		final JButton aButton = new JButton("Click Me");
		aButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if(message.equals(HELLO)) {
					message = GOODBYE;
				} else {
					message = HELLO;
				}
				aLabel.setText(message);
			}
		});
		final JPanel aPanel = new JPanel();
		aPanel.setBorder(BorderFactory.createEmptyBorder(20,60,20,60));
		aPanel.setLayout(new BorderLayout());
		aPanel.add(aLabel,BorderLayout.CENTER);
		aPanel.add(aButton,BorderLayout.SOUTH);
		add(aPanel);
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
