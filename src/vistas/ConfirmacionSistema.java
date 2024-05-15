package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ConfirmacionSistema extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private MenuPrincipal menuPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			 MenuPrincipal menuPrincipal = new MenuPrincipal();
			ConfirmacionSistema dialog = new ConfirmacionSistema(menuPrincipal);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConfirmacionSistema(MenuPrincipal menuPrincipal) {
		//todo esto es para poder crerra el panel menu principal desde aqui
		super(menuPrincipal); 
		this.menuPrincipal = menuPrincipal;
		
		setTitle("Sistema");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConfirmacionSistema.class.getResource("/Images/sistema16px.png")));
		setBackground(new Color(245, 245, 245));
		setResizable(false);
		setBounds(100, 100, 450, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblestasSegurDe = new JLabel("¿Estas segur@ de que quieres salir?");
			lblestasSegurDe.setHorizontalAlignment(SwingConstants.CENTER);
			lblestasSegurDe.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			lblestasSegurDe.setBounds(0, 24, 434, 23);
			contentPanel.add(lblestasSegurDe);
		}
		{
			JButton cancelButton = new JButton("No");
			cancelButton.setForeground(new Color(255, 255, 255));
			cancelButton.setBackground(new Color(178, 34, 34));
			cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
			cancelButton.setActionCommand("Cancel");
			cancelButton.setBounds(219, 77, 105, 23);
			cancelButton.setBorderPainted(false);
			contentPanel.add(cancelButton);
			

			cancelButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Cerrar la ventana de confirmación si cancelo
	                dispose();
	            }
	        });
			
		}
		{
			JButton btnSi = new JButton("Si");
			btnSi.setForeground(new Color(255, 255, 255));
			btnSi.setFont(new Font("Segoe UI", Font.BOLD, 14));
			btnSi.setBackground(new Color(143, 188, 143));
			btnSi.setBorderPainted(false);
			btnSi.setActionCommand("OK");
			btnSi.setBounds(104, 77, 105, 23);
			contentPanel.add(btnSi);
			
			btnSi.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        // Cerrar MenuPrincipal al "Cancelar"
			        menuPrincipal.dispose();
			    }
			});
	}
	}
}
