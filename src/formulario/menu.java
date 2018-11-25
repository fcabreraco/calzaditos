package formulario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import Gestor.GestUsuario;
import usuario.Usuario;

public class menu extends JFrame {

	public static JPanel contentPane;
	public static JTextField txtUser;
	public static JLabel lblUser;
	public static JLabel lblPassword;
	public static JLabel lblClinicaCalzaditos;
	public static JPasswordField jpassclave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu frame = new menu();
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
	public menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUser = new JTextField();
		txtUser.setToolTipText("");
		txtUser.setBounds(149, 63, 134, 23);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		JButton btnIniciar = new JButton("Iniciar sesion");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciar();
			}
		});

		btnIniciar.setBounds(215, 132, 116, 23);
		contentPane.add(btnIniciar);

		lblUser = new JLabel("User");
		lblUser.setBounds(45, 67, 76, 14);
		contentPane.add(lblUser);

		lblPassword = new JLabel("Contraseña");
		lblPassword.setBounds(45, 101, 76, 14);
		contentPane.add(lblPassword);

		lblClinicaCalzaditos = new JLabel("Clinica calzaditos");
		lblClinicaCalzaditos.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblClinicaCalzaditos.setBounds(132, 11, 167, 30);
		contentPane.add(lblClinicaCalzaditos);

		jpassclave = new JPasswordField();
		jpassclave.setBounds(149, 97, 136, 20);
		contentPane.add(jpassclave);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}

		});
		btnSalir.setBounds(116, 132, 89, 23);
		contentPane.add(btnSalir);
	}

	// Método utilizado para el Loggin
	protected void iniciar() {
		String usuario = txtUser.getText();
		String clave = String.valueOf(jpassclave.getPassword());

		// Instanciar de la clase GestUsuario
		GestUsuario gestionUsuario = new GestUsuario();

		// Instanciar la clase Usuario
		Usuario usuario2 = new Usuario();
		usuario2.setUsuario(usuario);
		usuario2.setclave(clave);

		Usuario usu = gestionUsuario.obtenerUsuario(usuario2);
		// Evaluamos si he halló alguna coincidencia en la base de datos
		if (usu != null) {
			JOptionPane.showMessageDialog(contentPane, "Bienvenido");
			this.dispose();
			Inicio inicio = new Inicio(usu);
			inicio.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(contentPane, "Datos inválidos", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
