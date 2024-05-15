package vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class ClienteConsulta extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	
	//pruebas funcionamiento
    private JTextField textFieldDocumento;
    private ArrayList<String> listaClientes;
    private JScrollPane scrollPane;
    private JTextArea textArea;
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
		 btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
	        btnBuscar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String documento = textFieldDocumento.getText();
	                buscarCliente(documento);
	            }
	        });
	        btnBuscar.setBounds(159, 123, 111, 34);
	        getContentPane().add(btnBuscar);
	        
	        JButton btnReserva = new JButton("Hacer reserva");
	        btnReserva.setIcon(new ImageIcon(ClienteConsulta.class.getResource("/Images/reserva24px.png")));
	        btnReserva.setForeground(new Color(255, 255, 255));
	        btnReserva.setBorderPainted(false);
	        btnReserva.setBackground(new Color(220, 20, 60));
	        btnReserva.setFont(new Font("Segoe UI", Font.BOLD, 14));
	        btnReserva.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		ReservaCreacion resCre = new ReservaCreacion();
					desktopPane.add(resCre);
					resCre.setVisible(true);
	        		
	        	}
	        });
	        btnReserva.setBounds(266, 342, 170, 40);
	        getContentPane().add(btnReserva);
	        
/**********************  Lista cliente ejemplo para pruebas ******************************************************/	

	        listaClientes = new ArrayList<>();
	        listaClientes.add("123456789,Juan,Perez,Gomez,555-1234,juan@example.com");
	        listaClientes.add("987654321,María,López,González,555-5678,maria@example.com");
	        listaClientes.add("111111111,Carlos,González,Rodríguez,555-1111,carlos@example.com");
	        listaClientes.add("222222222,Ana,Martínez,Sánchez,555-2222,ana@example.com");
	        listaClientes.add("333333333,Pablo,Fernández,López,555-3333,pablo@example.com");
	        listaClientes.add("444444444,Sofía,Ruiz,García,555-4444,sofia@example.com");
	        listaClientes.add("555555555,Miguel,Díaz,Pérez,555-5555,miguel@example.com");
	    }
/********************** MÉTODO BUSCAR CLIENTE ******************************************************/	
	
	private void buscarCliente(String documento) {
	    boolean encontrado = false;
	    for (int i = 0; i < listaClientes.size(); i++) {
	        String cliente = listaClientes.get(i);
	        String[] datosCliente = cliente.split(",");
	        if (datosCliente[0].equals(documento)) {
	            encontrado = true;

	            //mostrarInformacionCliente(cliente);
	            
	            
	            textArea.setText(
	            	    "INFORMACIÓN DEL CLIENTE:\n" +
	            	    "___________________________________________________________________________________\n\n" +
	            	    "DNI:\t\t" + datosCliente[0] + "\n" +
	            	    "NOMBRE:\t\t" + datosCliente[1] + "\n" +
	            	    "PRIMER APELLIDO:\t" + datosCliente[2] + "\n" +
	            	    "SEGUNDO APELLIDO:\t" + datosCliente[3] + "\n" +
	            	    "TELÉFONO:\t"+"\t" + datosCliente[4] + "\n" +
	            	    "CORREO ELECTRÓNICO:\t" + datosCliente[5]
	            	    );
	            
	            JOptionPane.showMessageDialog(this, "Cliente encontrado", "Información del cliente", JOptionPane.INFORMATION_MESSAGE);
	            
	            break;
	            
	           
	        }
	    }
	    if (!encontrado) {
	        // Si el cliente no se encuentra, lanzar el JDialog ConfirmacionCliente
	        ConfirmacionCliente confCli = new ConfirmacionCliente(getDesktopPane());
			//centrar en el escritorio
			confCli.setLocationRelativeTo(getDesktopPane());
	        confCli.setVisible(true);
	    }
	}
}
