package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;
import Model.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JPasswordField fld_pass;
	private Hasta hasta = new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(173, 216, 230));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 11, 154, 23);
		w_pane.add(lblNewLabel_1);

		fld_name = new JTextField();
		fld_name.setColumns(10);
		fld_name.setBounds(10, 34, 264, 32);
		w_pane.add(fld_name);

		JLabel lblNewLabel_1_1 = new JLabel("T.C Numarası");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 64, 154, 23);
		w_pane.add(lblNewLabel_1_1);

		fld_tcno = new JTextField();
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 98, 264, 32);
		w_pane.add(fld_tcno);

		JLabel lblNewLabel_1_1_1 = new JLabel("Şifre");
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(10, 141, 154, 23);
		w_pane.add(lblNewLabel_1_1_1);

		fld_pass = new JPasswordField();
		fld_pass.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 11));
		fld_pass.setBounds(10, 175, 264, 32);
		w_pane.add(fld_pass);

		JButton btn_backto = new JButton("Geri Dön");
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_backto.setBounds(10, 248, 264, 32);
		w_pane.add(btn_backto);

		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_tcno.getText().length() == 0 || fld_pass.getText().length() == 0
						|| fld_name.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = hasta.register(fld_tcno.getText(), fld_pass.getText(), fld_name.getText());
						if (control) {
							Helper.showMsg("success");
							LoginGUI login = new LoginGUI();
							login.setVisible(true);
							dispose();

						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
			}
		});
		btn_register.setBounds(10, 211, 264, 32);
		w_pane.add(btn_register);
	}
}
