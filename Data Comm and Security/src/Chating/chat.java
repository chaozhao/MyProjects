package Chating;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Timer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

public class chat extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField jTextField0;
	private JButton jButton3;
	private JButton jButton4;
	private JButton jButton5;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JTextField jTextField1;
	private JButton jButton0;
	public static ArrayList clientArrayList;
	private InetAddress address;
	private JList jList0;
	public static DefaultListModel listModel;
	private DefaultListModel listModel1;
	private JScrollPane jScrollPane0;
	private UDPClient client;
	public static JTextPane jTextPane0;
	private JScrollPane jScrollPane1;
	private int port;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JList jList1;
	private JScrollPane jScrollPane2;
	public static String serverIP = null;
	private ClientTimer ct;
	private Timer timerTask;
	private ConnectRenewThread t2;
	public static JLabel jLabel5;
	private JButton jButton2;
	private JTextField jTextField3;
	private JTextField jTextField2;
	private JButton jButton1;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JSeparator jSeparator1;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JSeparator jSeparator2;
	private JSeparator jSeparator3;
	private JButton sendButton;
	private JFileChooser fc;
	private File file;
	private String fileName;
	private long fileLength;
	private JButton jButton6;
	private JButton jButton7;
	private String IPAddress;
	private JButton receiveButton;
	private FileSendThread fileSend;
	private FileReceiveThread fileReceive;
	public static JProgressBar jProgressBar0;
	public static JProgressBar jProgressBar1;
	public static JLabel jLabel10;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";

	public chat() {
		super.setTitle("Chatting");
		clientArrayList = new ArrayList();
		client = new UDPClient();
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		initComponents();
		port = client.openSocket();
		MessReceiveThread thread1 = new MessReceiveThread(client);
		thread1.start();
		ConnectRenew renew = new ConnectRenew();
		try {
			renew.openSocket();
		} catch (Exception e) {
			System.out
					.println("fail to open socket in ConnectRenew. Please change the port in ConnectRenew and UPDSe");
		}
		t2 = new ConnectRenewThread(renew);
		ct = new ClientTimer(port, renew);
		timerTask = new Timer();
	}

	private void initComponents() {
		setTitle("Chatting");
		setLayout(new GroupLayout());
		add(getJLabel2(), new Constraints(new Leading(42, 109, 12, 12),
				new Leading(36, 10, 10)));
		add(getJLabel3(), new Constraints(new Leading(161, 10, 10),
				new Leading(36, 12, 12)));
		add(getJLabel4(), new Constraints(new Leading(42, 12, 12), new Leading(
				12, 12, 12)));
		add(getJTextField1(), new Constraints(new Leading(161, 12, 12),
				new Leading(8, 12, 12)));
		add(getJButton0(), new Constraints(new Leading(410, 12, 12),
				new Leading(5, 12, 12)));
		add(getJLabel6(), new Constraints(new Leading(42, 12, 12), new Leading(
				63, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(383, 124, 10, 10),
				new Leading(57, 12, 12)));
		add(getJScrollPane0(), new Constraints(new Leading(383, 165, 10, 10),
				new Leading(86, 128, 10, 10)));
		add(getJButton4(), new Constraints(new Leading(566, 83, 12, 12),
				new Leading(184, 10, 10)));
		add(getJTextField0(), new Constraints(new Leading(42, 218, 10, 10),
				new Leading(373, 18, 10, 10)));
		add(getJScrollPane1(), new Constraints(new Leading(42, 299, 12, 12),
				new Leading(87, 241, 10, 10)));
		add(getJLabel1(), new Constraints(new Leading(384, 64, 12, 12),
				new Leading(230, 28, 12, 12)));
		add(getJScrollPane2(), new Constraints(new Leading(383, 164, 12, 12),
				new Leading(262, 127, 10, 10)));
		add(getJButton5(), new Constraints(new Leading(569, 80, 12, 12),
				new Leading(365, 25, 12, 12)));
		add(getJButton3(), new Constraints(new Leading(278, 71, 10, 10),
				new Leading(367, 24, 10, 10)));
		add(getJLabel7(), new Constraints(new Leading(42, 12, 12), new Leading(
				424, 12, 12)));
		add(getJTextField2(), new Constraints(new Leading(42, 214, 10, 10),
				new Leading(454, 12, 12)));
		add(getJSeparator2(), new Constraints(new Bilateral(-4, 0, 670),
				new Leading(433, -18, 10, 10)));
		add(getJSeparator3(), new Constraints(new Leading(6, 643, 12, 12),
				new Leading(409, 12, 12, 12)));
		add(getJLabel8(), new Constraints(new Leading(42, 12, 12), new Leading(
				531, 10, 10)));
		add(getJLabel9(), new Constraints(new Leading(42, 12, 12), new Leading(
				555, 10, 10)));
		add(getJTextField3(), new Constraints(new Leading(42, 214, 12, 12),
				new Leading(580, 10, 10)));
		add(getJSeparator1(), new Constraints(new Leading(6, 643, 12, 12),
				new Leading(520, 11, 10, 10)));
		add(getJButton6(), new Constraints(new Leading(297, 82, 10, 10),
				new Leading(482, 25, 12, 12)));
		add(getJLabel10(), new Constraints(new Leading(297, 10, 10),
				new Leading(456, 12, 12)));
		add(getJButton1(), new Constraints(new Leading(188, 70, 10, 10),
				new Leading(480, 27, 12, 12)));
		add(getJButton2(), new Constraints(new Leading(185, 76, 12, 12),
				new Leading(614, 24, 12, 12)));
		add(getJButton7(), new Constraints(new Leading(297, 83, 12, 12),
				new Leading(614, 24, 12, 12)));
		add(getJLabel5(), new Constraints(new Leading(79, 381, 10, 10),
				new Leading(554, 17, 12, 12)));
		add(getJProgressBar0(), new Constraints(new Leading(416, 10, 10),
				new Leading(489, 16, 12, 12)));
		add(getJProgressBar1(), new Constraints(new Leading(414, 10, 10),
				new Leading(620, 17, 12, 12)));
		setSize(666, 652);
	}

	private JLabel getJLabel10() {
		if (jLabel10 == null) {
			jLabel10 = new JLabel();
			jLabel10.setText("File receive");
		}
		return jLabel10;
	}

	private JProgressBar getJProgressBar1() {
		if (jProgressBar1 == null) {
			jProgressBar1 = new JProgressBar();
			jProgressBar1.setStringPainted(true);
		}
		return jProgressBar1;
	}

	private JProgressBar getJProgressBar0() {
		if (jProgressBar0 == null) {
			jProgressBar0 = new JProgressBar();
			jProgressBar0.setStringPainted(true);
		}
		return jProgressBar0;
	}

	private JButton getJButton7() {
		if (jButton7 == null) {
			jButton7 = new JButton();
			jButton7.setText("Send");
			jButton7.addActionListener(this);
		}
		return jButton7;
	}

	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setText("Receive");
			jButton6.addActionListener(this);
		}
		return jButton6;
	}

	private JSeparator getJSeparator3() {
		if (jSeparator3 == null) {
			jSeparator3 = new JSeparator();
		}
		return jSeparator3;
	}

	private JSeparator getJSeparator2() {
		if (jSeparator2 == null) {
			jSeparator2 = new JSeparator();
		}
		return jSeparator2;
	}

	private JLabel getJLabel9() {
		if (jLabel9 == null) {
			jLabel9 = new JLabel();
			jLabel9.setText("File:");
		}
		return jLabel9;
	}

	private JLabel getJLabel8() {
		if (jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("File request from group members:");
		}
		return jLabel8;
	}

	private JSeparator getJSeparator1() {
		if (jSeparator1 == null) {
			jSeparator1 = new JSeparator();
		}
		return jSeparator1;
	}

	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("File Exchange:");
		}
		return jLabel7;
	}

	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("Text Message:");
		}
		return jLabel6;
	}

	private JButton getSendButton() {
		if (sendButton == null) {
			sendButton = new JButton();
			sendButton.setText("Send");
			sendButton.addActionListener(this);
		}
		return sendButton;

	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Query");
			jButton1.addActionListener(this);
		}
		return jButton1;
	}

	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setText("File name here");
		}
		return jTextField2;
	}

	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
			jTextField3.setText("your file to send (path)");
		}
		return jTextField3;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Select");
			jButton2.addActionListener(this);
		}
		return jButton2;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("No file request yet");
		}
		return jLabel5;
	}

	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getJList1());
		}
		return jScrollPane2;
	}

	private JList getJList1() {
		if (jList1 == null) {
			jList1 = new JList();
			listModel1 = new DefaultListModel();
			jList1.setModel(listModel1);
		}
		return jList1;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("My Group:");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Online client list:");
		}
		return jLabel0;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTextPane0());
		}
		return jScrollPane1;
	}

	private JTextPane getJTextPane0() {
		if (jTextPane0 == null) {
			jTextPane0 = new JTextPane();
			jTextPane0.setText("");
		}
		return jTextPane0;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJList0());
		}
		return jScrollPane0;
	}

	private JList getJList0() {
		if (jList0 == null) {
			jList0 = new JList();
			listModel = new DefaultListModel();
			jList0.setModel(listModel);
		}
		return jList0;
	}

	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == jButton0) {
			listModel.removeAllElements();
			clientArrayList.clear();
			// ClientSignIn c = new ClientSignIn();
			serverIP = jTextField1.getText();
			timerTask.schedule(ct, 1000, 6000);
			t2.start();
			/*
			 * c.clientSignIn(serverIP, port); String clientList =
			 * c.getClientList(); StringTokenizer str = new
			 * StringTokenizer(clientList, ";"); int j = str.countTokens();
			 * String nextToken = ""; for (int i = 0; i < j; i++) { nextToken =
			 * str.nextToken(); nextToken = nextToken.replace("/", "");
			 * clientArrayList.add(nextToken); }
			 * 
			 * for (int i = 0; i < clientArrayList.size(); i += 2) {
			 * listModel.addElement(clientArrayList.get(i)); }
			 */
		}
		if (source == jButton3) {
			for (int i = 0; i < listModel1.size(); i++) {
				for (int y = 0; y < clientArrayList.size(); y += 2) {
					if (listModel1.get(i).equals(clientArrayList.get(y))) {

						client.sendMessage(clientArrayList.get(y).toString(),
								Integer.parseInt(clientArrayList.get(y + 1)
										.toString()), "message;"
										+ address.getHostAddress().toString()
										+ " said to me:"
										+ jTextField0.getText());

					}
				}
			}

		}
		if (source == jButton4) {
			listModel1.addElement(jList0.getSelectedValue());
		}
		if (source == jButton5) {
			listModel1.removeElementAt(jList1.getSelectedIndex());
		}

		if (source == jButton1) {
			for (int i = 0; i < listModel1.size(); i++) {
				for (int y = 0; y < clientArrayList.size(); y += 2) {
					if (listModel1.get(i).equals(clientArrayList.get(y))) {

						client.sendMessage(clientArrayList.get(y).toString(),
								Integer.parseInt(clientArrayList.get(y + 1)
										.toString()), "file;"
										+ "<"
										+ address.getHostAddress().toString()
										+ ">"
										+ " want to get a file:"
										+ jTextField2.getText()
										+ ";"
										+ Integer.parseInt(clientArrayList.get(
												y + 1).toString()));

					}
				}
			}
		}
		if (source == jButton2) {
			fc = new JFileChooser();
			fc.showSaveDialog(jButton2); 
			file = fc.getSelectedFile();
			fileLength = file.length();
			fileName = file.getAbsolutePath();
			jTextField3.setText(fileName);

		}
		if (source == jButton7) {

			String getIP = jLabel5.getText();
			if (!getIP.equals("NO file request yet")) {
				StringTokenizer str = new StringTokenizer(getIP, ">");
				IPAddress = str.nextToken();
				IPAddress = IPAddress.substring(1, IPAddress.length());
				client.sendMessage(IPAddress, MessReceiveThread.port, "send;"
						+ "<" + address.getHostAddress().toString() + ">"
						+ " send me the file:" + file.getName());
				fileSend = new FileSendThread(fileName, fileLength);
				fileSend.start();
			}
		}
		if (source == jButton6) {
			String getIP = jLabel10.getText();
			if (!getIP.equals("File receive")) {
				StringTokenizer str = new StringTokenizer(getIP, ">");
				IPAddress = str.nextToken();
				IPAddress = IPAddress.substring(1, IPAddress.length());
				str=new StringTokenizer(getIP,":");
				str.nextToken();
				File fileToSave=new File(str.nextToken());
				fc = new JFileChooser();
				fc.setSelectedFile(fileToSave);
				int result = fc.showSaveDialog(receiveButton);
				file = fc.getSelectedFile();
				if (file != null && result == fc.APPROVE_OPTION) {
					fileReceive = new FileReceiveThread(IPAddress, file.getAbsolutePath());
					fileReceive.start();
				}
			}
		}

	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("Get Online Client");
			jButton0.addActionListener(this);
		}
		return jButton0;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField(20);
			jTextField1.setText("IP here");
		}
		return jTextField1;
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

			jLabel3.setText(address.getHostAddress().toString());
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

	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setText("Delete");
			jButton5.addActionListener(this);
		}
		return jButton5;
	}

	private JButton getReceiveButton() {
		if (receiveButton == null) {
			receiveButton = new JButton();
			receiveButton.setText("receive");
			// receiveButton.addActionListener(this);
		}
		return receiveButton;
	}

	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setText("Add");
			jButton4.addActionListener(this);
		}
		return jButton4;
	}

	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("Send");
			jButton3.addActionListener(this);
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
	 * Main entry of the class. Note: This class is only created so that you can
	 * easily preview the result at runtime. It is not expected to be managed by
	 * the designer. You can modify it as you like.
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
