package View;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.*;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_doctorTc;
	private JPasswordField fld_doctorPass;
	private DBConnection conn = new DBConnection();
	private JPasswordField fld_hastaPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(135, 206, 250));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("hosp.png")));
		lbl_logo.setBounds(212, 11, 67, 55);
		w_pane.add(lbl_logo);

		JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemine Hoşgeldiniz");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel.setBounds(92, 71, 306, 30);
		w_pane.add(lblNewLabel);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 127, 464, 223);
		w_pane.add(w_tabpane);

		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(new Color(173, 216, 230));
		w_tabpane.addTab("Hasta Girişi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("T.C NUMARANIZ:");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(21, 38, 146, 30);
		w_hastaLogin.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("ŞİFRE:");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(21, 97, 55, 30);
		w_hastaLogin.add(lblNewLabel_1_1);

		fld_hastaTc = new JTextField();
		fld_hastaTc.setBounds(161, 38, 241, 30);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);

		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setBounds(10, 138, 203, 57);
		w_hastaLogin.add(btn_register);

		JButton btn_hastaLogin = new JButton("Giriş Yap");
		btn_hastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_hastaTc.getText().length() == 0 || fld_hastaPass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					boolean key = true;
					try {
						Connection con = conn.connDb();
						java.sql.Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT*FROM user");
						while (rs.next()) {
							if (fld_hastaTc.getText().equals(rs.getString("tcno"))
									&& fld_hastaPass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("hasta")) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPassword("password");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key = false;

								}

							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (key) {
						Helper.showMsg("Böyle bir hasta bulunamadı lütfen kayıt olunuz !");
					}
				}
			}
		});
		btn_hastaLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_hastaLogin.setBounds(231, 138, 203, 57);
		w_hastaLogin.add(btn_hastaLogin);

		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setBounds(161, 91, 241, 30);
		w_hastaLogin.add(fld_hastaPass);

		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(new Color(173, 216, 230));
		w_tabpane.addTab("Doktor Girişi", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);

		JButton btn_doctorLogin = new JButton("Giriş Yap");
		btn_doctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorTc.getText().length() == 0 || fld_doctorPass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						Connection con = conn.connDb();
						java.sql.Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT*FROM user");
						while (rs.next()) {
							if (fld_doctorTc.getText().equals(rs.getString("tcno"))
									&& fld_doctorPass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("bashekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword("password");
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									System.out.println(bhekim.getName());
									BashekimGUI bGui = new BashekimGUI(bhekim);
									bGui.setVisible(true);
									dispose();

								}

								if (rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
								}
							}
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
			}
		});
		btn_doctorLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_doctorLogin.setBounds(42, 127, 371, 57);
		w_doctorLogin.add(btn_doctorLogin);

		JLabel lblNewLabel_1_1_1 = new JLabel("ŞİFRE:");
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_1_1_1.setBounds(21, 77, 55, 27);
		w_doctorLogin.add(lblNewLabel_1_1_1);

		fld_doctorTc = new JTextField();
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(161, 27, 241, 30);
		w_doctorLogin.add(fld_doctorTc);

		JLabel lblNewLabel_1_2 = new JLabel("T.C NUMARANIZ:");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_1_2.setBounds(21, 27, 146, 30);
		w_doctorLogin.add(lblNewLabel_1_2);

		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(161, 77, 241, 30);
		w_doctorLogin.add(fld_doctorPass);
	}
}
