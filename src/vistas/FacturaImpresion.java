package vistas;

import java.awt.BorderLayout;
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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(48, 62, 281, 133);
		getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("Factura de la reserva");
		lblNewLabel.setBounds(48, 23, 147, 13);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.setBounds(241, 217, 85, 21);
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
        	    "___________________________________________________________________________________\n\n" +
        	    "DNI:\t\t" + datosReserva[0] + "\n" +
        	    "TIPO DE HABITACION:\t\t" + datosReserva[1] + "\n" +
        	    "FECHA DE ENTRADA:\t" + datosReserva[2] + "\n" +
        	    "FECHA DE SALIDA:\t" + datosReserva[3] + "\n" +
        	    "EXTRA:\t\t" + datosReserva[4] + "\n" +
        	    "_________________________________________\n\n" +
        	    "PRECIO TOTAL:\t\t" + precioFinal
        );
	}
}
