package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import operaciones.Controlador;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.Font;

public class FacturaImpresion extends JDialog {

	private static final long serialVersionUID = 1L;
	private Controlador controlador;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			FacturaImpresion dialog = new FacturaImpresion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/

	/**
	 * Create the dialog.
	 */
	public FacturaImpresion(String datosReservaFactura) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FacturaImpresion.class.getResource("/Images/factura16px.png")));
		setTitle("Factura");
		setBounds(100, 100, 542, 387);
		getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(64, 66, 393, 219);
		textArea.setEditable(false);
		getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("Factura de la reserva");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel.setBounds(64, 25, 147, 13);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(372, 304, 85, 21);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(220, 20, 60));
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	               dispose();
	           }
        });
		
		
		mostrarPrecio(datosReservaFactura);
	}
	
	public void mostrarPrecio(String datosReservaFactura) {
		controlador = new Controlador();
		
		double precioFinal = controlador.precioFinal(datosReservaFactura);
		
		String[] datosReserva = datosReservaFactura.split(",");
		
		textArea.setText(
        	    "INFORMACIÓN DE LA RESERVA:\n" +
        	    "_________________________________________________\n\n" +
        	    "DNI:\t\t" + datosReserva[2] + "\n" +
        	    "TIPO DE HABITACION:\t" + datosReserva[3] + "\n" +
        	    "FECHA DE ENTRADA:\t" + datosReserva[0] + "\n" +
        	    "FECHA DE SALIDA:\t" + datosReserva[1] + "\n" +
        	    "EXTRA:\t\t" + datosReserva[4] + "\n" +
        	    "_________________________________________________\n\n" +
        	    "PRECIO TOTAL:\t" + precioFinal + " €"
        );
	}
}
