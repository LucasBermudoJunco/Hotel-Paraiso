package vistas;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import operaciones.Controlador;

public class ReservaConsulta extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldDocumento;
    private JTextArea textArea;
	private Controlador controlador;
	@SuppressWarnings("unused")
	private final JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservaConsulta frame = new ReservaConsulta(desktopPane);
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
	public ReservaConsulta(JDesktopPane desktopPane) {
		this.desktopPane = desktopPane;
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setForeground(new Color(255, 255, 255));
		setFrameIcon(new ImageIcon(ReservaConsulta.class.getResource("/Images/reserva16px.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta reserva");
		setBounds(100, 100, 450, 423);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Introduce el número de la reserva:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel.setBounds(0, 43, 446, 45);
		getContentPane().add(lblNewLabel);
		
		textFieldDocumento = new JTextField();
		textFieldDocumento.setColumns(10);
		textFieldDocumento.setBounds(112, 82, 222, 30);
		getContentPane().add(textFieldDocumento);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setBounds(10, 168, 416, 163);
		getContentPane().add(scrollPane_1);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textArea.setEditable(false);
		textArea.setBackground(new Color(245, 245, 245));
		scrollPane_1.setViewportView(textArea);
		textArea.setEditable(false);//para que no se pueda escribir
		
		
		
/************************************* COMPORTAMIENTO DE BOTÓN ********************************************************************/ 		
		
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(ReservaConsulta.class.getResource("/Images/lupa24px.png")));
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnBuscar.setBorderPainted(false);
		btnBuscar.setBackground(new Color(30, 144, 255));
		btnBuscar.setBounds(159, 123, 111, 34);
		btnBuscar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	               String documento = textFieldDocumento.getText();
	               buscarReserva(documento);
	           }
        });
		getContentPane().add(btnBuscar);
		
		JButton btnReservaFactura = new JButton("Hacer factura");
		btnReservaFactura.setIcon(new ImageIcon(ReservaConsulta.class.getResource("/Images/factura24px.png")));
		btnReservaFactura.setForeground(Color.WHITE);
		btnReservaFactura.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnReservaFactura.setBorderPainted(false);
		btnReservaFactura.setBackground(new Color(154, 205, 50));
		btnReservaFactura.setBounds(256, 342, 170, 40);
		getContentPane().add(btnReservaFactura);

	}
	
/********************** MÉTODO BUSCAR RESERVA ******************************************************/	

	private void buscarReserva(String documento) {
		controlador = new Controlador();
		
		if (documento.isEmpty()) {
            // lanza mensaje si hay alguno vacío
            JOptionPane.showMessageDialog(null, "Por favor, rellena el campo.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
    		String infoReserva = controlador.obtenerInfoReservaConEsteCodigo(documento);
        	
			if(!infoReserva.isEmpty()) {
				String[] datosReserva = infoReserva.split(",");
				
				//mostrarInformacionCliente(cliente);
		        textArea.setText(
		        	    "INFORMACIÓN DEL CLIENTE:\n" +
		        	    "___________________________________________________________________________________\n\n" +
		        	    "ID:\t\t" + datosReserva[0] + "\n" +
		        	    "HABITACIÓN:\t\t" + datosReserva[1] + "\n" +
		        	    "FECHA DE ENTRADA:\t" + datosReserva[2] + "\n" +
		        	    "FECHA DE SALIDA:\t" + datosReserva[3] + "\n" +
		        	    "CÓDIGO DE CLIENTE:\t" + datosReserva[4]
		        );
		        
		        JOptionPane.showMessageDialog(this, "Reserva encontrada", "Información de la reserva", JOptionPane.INFORMATION_MESSAGE);
		        
			} else {
				// Si el cliente no se encuentra, lanzar el JDialog ConfirmacionCliente
		        ConfirmacionCliente confCli = new ConfirmacionCliente(getDesktopPane());
				// Centrar en el escritorio
				confCli.setLocationRelativeTo(getDesktopPane());
		        confCli.setVisible(true);
			}
        }
	}
}
