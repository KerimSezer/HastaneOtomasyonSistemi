package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

import Model.Doctor;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import Helper.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Appointment;
import Model.Doctor;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DoctorGUI extends JFrame {

	private JPanel contentPane;
	private static Doctor doctor = new Doctor();
	private JTable table_whour;
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private Color c0 = new Color(209, 242, 235);
	private Color c1 = new Color(163, 228, 215);
	private JTable table_doctorAppoint;
	private DefaultTableModel d_appointModel;
	private Object[] d_appointData = null;
	private Appointment appoint = new Appointment();
	private JPopupMenu d_appointMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public DoctorGUI(Doctor doctor) throws SQLException {
		d_appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[3];
		colAppoint[0] = "ID";
		colAppoint[1] = "Hasta";
		colAppoint[2] = "Tarih";
		d_appointModel.setColumnIdentifiers(colAppoint);
		d_appointData = new Object[3];
		try {
			for (int i = 0; i < appoint.getRandevuList(doctor.getId()).size(); i++) {
				d_appointData[0] = appoint.getRandevuList(doctor.getId()).get(i).getId();
				d_appointData[1] = appoint.getRandevuList(doctor.getId()).get(i).getHastaName();
				d_appointData[2] = appoint.getRandevuList(doctor.getId()).get(i).getAppDate();
				d_appointModel.addRow(d_appointData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getwdate();
			whourModel.addRow(whourData);
		}

		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, Sayın " + doctor.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(24, 11, 258, 24);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		btnNewButton.setBounds(597, 11, 127, 30);
		contentPane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 78, 714, 359);
		contentPane.add(w_tab);

		JPanel w_whour = new JPanel();
		w_whour.setBackground(new Color(173, 216, 230));
		w_tab.addTab("Çalışma Saatleri", null, w_whour, null);
		w_whour.setLayout(null);

		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(10, 11, 120, 20);
		w_whour.add(select_date);

		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(new String[] { "10:00", "10:30", "11:00", "11:30", "12:00",
				"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00" }));
		select_time.setBounds(140, 11, 65, 20);
		w_whour.add(select_time);

		JButton btn_addWhour = new JButton("Ekle");
		btn_addWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = "";
				try {
					date = sdf.format(select_date.getDate());
				} catch (Exception e2) {
					// TODO: handle excepiton
				}
				if (date.length() == 0) {
					Helper.showMsg("Lütfen geçerli bir tarih giriniz.");
				} else {
					String time = " " + select_time.getSelectedItem().toString() + ":00";
					String selectDate = date + time;
					boolean control;
					try {
						control = doctor.addwWhour(doctor.getId(), doctor.getName(), selectDate);
						if (control) {
							Helper.showMsg("success");
							updateWhourModel(doctor);
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		btn_addWhour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		btn_addWhour.setBounds(230, 11, 65, 20);
		w_whour.add(btn_addWhour);

		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(0, 42, 709, 289);
		w_whour.add(w_scrollWhour);

		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);

		JButton btn_deleteWhour = new JButton("Sil");
		btn_deleteWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if (selRow >= 0) {
					String selectRow = table_whour.getModel().getValueAt(selRow, 0).toString();
					int selID = Integer.parseInt(selectRow);
					boolean control;
					try {
						control = doctor.deleteWhour(selID);
						if (control) {
							Helper.showMsg("success");
							updateWhourModel(doctor);
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					Helper.showMsg("Lütfen bir tarih seçiniz.");
				}
			}
		});
		btn_deleteWhour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		btn_deleteWhour.setBounds(621, 11, 65, 20);
		w_whour.add(btn_deleteWhour);
	
	JPanel w_appointments = new JPanel();
	w_appointments.setBackground(new Color(173, 216, 230));
	w_tab.addTab("Randevular", null, w_appointments, null);
	w_appointments.setLayout(null);

	JScrollPane w_scrollAppoint = new JScrollPane();
	w_scrollAppoint.setBounds(10, 11, 649, 296);
	w_appointments.add(w_scrollAppoint);

	d_appointMenu = new JPopupMenu();
	JMenuItem deleteMenu = new JMenuItem("İptal Et");
	d_appointMenu.add(deleteMenu);
	deleteMenu.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String selDate = (String) table_doctorAppoint.getValueAt(table_doctorAppoint.getSelectedRow(), 2);
				String selHastaName = (String) table_doctorAppoint.getValueAt(table_doctorAppoint.getSelectedRow(),
						1);
				appoint.deleteAppoint(selDate, selHastaName);
				updateDAppointModel(doctor.getId());

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
	});

	table_doctorAppoint = new JTable(d_appointModel);
	table_doctorAppoint.setComponentPopupMenu(d_appointMenu);
	table_doctorAppoint.addMouseListener(new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			Point point = e.getPoint();
			try {
				int selectedRow = table_doctorAppoint.rowAtPoint(point);
				table_doctorAppoint.setRowSelectionInterval(selectedRow, selectedRow);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	});
	w_scrollAppoint.setViewportView(table_doctorAppoint);
}


	public void updateWhourModel(Doctor doctor) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getwdate();
			whourModel.addRow(whourData);
		}
	}
	public void updateDAppointModel(int doctor_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doctorAppoint.getModel();
		clearModel.setRowCount(0);
		try {
			for (int i = 0; i < appoint.getRandevuList(doctor_id).size(); i++) {
				d_appointData[0] = appoint.getRandevuList(doctor_id).get(i).getId();
				d_appointData[1] = appoint.getRandevuList(doctor_id).get(i).getHastaName();
				d_appointData[2] = appoint.getRandevuList(doctor_id).get(i).getAppDate();
				d_appointModel.addRow(d_appointData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

