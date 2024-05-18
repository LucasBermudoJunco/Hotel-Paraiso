package vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import operaciones.Controlador;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class ReservaCreacion extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldDNI;
	private JTextField textFieldFechaEntrada;
	private JTextField textFieldFechaSalida;
	private Controlador controlador;

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
		
		textFieldDNI = new JTextField();
		textFieldDNI.setColumns(10);
		textFieldDNI.setBounds(25, 65, 232, 28);
		getContentPane().add(textFieldDNI);
		
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
		
		JLabel lblFechaSalida = new JLabel("Fecha salida:");
		lblFechaSalida.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblFechaSalida.setBounds(25, 191, 144, 28);
		getContentPane().add(lblFechaSalida);
		
		textFieldFechaEntrada = new JTextField();
		textFieldFechaEntrada.setColumns(10);
		textFieldFechaEntrada.setBounds(133, 154, 232, 28);
		getContentPane().add(textFieldFechaEntrada);
		
		textFieldFechaSalida = new JTextField();
		textFieldFechaSalida.setColumns(10);
		textFieldFechaSalida.setBounds(133, 194, 232, 28);
		getContentPane().add(textFieldFechaSalida);
		
		JLabel lblExtras = new JLabel("Extras:");
		lblExtras.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblExtras.setBounds(25, 250, 144, 28);
		getContentPane().add(lblExtras);
		
		JButton btnRealizarReserva = new JButton("Realizar reserva");
		btnRealizarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearReserva();
				FacturaImpresion factImpr = new FacturaImpresion();
				factImpr.setVisible(true);
			}
		});
		btnRealizarReserva.setIcon(new ImageIcon(ReservaCreacion.class.getResource("/Images/reserva24px.png")));
		btnRealizarReserva.setForeground(Color.WHITE);
		btnRealizarReserva.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnRealizarReserva.setBorderPainted(false);
		btnRealizarReserva.setBackground(new Color(220, 20, 60));
		btnRealizarReserva.setBounds(266, 380, 170, 40);
		getContentPane().add(btnRealizarReserva);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBounds(0, 288, 458, 40);
		getContentPane().add(panel);
		
		JRadioButton radioButtonDesayuno = new JRadioButton("Desayuno +10€");
		panel.add(radioButtonDesayuno);
		
		JRadioButton radioButtonMediaPension = new JRadioButton("Media pensión +30€");
		panel.add(radioButtonMediaPension);
		
		JRadioButton radioButtonPensionCompleta = new JRadioButton("Pensión completa +60€");
		panel.add(radioButtonPensionCompleta);
		
		ButtonGroup grupoBoton = new ButtonGroup();
		grupoBoton.add(radioButtonDesayuno);
		grupoBoton.add(radioButtonMediaPension);
		grupoBoton.add(radioButtonPensionCompleta);

	}
	
/********************** MÉTODO CREAR RESERVA ******************************************************/	

	private void crearReserva() {
		controlador = new Controlador();
		
        // guardar los valores de los campos de texto
		String id_res = textFieldDni.getText();
        String habitacion = textFieldNombre.getText();
        String fecha_entrada = textFieldApellidos.getText();
        String fecha_salida = textFieldTelefono.getText();
        String doc_identidad = textFieldMail.getText();

	}
}
