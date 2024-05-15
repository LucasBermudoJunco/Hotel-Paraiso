package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalityType;
import java.awt.Window.Type;
import java.awt.Color;

public class ConfirmacionCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	//para poder abrir con el actionlistener en el escritorio de principal
	private final JDesktopPane desktopPane;

	
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ConfirmacionCliente dialog = new ConfirmacionCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ConfirmacionCliente(JDesktopPane desktopPane) {
		setType(Type.POPUP);
		setResizable(false);
		
		this.desktopPane = desktopPane;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConfirmacionCliente.class.getResource("/Images/cliente16px.png")));
		setBounds(100, 100, 450, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("El cliente no está registrado");
			lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(0, 24, 434, 23);
			contentPanel.add(lblNewLabel);
		}
		
		JLabel lblNewLabel_1 = new JLabel("¿Quieres darlo de alta?");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 46, 434, 25);
		contentPanel.add(lblNewLabel_1);
		{
			JButton okButton = new JButton("Dar de Alta");
			okButton.setForeground(new Color(255, 255, 255));
			okButton.setBackground(new Color(143, 188, 143));
			okButton.setBorderPainted(false);
			okButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					ClienteAlta cliAlt = new ClienteAlta();
					//para que se vea en el escritorio
					desktopPane.add(cliAlt);
					cliAlt.setVisible(true);
					//cerrar el pulsar
					dispose();
					
				}
			});
			okButton.setBounds(98, 104, 120, 35);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancelar");
			cancelButton.setForeground(new Color(255, 255, 255));
			cancelButton.setBackground(new Color(165, 42, 42));
			cancelButton.setBorderPainted(false);
			cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
			cancelButton.setBounds(228, 104, 120, 35);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
			
			cancelButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Cerrar la ventana de confirmación si cancelo
	                dispose();
	            }
	        });
			
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
}
