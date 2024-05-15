package vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JPanel;

public class ReservaCreacion extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservaCreacion frame = new ReservaCreacion();
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
	public ReservaCreacion() {
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setClosable(true);
		setIconifiable(true);
		setFrameIcon(new ImageIcon(ReservaCreacion.class.getResource("/Images/reserva16px.png")));
		setTitle("Realizar reserva");
		setBounds(100, 100, 450, 460);
		getContentPane().setLayout(null);
		
		JLabel lblDni = new JLabel("Dni");
		lblDni.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblDni.setBounds(25, 44, 76, 14);
		getContentPane().add(lblDni);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(25, 65, 232, 28);
		getContentPane().add(textField);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Económica", "Estándar", "Suite"}));
		comboBox.setBounds(165, 110, 92, 22);
		getContentPane().add(comboBox);
		
		JLabel lblTipoDeHabitacin = new JLabel("Tipo de habitación:");
		lblTipoDeHabitacin.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblTipoDeHabitacin.setBounds(25, 104, 144, 28);
		getContentPane().add(lblTipoDeHabitacin);
		
		JLabel lblFechaEntrada = new JLabel("Fecha entrada:");
		lblFechaEntrada.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblFechaEntrada.setBounds(25, 152, 144, 28);
		getContentPane().add(lblFechaEntrada);
		
		JLabel lblFechaSalidad = new JLabel("Fecha salidad:");
		lblFechaSalidad.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblFechaSalidad.setBounds(25, 191, 144, 28);
		getContentPane().add(lblFechaSalidad);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(133, 154, 232, 28);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(133, 194, 232, 28);
		getContentPane().add(textField_2);
		
		JLabel lblExtras = new JLabel("Extras:");
		lblExtras.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblExtras.setBounds(25, 243, 144, 28);
		getContentPane().add(lblExtras);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Extra1");
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		chckbxNewCheckBox.setBounds(25, 281, 97, 23);
		getContentPane().add(chckbxNewCheckBox);
		
		JCheckBox chckbxExtra = new JCheckBox("Extra2");
		chckbxExtra.setBackground(Color.WHITE);
		chckbxExtra.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		chckbxExtra.setBounds(168, 281, 97, 23);
		getContentPane().add(chckbxExtra);
		
		JCheckBox chckbxExtra_1 = new JCheckBox("Extra3");
		chckbxExtra_1.setBackground(Color.WHITE);
		chckbxExtra_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		chckbxExtra_1.setBounds(304, 281, 97, 23);
		getContentPane().add(chckbxExtra_1);
		
		JCheckBox chckbxExtra_1_1 = new JCheckBox("Extra6");
		chckbxExtra_1_1.setBackground(Color.WHITE);
		chckbxExtra_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		chckbxExtra_1_1.setBounds(304, 307, 97, 23);
		getContentPane().add(chckbxExtra_1_1);
		
		JCheckBox chckbxExtra_4 = new JCheckBox("Extra5");
		chckbxExtra_4.setBackground(Color.WHITE);
		chckbxExtra_4.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		chckbxExtra_4.setBounds(168, 307, 97, 23);
		getContentPane().add(chckbxExtra_4);
		
		JCheckBox chckbxExtra_3 = new JCheckBox("Extra4");
		chckbxExtra_3.setBackground(Color.WHITE);
		chckbxExtra_3.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		chckbxExtra_3.setBounds(25, 307, 97, 23);
		getContentPane().add(chckbxExtra_3);
		
		JButton btnRealizarReserva = new JButton("Realizar reserva");
		btnRealizarReserva.setIcon(new ImageIcon(ReservaCreacion.class.getResource("/Images/reserva24px.png")));
		btnRealizarReserva.setForeground(Color.WHITE);
		btnRealizarReserva.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnRealizarReserva.setBorderPainted(false);
		btnRealizarReserva.setBackground(new Color(220, 20, 60));
		btnRealizarReserva.setBounds(266, 380, 170, 40);
		getContentPane().add(btnRealizarReserva);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBounds(0, 269, 458, 72);
		getContentPane().add(panel);

	}
}
