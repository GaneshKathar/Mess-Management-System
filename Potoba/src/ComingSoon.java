import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;


public class ComingSoon extends JPanel {

	/**
	 * Create the panel.
	 */
	public ComingSoon() {
		setLayout(null);
		
		JLabel lblComingSoon = new JLabel("Coming Soon....");
		lblComingSoon.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblComingSoon.setBounds(106, 193, 464, 108);
		add(lblComingSoon);

	}
}
