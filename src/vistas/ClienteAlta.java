package vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import operaciones.Controlador;

public class ClienteAlta extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTextField textFieldTelefono;
	private JTextField textFieldMail;
	
	
	//para guardar datos alta cliente
	//private ArrayList<String> listaClientes;
	private JTextField textFieldDni;
	
	private Controlador controlador;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteAlta frame = new ClienteAlta();
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
	public ClienteAlta() {
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().setBackground(new Color(245, 245, 245));
		setClosable(true);
		setIconifiable(true);
		setFrameIcon(new ImageIcon(ClienteAlta.class.getResource("/Images/cliente16px.png")));
		setTitle("Alta cliente");
		setBounds(100, 100, 450, 444);
		getContentPane().setLayout(null);
		
		JLabel altaClienteNombre = new JLabel("Nombre:");
		altaClienteNombre.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		altaClienteNombre.setBounds(35, 71, 76, 14);
		getContentPane().add(altaClienteNombre);
		
		JLabel altaClienteApellido1 = new JLabel("Apellidos:");
		altaClienteApellido1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		altaClienteApellido1.setBounds(35, 127, 129, 21);
		getContentPane().add(altaClienteApellido1);
		
		JLabel altaClienteTelefono = new JLabel("Teléfono:");
		altaClienteTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		altaClienteTelefono.setBounds(35, 253, 91, 14);
		getContentPane().add(altaClienteTelefono);
		
		JLabel lblNewLabel_4 = new JLabel("Mail:");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(35, 313, 106, 14);
		getContentPane().add(lblNewLabel_4);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(35, 92, 232, 28);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellidos = new JTextField();
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setBounds(35, 153, 232, 28);
		getContentPane().add(textFieldApellidos);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(35, 274, 232, 28);
		getContentPane().add(textFieldTelefono);
		
		textFieldMail = new JTextField();
		textFieldMail.setColumns(10);
		textFieldMail.setBounds(35, 331, 232, 28);
		getContentPane().add(textFieldMail);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setIcon(new ImageIcon(ClienteAlta.class.getResource("/Images/cliente24px.png")));
		btnRegistrar.setBorderPainted(false);
		btnRegistrar.setBackground(new Color(143, 188, 143));
		btnRegistrar.setForeground(new Color(255, 255, 255));
		btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnRegistrar.setBounds(289, 367, 135, 36);
		getContentPane().add(btnRegistrar);
		
		JLabel lblDni = new JLabel("Dni:");
		lblDni.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblDni.setBounds(35, 11, 76, 14);
		getContentPane().add(lblDni);
		
		textFieldDni = new JTextField();
		textFieldDni.setColumns(10);
		textFieldDni.setBounds(35, 32, 232, 28);
		getContentPane().add(textFieldDni);

		btnRegistrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	darDeAltaAUnCliente();
		    }
		});
	}

/********************** MÉTODO DAR DE ALTA A UN CLIENTE ******************************************************/	
		
	public void darDeAltaAUnCliente() {
		controlador = new Controlador();
		
        // guardar los valores de los campos de texto
    	String dni = textFieldDni.getText();
        String nombre = textFieldNombre.getText();
        String apellidos = textFieldApellidos.getText();
        String telefono = textFieldTelefono.getText();
        String mail = textFieldMail.getText();
        
    	// Verificamos que no haya campos vacíos
        if (dni.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || mail.isEmpty()) {
            // lanza mensaje si hay alguno vacío
            JOptionPane.showMessageDialog(null, "Por favor, rellena todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
	        // Crear el string del cliente y agregarlo a la lista(segun modelo de prueba)
	        String datosCliente = dni + "," + nombre + "," + apellidos + "," + telefono + "," + mail;
	        
	        controlador.insertarCliente(datosCliente);
	
	        // Limpiar los campos de texto después de registrar el cliente(si no, no funciona)
	        textFieldDni.setText("");
	        textFieldNombre.setText("");
	        textFieldApellidos.setText("");
	        textFieldTelefono.setText("");
	        textFieldMail.setText("");
	        
	        // mensaje de éxito y cerrar el panel con dispose(igual que en utilizaciones anteriores)
	        JOptionPane.showMessageDialog(null, "Registro realizado con éxito");
	        //dispose(); --quiero que limpie, pero que no cierre el panel...por ahora
        }
	}
}
