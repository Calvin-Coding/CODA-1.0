package coda_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class CodaFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JMenuItem open;
	private String settext;
	private JTextArea area;
	private String text;
	private Font font;
	private Color front;
	private Color areaback;
	private Color btncolor;
	private Font areafont;
	private JMenuBar bar;
	private JMenu file;
	private JMenuItem save;
	private String fontsize;
	private JMenuItem njava;
	private JMenuItem nhtml;
	private JMenu n;

	public CodaFrame(String stext) {
		super("C()D/\\");
		settext = stext;
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		try {
			setIconImage(ImageIO.read(getClass().getResourceAsStream("codalogo2.png")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		open = new JMenuItem("Open", 'O');
		save = new JMenuItem("Save", 'S');
		KeyStroke opens = KeyStroke.getKeyStroke("control O");
		open.setAccelerator(opens);
		KeyStroke saves = KeyStroke.getKeyStroke("control S");
		save.setAccelerator(saves);
		n = new JMenu("New");
		area = new JTextArea(settext);
		bar = new JMenuBar();
		file = new JMenu("File");
		file.setMnemonic('F');
		njava = new JMenuItem("Java", 'J');
		nhtml = new JMenuItem("HTML", 'H');
		bar.add(file);
		n.add(njava);
		file.add(n);
		file.add(save);
		file.add(open);
		setJMenuBar(bar);
		add(new JScrollPane(area), BorderLayout.CENTER);
		front = new Color(236, 236, 236);
		areaback = new Color(64, 80, 100);
		font = new Font(java.awt.Font.MONOSPACED, Font.BOLD, 14);
		fontsize = JOptionPane.showInputDialog(null, "Enter Font Size", 12);
		int font12 = Integer.parseInt(fontsize);
		areafont = new Font(java.awt.Font.MONOSPACED, Font.BOLD, font12);
		btncolor = new Color(128, 111, 153);
		area.setBackground(areaback);
		area.setForeground(front);
		area.setFont(areafont);
		area.setCaretColor(front);
		file.setFont(font);
		bar.setBackground(btncolor);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text = area.getText();
				JFileChooser jfc = new JFileChooser();

				int jfcout = jfc.showSaveDialog(null);

				if (jfcout == JFileChooser.APPROVE_OPTION) {
					File seclectedFile = jfc.getSelectedFile();
					String FileName = seclectedFile.getAbsolutePath();
					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter(FileName));
						writer.write(text);
						writer.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Canceling...", "MESSAGE - C()D/\\", JOptionPane.CANCEL_OPTION);
				}
			}
		});
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				String filename = f.getAbsolutePath();

				try {
					FileReader read = new FileReader(filename);
					BufferedReader br = new BufferedReader(read);
					area.read(br, null);
					br.close();
					area.requestFocus();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		njava.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new CodaFrame("public class ClassName {" + "\n\tpublic static void main(String[] args) {" + "\n"
						+ "\n\t}" + "\n}");
			}
		});
		area.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();

				if (key == '{') {
					area.append("}\n");
				} else if (key == '(') {
					area.append("()");
				} else if (key == '"') {
					area.append("\"");
				} else if (key == '\'') {
					area.append("\'");
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

		});
	}
}
