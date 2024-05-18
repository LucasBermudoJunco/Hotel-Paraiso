package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Insets;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JDesktopPane desktopPane;
	//Con esta vriable consigo ajustar los margenes del logo respecto a la pantalla
	private final int MARGIN = 20; // Controlo directamente desde aqui cambiando el valor
	
	//para evitar diplicidaes en paneles activos
	private boolean clientePanelActivo = false;
    private boolean reservaPanelActivo = false;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					//Activar pantalla completa
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
	public MenuPrincipal() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/Images/iconoLogo.png")));
		setFont(new Font("Arial", Font.PLAIN, 15));
		setType(Type.POPUP);
		setTitle("Hotel Paradiso V0.0");
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 800);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.WHITE);
		menuBar.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 16));
		menuBar.setBackground(new Color(30, 144, 255));
		menuBar.setMargin(new Insets(20, 20, 20, 0));
		setJMenuBar(menuBar);
		
		
		//MENU SISTEMA
		JMenu menuSistema = new JMenu("Sistema    ");
		menuSistema.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Images/sistema.png")));
		menuSistema.setForeground(Color.WHITE);
		menuSistema.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(menuSistema);
		
//Panel SISTEMA salir
/*************************************************************************************/				

		JMenuItem menuSalir = new JMenuItem("Salir");
		menuSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConfirmacionSistema confSist = new ConfirmacionSistema(MenuPrincipal.this);
				//centrar en el escritorio
				confSist.setLocationRelativeTo(desktopPane);
				
				confSist.setVisible(true);
				
			}
		});
		menuSalir.setBackground(new Color(255, 255, 255));
		menuSalir.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuSistema.add(menuSalir);

/*************************************************************************************/			
		
		//MENU CLIENTE
		JMenu menuCliente = new JMenu("Clientes    ");
		menuCliente.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Images/cliente.png")));
		menuCliente.setForeground(Color.WHITE);
		menuCliente.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(menuCliente);

		
//Panel CLIENTE consulta en desktop
/*************************************************************************************/				
		
		JMenuItem menuClienteConsulta = new JMenuItem("Consulta");
		menuClienteConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteConsulta cliCons = new ClienteConsulta(desktopPane);
				desktopPane.add(cliCons);
				//control de posición del panel
				cliCons.setLocation(20, 20);
				cliCons.setVisible(true);
				
			}
		});
		menuClienteConsulta.setBackground(new Color(255, 255, 255));
		menuClienteConsulta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuCliente.add(menuClienteConsulta);
		
//Panel CLIENTE alta en desktop
/*************************************************************************************/		
		JMenuItem menuClienteAlta = new JMenuItem("Alta");
		menuClienteAlta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClienteAlta cliAlt = new ClienteAlta("");
                desktopPane.add(cliAlt);
				//control de posición del panel
				cliAlt.setLocation(40, 40);
                cliAlt.setVisible(true);
            }
		});
		
		
		menuClienteAlta.setBackground(new Color(255, 255, 255));
		menuClienteAlta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuCliente.add(menuClienteAlta);
/*************************************************************************************/	
		
		
		
		//MENU RESERVA
		JMenu menuReserva = new JMenu("Reservas    ");
		menuReserva.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Images/reserva.png")));
		menuReserva.setForeground(Color.WHITE);
		menuReserva.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(menuReserva);
		
//Panel RESERVA consulta en desktop
/*************************************************************************************/			
		
		
		JMenuItem menuReservaConsulta = new JMenuItem("Consulta");
		menuReservaConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReservaConsulta reserCons = new ReservaConsulta(desktopPane);
				desktopPane.add(reserCons);
				//control de posición del panel
				reserCons.setLocation(60, 60);
				reserCons.setVisible(true);
				
			}
		});
		menuReservaConsulta.setBackground(new Color(255, 255, 255));
		menuReservaConsulta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuReserva.add(menuReservaConsulta);
		
//Panel RESERVA realizar en desktop
/*************************************************************************************/				
		
		JMenuItem menuReservaRealizar = new JMenuItem("Realizar");
		menuReservaRealizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ReservaCreacion reserCrea = new ReservaCreacion();
				desktopPane.add(reserCrea);
				//control de posición del panel
				reserCrea.setLocation(80, 80);
				reserCrea.setVisible(true);
				
				
			}
		});
		menuReservaRealizar.setBackground(new Color(255, 255, 255));
		menuReservaRealizar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuReserva.add(menuReservaRealizar);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBorder(null);
		desktopPane.setBackground(new Color(255, 255, 255));
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Images/logoResized.png")));
		logo.setBounds(1072, 627, 182, 74);
		desktopPane.add(logo);
		
		//Component adapter para posicionar el logo relativo
		 addComponentListener(new ComponentAdapter() {
	            @Override
	            public void componentResized(ComponentEvent e) {
	                // Actualizar la posición del logo al cambiar el tamaño de la ventana
	                int x = desktopPane.getWidth() - logo.getWidth()- MARGIN;
	                int y = desktopPane.getHeight() - logo.getHeight()- MARGIN;
	                logo.setBounds(x, y, logo.getWidth(), logo.getHeight());
	            }
	        });
		
		
		
		
		
		
	
		
		
	
		
		
		
	}
}
