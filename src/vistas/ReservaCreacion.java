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
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
	private JComboBox<String> comboBox;
	private JRadioButton radioButtonDesayuno;
	private JRadioButton radioButtonMediaPension;
	private JRadioButton radioButtonPensionCompleta;
	private ButtonGroup grupoBoton;

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
		
		comboBox = new JComboBox<>();
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
		
		grupoBoton = new ButtonGroup();
		grupoBoton.add(radioButtonDesayuno);
		grupoBoton.add(radioButtonMediaPension);
		grupoBoton.add(radioButtonPensionCompleta);
		
		
		radioButtonDesayuno.setSelected(true);
		
		JButton btnRealizarReserva = new JButton("Realizar reserva");
		btnRealizarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int extra = 0;
				
				if (radioButtonDesayuno.isSelected()) {
		            extra = 1;
		        } else if (radioButtonMediaPension.isSelected()) {
		            extra = 2;
		        } else if (radioButtonPensionCompleta.isSelected()) {
		            extra = 3;
		        }
				
				crearReserva(extra);
			}
		});
		btnRealizarReserva.setIcon(new ImageIcon(ReservaCreacion.class.getResource("/Images/reserva24px.png")));
		btnRealizarReserva.setForeground(Color.WHITE);
		btnRealizarReserva.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnRealizarReserva.setBorderPainted(false);
		btnRealizarReserva.setBackground(new Color(220, 20, 60));
		btnRealizarReserva.setBounds(266, 380, 170, 40);
		getContentPane().add(btnRealizarReserva);

	}
	
/********************** MÉTODO CREAR RESERVA ******************************************************/	

	private void crearReserva(int extraNum) {
		controlador = new Controlador();
//		radioButtonDesayuno = new JRadioButton("Desayuno +10€");
//		radioButtonMediaPension = new JRadioButton("Media pensión +30€");
//		radioButtonPensionCompleta = new JRadioButton("Pensión completa +60€");
		
        // guardar los valores de los campos de texto
		String doc_identidad = textFieldDNI.getText();
		String tipoHabitacion = (String) comboBox.getSelectedItem();
//		btnRealizarReserva.addActionListener(e -> {
//            // Obtener el elemento seleccionado
//			tipoHabitacion = (String) comboBox.getSelectedItem();
//        });
        String fecha_entrada = textFieldFechaEntrada.getText();
        String fecha_salida = textFieldFechaSalida.getText();
        String extra = "";
        if (extraNum == 1) {
            extra = "Desayuno +10€";
        } else if (extraNum == 2) {
            extra = "Media pensión +30€";
        } else if (extraNum == 3) {
            extra = "Pensión completa +60€";
        }
        
     // Verificamos que no haya campos vacíos
        if (doc_identidad.isEmpty() || fecha_entrada.isEmpty() || fecha_salida.isEmpty()) {
            // lanza mensaje si hay alguno vacío
            JOptionPane.showMessageDialog(null, "Por favor, rellena todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
        	try {
        		// Comprobación de fecha de entrada y de salida
        		fecha_entrada = fecha_entrada.replace("/","-");
        		String[] valoresFechaEntrada = fecha_entrada.split("-");
        		LocalDate.of(Integer.valueOf(valoresFechaEntrada[0]),
        				Integer.valueOf(valoresFechaEntrada[1]),
        				Integer.valueOf(valoresFechaEntrada[2]));
        		
        		fecha_salida = fecha_salida.replace("/","-");
        		String[] valoresFechaSalida = fecha_salida.split("-");
        		LocalDate.of(Integer.valueOf(valoresFechaSalida[0]),
        				Integer.valueOf(valoresFechaSalida[1]),
        				Integer.valueOf(valoresFechaSalida[2]));
        		
        		if(LocalDate.of(Integer.valueOf(valoresFechaEntrada[0]),
        				Integer.valueOf(valoresFechaEntrada[1]),
        				Integer.valueOf(valoresFechaEntrada[2])).isAfter(
        			LocalDate.of(Integer.valueOf(valoresFechaSalida[0]),
        				Integer.valueOf(valoresFechaSalida[1]),
        				Integer.valueOf(valoresFechaSalida[2]))) ||
    				LocalDate.of(Integer.valueOf(valoresFechaEntrada[0]),
        				Integer.valueOf(valoresFechaEntrada[1]),
        				Integer.valueOf(valoresFechaEntrada[2])).isEqual(
        			LocalDate.of(Integer.valueOf(valoresFechaSalida[0]),
        				Integer.valueOf(valoresFechaSalida[1]),
        				Integer.valueOf(valoresFechaSalida[2])))) {
        			throw new Exception("La fecha de entrada no puede ser mayor o igual que la fecha de salida.");
        		}
        		
		        if(controlador.hayUnClienteConEsteDNI(doc_identidad).equals("Sí hay cliente con ese DNI")) {
			        // Crear el string de la reserva
			        String datosReserva = fecha_entrada + "," + fecha_salida + "," + doc_identidad;
		        
			        String resultadoInsercion = controlador.insertarReserva(datosReserva);
			        
			        String datosReservaFactura = datosReserva + "," + tipoHabitacion + "," + extra;
			        
					FacturaImpresion factImpr = new FacturaImpresion(datosReservaFactura);
					factImpr.setVisible(true);
			        
			        if(resultadoInsercion.equals("")) {
				        // Limpiar los campos de texto después de registrar el cliente(si no, no funciona)
			        	textFieldDNI.setText("");
				        textFieldFechaEntrada.setText("");
				        textFieldFechaSalida.setText("");
				        grupoBoton.clearSelection();
				        
				        // mensaje de éxito y cerrar el panel con dispose(igual que en utilizaciones anteriores)
				        JOptionPane.showMessageDialog(null, "Registro realizado con éxito");
				        dispose(); //quiero que limpie y que cierre el panel
			        }
		        } else if(controlador.hayUnClienteConEsteDNI(doc_identidad).equals("No hay ningún cliente con ese DNI")) {
		        	JOptionPane.showMessageDialog(null, "No hay ningún cliente con ese DNI.");
		        } else {
		        	JOptionPane.showMessageDialog(null, "Error de conexión.");
			        dispose(); //quiero que limpie y que cierre el panel
		        }
        	} catch(Exception e) {
        		e.printStackTrace();
        		
        		if(!e.getMessage().equals("La fecha de entrada no puede ser mayor o igual que la fecha de salida.")) {
        			JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Debe ser AAAA/MM/dd");
        		} else {
        			JOptionPane.showMessageDialog(null, e.getMessage());
        		}
        	}
        }

	}
}
