package vistas;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import operaciones.Controlador;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class ClienteConsulta extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private Controlador controlador;
	
	//pruebas funcionamiento
    private JTextField textFieldDocumento;
    private JTextArea textArea;
	@SuppressWarnings("unused")
	private final JDesktopPane desktopPane;
    
    
    
	
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteConsulta frame = new ClienteConsulta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public ClienteConsulta(JDesktopPane desktopPane) {
		setForeground(Color.WHITE);
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		this.desktopPane = desktopPane;
		
		setIconifiable(true);
		getContentPane().setBackground(new Color(245, 245, 245));
		setClosable(true);
		setFrameIcon(new ImageIcon(ClienteConsulta.class.getResource("/Images/cliente16px.png")));
		setTitle("Consulta cliente");
		setBounds(100, 100, 450, 423);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Introduce número de documento:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 43, 436, 45);
		getContentPane().add(lblNewLabel);
		
		textFieldDocumento = new JTextField();
        textFieldDocumento.setBounds(116, 82, 222, 30);
        getContentPane().add(textFieldDocumento);
        textFieldDocumento.setColumns(10);
        

        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        scrollPane_1.setBounds(10, 168, 426, 163);
        getContentPane().add(scrollPane_1);
        
        textArea = new JTextArea();
        textArea.setBackground(new Color(245, 245, 245));
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        scrollPane_1.setViewportView(textArea);
        textArea.setEditable(false);//para que no se pueda escribir
        
        

/************************************* COMPORTAMIENTO DE BOTÓN ********************************************************************/        
        
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(ClienteConsulta.class.getResource("/Images/lupa24px.png")));
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBorderPainted(false);
		btnBuscar.setBackground(new Color(30, 144, 255));
		btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 12));
	    btnBuscar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	               buscarCliente();
	           }
        });
        btnBuscar.setBounds(137, 123, 180, 34);
        getContentPane().add(btnBuscar);
	        
        JButton btnReserva = new JButton("Hacer reserva");
        btnReserva.setIcon(new ImageIcon(ClienteConsulta.class.getResource("/Images/reserva24px.png")));
        btnReserva.setForeground(new Color(255, 255, 255));
        btnReserva.setBorderPainted(false);
        btnReserva.setBackground(new Color(220, 20, 60));
        btnReserva.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnReserva.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		ReservaCreacion resCre = new ReservaCreacion();
				desktopPane.add(resCre);
				resCre.setVisible(true);
        		
        	}
        });
        btnReserva.setBounds(214, 342, 222, 40);
        getContentPane().add(btnReserva);
	}
	
/********************** MÉTODO BUSCAR CLIENTE ******************************************************/	
	
	private void buscarCliente() {
		controlador = new Controlador();
		
		String documento = textFieldDocumento.getText();
		
		if (documento.isEmpty()) {
            // lanza mensaje si hay alguno vacío
            JOptionPane.showMessageDialog(null, "Por favor, rellena el campo.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if(documento.length() > 9) {
        	// lanza mensaje si el DNI es más largo de lo permitido
            JOptionPane.showMessageDialog(null, "El DNI no puede tener más de 9 caracteres (incluyendo espacios).", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
    		String infoCliente = controlador.obtenerInfoClienteConEsteDNI(documento);
    		
			if(infoCliente.equals("error de conexión")) {
				JOptionPane.showMessageDialog(null, "Error de conexión.");
		        dispose(); //quiero que limpie y que cierre el panel
			} else if(infoCliente.equals("No hay ningún cliente con ese DNI")) {
				// Si el cliente no se encuentra, lanzar el JDialog ConfirmacionCliente
		        ConfirmacionCliente confCli = new ConfirmacionCliente(getDesktopPane(),textFieldDocumento.getText());
				// Centrar en el escritorio
				confCli.setLocationRelativeTo(getDesktopPane());
		        confCli.setVisible(true);
			} else {
				String[] datosCliente = infoCliente.split(",");
				
				//mostrarInformacionCliente(cliente);
		        textArea.setText(
		        	    "INFORMACIÓN DEL CLIENTE:\n" +
		        	    "___________________________________________________________________________________\n\n" +
		        	    "DNI:\t\t" + datosCliente[0] + "\n" +
		        	    "NOMBRE:\t\t" + datosCliente[1] + "\n" +
		        	    "APELLIDOS:\t\t" + datosCliente[2] + "\n" +
		        	    "TELÉFONO:\t"+"\t" + datosCliente[3] + "\n" +
		        	    "CORREO ELECTRÓNICO:\t" + datosCliente[4]
		        );
		        
		        JOptionPane.showMessageDialog(this, "Cliente encontrado", "Información del cliente", JOptionPane.INFORMATION_MESSAGE);
		        
			}
        }
	}
}
