package edu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Osobe extends JFrame {
	
	private ArrayList<Osoba> osobe = new ArrayList<Osoba>();
	private JPanel frmOsobe;
	private JTextField txtImePrezime;
	private JTextField txtGodiste;
	private JTextField txtImePrezimePrvi;
	private JTextField txtGodistePrvi;
	private JTextArea tAreaOsobe;
	private JRadioButton rdPrezimeIme;
	private JRadioButton rdGodiste;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Osobe frame = new Osobe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Osobe() {
		setTitle("Osobe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 535);
		frmOsobe = new JPanel();
		frmOsobe.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frmOsobe);
		frmOsobe.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pnlUnos = new JPanel();
		frmOsobe.add(pnlUnos);
		
		JPanel pnlImePrezime = new JPanel();
		pnlUnos.add(pnlImePrezime);
		
		JLabel lblImePrezime = new JLabel("Ime i prezime:");
		pnlImePrezime.add(lblImePrezime);
		
		txtImePrezime = new JTextField();
		pnlImePrezime.add(txtImePrezime);
		txtImePrezime.setColumns(10);
		
		JPanel pnlGodiste = new JPanel();
		pnlUnos.add(pnlGodiste);
		
		JLabel lblGodiste = new JLabel("godiste:");
		pnlGodiste.add(lblGodiste);
		
		txtGodiste = new JTextField();
		pnlGodiste.add(txtGodiste);
		txtGodiste.setColumns(10);
		
		JPanel pnlDugmad = new JPanel();
		frmOsobe.add(pnlDugmad);
		
		JButton btnUnesi = new JButton("Unesi");
		btnUnesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String imePrezime = txtImePrezime.getText();
				String [] s = imePrezime.split("\\s+");
				
				if(s.length != 2) {
					JOptionPane.showMessageDialog(frmOsobe,"Nepravilan unos imena i prezimena!","Alert",JOptionPane.WARNING_MESSAGE);
					txtImePrezime.setText("");
					return;
				}
				
				String ime = s[0];
				String prezime = s[1];
				
				String sGodiste = txtGodiste.getText();
				int godiste;
				
				try {
					godiste = Integer.parseInt(sGodiste);
					
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(frmOsobe,"Nepravilan unos godista!","Alert",JOptionPane.WARNING_MESSAGE);
					txtGodiste.setText("");
					return;
					}
				
				if(sGodiste.length() != 4) {
					JOptionPane.showMessageDialog(frmOsobe,"Nepravilan unos godista!","Alert",JOptionPane.WARNING_MESSAGE);
					txtGodiste.setText("");
					return;
				}
				
				Osoba osoba = new Osoba(ime, prezime,  godiste);
				osobe.add(osoba);
				txtImePrezime.setText("");
				txtGodiste.setText("");
			}
		});
		pnlDugmad.add(btnUnesi);
		
		JButton btnPonisti = new JButton("Ponisti");
		btnPonisti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtImePrezime.setText("");
				txtGodiste.setText("");
			}
		});
		pnlDugmad.add(btnPonisti);
		
		JPanel pnlKriterijumi = new JPanel();
		pnlKriterijumi.setBorder(new TitledBorder(null, "sortiranje", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmOsobe.add(pnlKriterijumi);
		pnlKriterijumi.setLayout(new GridLayout(0, 1, 0, 0));
		
		rdPrezimeIme = new JRadioButton("po prezimenu i imenu");
		rdPrezimeIme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdPrezimeIme.isSelected()) {
					rdGodiste.setSelected(false);
				}
			}
		});
		rdPrezimeIme.setSelected(true);
		pnlKriterijumi.add(rdPrezimeIme);
		
		rdGodiste = new JRadioButton("po godinama starosti, rastuce");
		rdGodiste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdGodiste.isSelected()) {
					rdPrezimeIme.setSelected(false);
				}
			}
		});
		pnlKriterijumi.add(rdGodiste);
		
		JPanel pnlSortirajIspisi = new JPanel();
		frmOsobe.add(pnlSortirajIspisi);
		
		JButton btnSortirajIspisi = new JButton("Sortiraj i ispisi rezultat");
		btnSortirajIspisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(osobe.isEmpty()) {
					tAreaOsobe.setText("Kolekcija je prazna");
				}
				if(rdPrezimeIme.isSelected()) {
					Osoba.isLeksikografski = true;
					rdGodiste.setSelected(false);
				} else if (rdGodiste.isSelected()) {
					Osoba.isLeksikografski = false;
					rdPrezimeIme.setSelected(false);
				}
				Collections.sort(osobe);
				Osoba prvi = osobe.get(0);
				txtImePrezimePrvi.setText(prvi.getIme() + " " + prvi.getPrezime());
				txtGodistePrvi.setText(String.valueOf(prvi.getGodiste()));
			
			StringBuffer sb = new StringBuffer();
			
			for (Osoba o: osobe) {
				sb.append(o);
				sb.append("\n");
			}
			tAreaOsobe.setText(sb.toString());
			}
		});
		pnlSortirajIspisi.add(btnSortirajIspisi);
		
		JPanel pnlIspisPrvog = new JPanel();
		frmOsobe.add(pnlIspisPrvog);
		
		JPanel lblImePrezimePrvi = new JPanel();
		pnlIspisPrvog.add(lblImePrezimePrvi);
		
		JLabel label = new JLabel("Ime i prezime:");
		lblImePrezimePrvi.add(label);
		
		txtImePrezimePrvi = new JTextField();
		txtImePrezimePrvi.setEditable(false);
		txtImePrezimePrvi.setColumns(10);
		txtImePrezimePrvi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Font f = txtImePrezimePrvi.getFont();
				int newFontSize = f.getSize() + 5;
				txtImePrezimePrvi.setFont(new Font(f.getName(), Font.PLAIN, newFontSize));
				txtImePrezimePrvi.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtImePrezimePrvi.setFont(null);
				txtImePrezimePrvi.setForeground(null);
			}
		});
		lblImePrezimePrvi.add(txtImePrezimePrvi);
		
		JPanel pnlGodistePrvi = new JPanel();
		pnlIspisPrvog.add(pnlGodistePrvi);
		
		JLabel lblGodistePrvi = new JLabel("godiste:");
		pnlGodistePrvi.add(lblGodistePrvi);
	
		txtGodistePrvi = new JTextField();
		txtGodistePrvi.setEditable(false);
		txtGodistePrvi.setColumns(10);
		txtGodistePrvi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Font f = txtGodistePrvi.getFont();
				int newFontSize = f.getSize() + 5;
				txtGodistePrvi.setFont(new Font(f.getName(), Font.PLAIN, newFontSize));
				txtGodistePrvi.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtGodistePrvi.setFont(null);
				txtGodistePrvi.setForeground(null);
			}
		});
		pnlGodistePrvi.add(txtGodistePrvi);
		
		JScrollPane scrollOsobe = new JScrollPane();
		frmOsobe.add(scrollOsobe);
		
		tAreaOsobe = new JTextArea();
		scrollOsobe.setViewportView(tAreaOsobe);
		
		
		
	
		
	
	}

}
