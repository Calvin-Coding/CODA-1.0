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

public class CodaFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JMenuItem open;
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
	private JMenuItem n;

	public CodaFrame() {
		super("C()D/\\");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		try {
			setIconImage(ImageIO.read(getClass().getResourceAsStream("logo.png")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		n = new JMenuItem("New");
		area = new JTextArea("Welcome to Coda");
		bar = new JMenuBar();
		file = new JMenu("File");
		bar.add(file);
		file.add(n);
		file.addSeparator();
		file.add(save);
		file.add(open);
		setJMenuBar(bar);
		add(new JScrollPane(area), BorderLayout.CENTER);
		front = new Color(236, 236, 236);
		areaback = new Color(64, 80, 100);
		font = new Font(java.awt.Font.MONOSPACED, Font.BOLD, 12);
		fontsize = JOptionPane.showInputDialog(null, "Enter Font Size", 12);
		int font12 = Integer.parseInt(fontsize);
		areafont = new Font(java.awt.Font.MONOSPACED, Font.BOLD, font12);
		btncolor = new Color(128, 111, 153);
		area.setBackground(areaback);
		area.setForeground(front);
		area.setFont(areafont);
		area.setCaretColor(front);
		file.setFont(font);
		save.setFont(font);
		open.setFont(font);
		n.setFont(font);
		n.setBackground(btncolor);
		bar.setBackground(btncolor);
		open.setBackground(btncolor);
		save.setBackground(btncolor);
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
		n.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new CodaFrame();
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
