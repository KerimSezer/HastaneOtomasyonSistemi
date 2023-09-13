package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.Bashekim;
import Model.Clinic;
import Model.User;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.nio.channels.NonReadableChannelException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import Helper.*;
import javax.swing.JComboBox;

public class BashekimGUI extends JFrame {
	static Bashekim bashekim = new Bashekim();
	Clinic clinic = new Clinic();
	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dTc;
	private JPasswordField fld_dPass;
	private JTextField fld_doctorID;
	private JTable table_doctor;

	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private JTable table_clinic;
	private JTextField fld_clinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;
	private JTable table_worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
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
	public BashekimGUI(Bashekim bashekim) throws SQLException {
		// Doctor Model
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];

		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC NO";
		colDoctorName[3] = "Şifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for (int i = 0; i < bashekim.getdoctorList().size(); i++) {
			doctorData[0] = bashekim.getdoctorList().get(i).getId();
			doctorData[1] = bashekim.getdoctorList().get(i).getName();
			doctorData[2] = bashekim.getdoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getdoctorList().get(i).getPassword();

			doctorModel.addRow(doctorData);

		}
		// Clinic Model
		clinicModel = new DefaultTableModel();
		Object[] colClinic = new Object[2];
		colClinic[0] = "ID";
		colClinic[1] = "Poliklinik Adı";
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData = new Object[2];
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);

		}
		// WorkerModel
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];

		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(135, 206, 250));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, Sayın " + bashekim.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 15, 258, 24);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		btnNewButton.setBounds(583, 15, 127, 30);
		w_pane.add(btnNewButton);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 78, 714, 359);
		w_pane.add(tabbedPane);

		JPanel w_tab = new JPanel();
		w_tab.setBackground(new Color(173, 216, 230));
		tabbedPane.addTab("Doktor Yönetimi", null, w_tab, null);
		w_tab.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setBounds(506, 0, 154, 23);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		w_tab.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("T.C No");
		lblNewLabel_1_1.setBounds(506, 55, 154, 22);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		w_tab.add(lblNewLabel_1_1);

		fld_dName = new JTextField();
		fld_dName.setBounds(506, 23, 154, 32);
		w_tab.add(fld_dName);
		fld_dName.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("Şifre");
		lblNewLabel_1_1_1.setBounds(506, 119, 154, 15);
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		w_tab.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_3 = new JLabel("Kullanıcı ID");
		lblNewLabel_1_1_3.setBounds(506, 231, 154, 23);
		lblNewLabel_1_1_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		w_tab.add(lblNewLabel_1_1_3);

		fld_dTc = new JTextField();
		fld_dTc.setBounds(506, 76, 154, 32);
		w_tab.add(fld_dTc);
		fld_dTc.setColumns(10);

		fld_dPass = new JPasswordField();
		fld_dPass.setBounds(506, 145, 154, 32);
		w_tab.add(fld_dPass);

		JButton btn_addDoctor = new JButton("Ekle");
		btn_addDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_dName.getText().length() == 0 || fld_dPass.getText().length() == 0
						|| fld_dTc.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = bashekim.addDoctor(fld_dTc.getText(), fld_dPass.getText(),
								fld_dName.getText());
						if (control) {
							Helper.showMsg("success");
							fld_dName.setText(null);
							fld_dTc.setText(null);
							fld_dPass.setText(null);
							updateDoctorModel();

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addDoctor.setBounds(506, 188, 154, 32);
		w_tab.add(btn_addDoctor);

		JButton btn_delDoctor = new JButton("Sil");
		btn_delDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorID.getText().length() == 0) {
					Helper.showMsg("Lütfen geçerli bir doktor seçiniz.");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_doctorID.getText());
						try {
							boolean control = bashekim.deleteDoctor(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_doctorID.setText(null);
								updateDoctorModel();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_delDoctor.setBounds(504, 297, 154, 32);
		w_tab.add(btn_delDoctor);

		fld_doctorID = new JTextField();
		fld_doctorID.setEditable(false);
		fld_doctorID.setBounds(506, 254, 154, 32);
		w_tab.add(fld_doctorID);
		fld_doctorID.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 482, 309);
		w_tab.add(scrollPane);

		table_doctor = new JTable(doctorModel);
		scrollPane.setViewportView(table_doctor);

		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_doctorID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {
					// TODO: handle exception
				}

			}
		});
		table_doctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectTcno = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectPass = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();
					try {
						boolean control = bashekim.updateDoctor(selectID, selectTcno, selectPass, selectName);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(new Color(173, 216, 230));
		tabbedPane.addTab("Poliklinikler", null, w_clinic, null);
		w_clinic.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(353, 5, 2, 2);
		w_clinic.add(scrollPane_1);

		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(10, 5, 261, 315);
		w_clinic.add(w_scrollClinic);
		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				Clinic selectClinic = clinic.getFetch(selID);
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
		});
		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
					try {
						if (clinic.deleteClinic(selID)) {
							Helper.showMsg("success");
							updateClinicModel();
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}

			}
		});

		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);

			}
		});
		w_scrollClinic.setViewportView(table_clinic);

		fld_clinicName = new JTextField();
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(278, 28, 142, 32);
		w_clinic.add(fld_clinicName);
		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fld_clinicName.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (clinic.addClinic(fld_clinicName.getText())) {
							Helper.showMsg("success");
							fld_clinicName.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		btn_addClinic.setFont(new Font("YU Gothic UI Semibold", Font.PLAIN, 14));
		btn_addClinic.setBounds(278, 71, 142, 35);
		w_clinic.add(btn_addClinic);

		JLabel lblNewLabel_1_2 = new JLabel("Poliklinik Adı");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(278, 5, 154, 23);
		w_clinic.add(lblNewLabel_1_2);

		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(430, 5, 269, 315);
		w_clinic.add(w_scrollWorker);

		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);

		JComboBox select_doctor = new JComboBox();
		select_doctor.setBounds(278, 247, 142, 32);
		for (int i = 0; i < bashekim.getdoctorList().size(); i++) {
			select_doctor.addItem(
					new Item(bashekim.getdoctorList().get(i).getId(), bashekim.getdoctorList().get(i).getName()));
		}
		select_doctor.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());
		});
		w_clinic.add(select_doctor);

		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					Item doctorItem = (Item) select_doctor.getSelectedItem();

					try {
						boolean control = bashekim.addWorker(doctorItem.getKey(), selClinicID);

						Helper.showMsg("success");
						if (control) {
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for (int i = 0; i < bashekim.getClinicDoctorList(selClinicID).size(); i++) {
								workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
								workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
								workerModel.addRow(workerData);
							}
							table_worker.setModel(workerModel);
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}

				} else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz !");
				}
			}

		});
		btn_addWorker.setBounds(278, 297, 142, 32);
		w_clinic.add(btn_addWorker);

		JLabel lblNewLabel_1_2_1 = new JLabel("Poliklinik Adı");
		lblNewLabel_1_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_2_1.setBounds(281, 113, 142, 23);
		w_clinic.add(lblNewLabel_1_2_1);

		JButton btn_workerSelect = new JButton("Seç");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < bashekim.getClinicDoctorList(selClinicID).size(); i++) {
							workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
							workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);
				} else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz.");
				}
			}
		});
		btn_workerSelect.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_workerSelect.setBounds(281, 136, 142, 35);
		w_clinic.add(btn_workerSelect);
	}

	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < bashekim.getdoctorList().size(); i++) {
			doctorData[0] = bashekim.getdoctorList().get(i).getId();
			doctorData[1] = bashekim.getdoctorList().get(i).getName();
			doctorData[2] = bashekim.getdoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getdoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
	}

	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();

			clinicModel.addRow(clinicData);

		}

	}
}
