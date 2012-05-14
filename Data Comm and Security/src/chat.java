import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;


//VS4E -- DO NOT REMOVE THIS LINE!
public class chat extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField jTextField0;
	private JButton jButton3;
	private JLabel jLabel0;
	private JButton jButton4;
	private JButton jButton5;
	private JButton jButton6;
	private JLabel online_list;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JButton jButton7;
	private JTextField jTextField1;
	private JLabel jLabel1;
	private JButton jButton0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public chat() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(42, 320, 10, 10), new Leading(90, 325, 10, 10)));
		add(getJButton6(), new Constraints(new Trailing(12, 534, 534), new Leading(440, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(42, 109, 12, 12), new Leading(36, 10, 10)));
		add(getJLabel3(), new Constraints(new Leading(161, 10, 10), new Leading(36, 12, 12)));
		add(getJLabel4(), new Constraints(new Leading(42, 12, 12), new Leading(12, 12, 12)));
		add(getJButton4(), new Constraints(new Leading(410, 65, 162, 162), new Leading(440, 12, 12)));
		add(getJButton5(), new Constraints(new Bilateral(482, 85, 70), new Leading(440, 12, 12)));
		add(getJTextField0(), new Constraints(new Leading(42, 237, 10, 10), new Leading(442, 73, 10, 10)));
		add(getJButton3(), new Constraints(new Leading(299, 12, 12), new Leading(487, 12, 12)));
		add(getJTextField1(), new Constraints(new Leading(161, 12, 12), new Leading(8, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(410, 228, 12, 12), new Leading(90, 325, 12, 12)));
		add(getJButton0(), new Constraints(new Leading(410, 12, 12), new Leading(26, 12, 12)));
		setSize(666, 538);
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("Get Online Client");
		}
		return jButton0;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("get online client");
			jLabel1.setBorder(new LineBorder(Color.black, 1, false));
		}
		return jLabel1;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setText("IP here");
		}
		return jTextField1;
	}

	private JButton getJButton7() {
		if (jButton7 == null) {
			jButton7 = new JButton();
			jButton7.setText("Get Online Client");
		}
		return jButton7;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Server IP Address:");
		}
		return jLabel4;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("IP here");
		}
		return jLabel3;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Your IP Address: ");
		}
		return jLabel2;
	}

	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setText("Clear");
		}
		return jButton6;
	}

	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setText("Delete");
		}
		return jButton5;
	}

	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setText("Add");
		}
		return jButton4;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Chat Lable");
			jLabel0.setBorder(new LineBorder(Color.black, 1, false));
		}
		return jLabel0;
	}

	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("Send");
		}
		return jButton3;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
			jTextField0.setText("send text here");
		}
		return jTextField0;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				chat frame = new chat();
				frame.setDefaultCloseOperation(chat.EXIT_ON_CLOSE);
				frame.setTitle("chat");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
