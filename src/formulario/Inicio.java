package formulario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Gestor.GestUsuario;
import usuario.Usuario;

import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JToolBar;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.JTree;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Button;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Inicio extends JFrame {

	// Se definen componentes y variables globales
	private JPanel contentPane;
	private JTextField textIdUsuario;
	private GestUsuario GestionUsuario = new GestUsuario();
	private JTable Tconsulta;
	private ArrayList<Usuario> ArrayListUser = new ArrayList<Usuario>();
	private JTextField textIDUsu;
	private JTextField textNNUsu;
	private JTextField textEdadUsu;
	private JTextField textTelefUsu;
	private JTextField textDirecionUsu;
	private JTextField textNomUsu;
	private JTextField textClaveUsu;
	private Usuario UsurioConsultado = null;
	private JPanel panelCrearUsuario;
	private JButton btnCrearUsuario;
	private JPanel panelGestionUsuarios;
	private JComboBox comboBoxRol;
	private JButton btnActualizar;

	private void LimpiarCampos() {
		// Limpiar campos después de ser actulizados
		textIDUsu.setText("");
		textNNUsu.setText("");
		textEdadUsu.setText("");
		textTelefUsu.setText("");
		textDirecionUsu.setText("");
		comboBoxRol.setSelectedIndex(0);
		textNomUsu.setText("");
		textClaveUsu.setText("");
		textIDUsu.setText("");

		btnActualizar.setEnabled(false);
		btnCrearUsuario.setEnabled(true);
	}

//metodo para traer todo lo que hay en la base de datos 
	private void buscartodo() {
		clearTabla();
		String parametros[] = {};
		/**
		 * Se hace uso de una estructa de datos dinámica de Java (ArrayList<Usuario>)
		 */
		ArrayList<Usuario> ALU = new ArrayList<Usuario>();
		ResultSet rs = GestionUsuario.Consultas("SELECT * FROM user_login;", parametros);
		try {
			while (rs.next()) {
				// Se agregan al ArrayList<Usuario> todos los registros de la tabla user_login
				// (por fila)
				ALU.add(new Usuario(Long.parseLong(rs.getString(1)), rs.getString(2), Integer.parseInt(rs.getString(3)),
						Long.parseLong(rs.getString(4)), rs.getString(5), rs.getString(6), rs.getString(7),
						rs.getString(8)));
			}
			// Se optiene el DefaultTableModel de la tabla Tconsulta
			DefaultTableModel modelo = (DefaultTableModel) Tconsulta.getModel();
			int num_colums = modelo.getColumnCount();
			for (Usuario usu : ALU) {
				Object[] fila = new Object[num_colums];
				fila[0] = usu.getId();
				fila[1] = usu.getNombre();
				fila[2] = usu.getEdad();
				fila[3] = usu.getTelefono();
				fila[4] = usu.getDireccion();
				fila[5] = usu.getRol();
				fila[6] = usu.getUsuario();
				fila[7] = usu.getclave();
				// Añade la fila al modelo de la tabla
				modelo.addRow(fila);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

//inicio del constructor
	public Inicio(Usuario usu) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIconUser = new JLabel("");
		lblIconUser.setBounds(442, 5, 24, 24);
		contentPane.add(lblIconUser);

		JLabel lbllUserVista = new JLabel("");
		lbllUserVista.setBounds(476, 15, 138, 14);
		contentPane.add(lbllUserVista);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 11, 616, 327);
		contentPane.add(tabbedPane);

		panelGestionUsuarios = new JPanel();
		panelGestionUsuarios.setBackground(new Color(240, 240, 240));
		panelGestionUsuarios.setForeground(new Color(0, 0, 0));
		tabbedPane.addTab("Gestion usuario", null, panelGestionUsuarios, null);
		panelGestionUsuarios.setLayout(null);

		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(0, 0, 596, 299);
		panelGestionUsuarios.add(tabbedPane_2);
		// contentPane.setLayout(gl_panelConsultaUsuario);
		// Tconsulta.removeColumn(Tconsulta.getColumn("Objecte"));

		panelCrearUsuario = new JPanel();
		tabbedPane_2.addTab("Crear usuario", null, panelCrearUsuario, null);
		panelCrearUsuario.setLayout(null);

		comboBoxRol = new JComboBox();
		comboBoxRol.setModel(new DefaultComboBoxModel(new String[] { "", "Administrador", "Cajero" }));
		comboBoxRol.setBounds(368, 84, 213, 20);
		panelCrearUsuario.add(comboBoxRol);

		JLabel lblTipoDeUsuario_1 = new JLabel("Tipo de usuario");
		lblTipoDeUsuario_1.setBounds(244, 87, 114, 14);
		panelCrearUsuario.add(lblTipoDeUsuario_1);

		JLabel lblIdentificacion_1 = new JLabel("Identificacion ");
		lblIdentificacion_1.setBounds(26, 19, 111, 14);
		panelCrearUsuario.add(lblIdentificacion_1);

		textIDUsu = new JTextField();
		textIDUsu.setBounds(110, 19, 122, 20);
		panelCrearUsuario.add(textIDUsu);
		textIDUsu.setColumns(10);

		JLabel lblNombre = new JLabel(" Nombre completo");
		lblNombre.setBounds(244, 22, 114, 14);
		panelCrearUsuario.add(lblNombre);

		textNNUsu = new JTextField();
		textNNUsu.setBounds(365, 19, 216, 20);
		panelCrearUsuario.add(textNNUsu);
		textNNUsu.setColumns(10);

		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(23, 48, 46, 14);
		panelCrearUsuario.add(lblEdad);

		textEdadUsu = new JTextField();
		textEdadUsu.setBounds(110, 50, 122, 20);
		panelCrearUsuario.add(textEdadUsu);
		textEdadUsu.setColumns(10);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(244, 51, 46, 14);
		panelCrearUsuario.add(lblTelefono);

		JLabel lblDirecin = new JLabel("Direci\u00F3n");
		lblDirecin.setBounds(23, 84, 46, 14);
		panelCrearUsuario.add(lblDirecin);

		textTelefUsu = new JTextField();
		textTelefUsu.setBounds(365, 50, 216, 20);
		panelCrearUsuario.add(textTelefUsu);
		textTelefUsu.setColumns(10);

		textDirecionUsu = new JTextField();
		textDirecionUsu.setBounds(110, 81, 122, 20);
		panelCrearUsuario.add(textDirecionUsu);
		textDirecionUsu.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(23, 123, 46, 14);
		panelCrearUsuario.add(lblUsuario);

		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(244, 126, 46, 14);
		panelCrearUsuario.add(lblClave);

		textNomUsu = new JTextField();
		textNomUsu.setBounds(106, 120, 122, 20);
		panelCrearUsuario.add(textNomUsu);
		textNomUsu.setColumns(10);

		textClaveUsu = new JTextField();
		textClaveUsu.addKeyListener(new KeyAdapter() {
			// evento
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if ((int) evt.getKeyChar() > 32 && (int) evt.getKeyChar() <= 47
						|| (int) evt.getKeyChar() >= 58 && (int) evt.getKeyChar() <= 64
						|| (int) evt.getKeyChar() >= 91 && (int) evt.getKeyChar() <= 96
						|| (int) evt.getKeyChar() >= 123 && (int) evt.getKeyChar() <= 255) {
					getToolkit().beep();
					evt.consume();
					JOptionPane.showMessageDialog(contentPane, "Los caracteres #,!,¡,?,^,¿,|,° no están permitidos");
					// JOptionPane.showInputDialog("Ingrese solo letras");
					textClaveUsu.setCursor(null);
				}

			}
		});
		textClaveUsu.setText("");
		textClaveUsu.setBounds(368, 123, 213, 20);
		panelCrearUsuario.add(textClaveUsu);
		textClaveUsu.setColumns(10);

		// Insertar usuario
		btnCrearUsuario = new JButton("Crear Usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**
				 * Haciendo uso de un procedimiento almacenado, se insertan datos a la tabla
				 * user_login, validando (desde base de datos) si existen previamente el nombre
				 * de usuario o identificador del mismo. Si no existen, se crea el nuevo
				 * registro
				 */
				String sql = "CALL InsertarUsuario(?, ?, ?, ?, ?, ?, ?, ?);";
				// Se organizan los parámetros de la función EjecutaQuery como se requiere en la
				// misma
				String parametros[] = { textIDUsu.getText(), textNNUsu.getText(), textEdadUsu.getText(),
						textTelefUsu.getText(), textDirecionUsu.getText(),
						comboBoxRol.getItemAt(comboBoxRol.getSelectedIndex()).toString(), textNomUsu.getText(),
						textClaveUsu.getText() };
				GestionUsuario.EjecutaQuery(sql, parametros);
				LimpiarCampos();
			}

		});
		btnCrearUsuario.setBounds(59, 173, 161, 23);
		panelCrearUsuario.add(btnCrearUsuario);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textIDUsu.setEnabled(true);
				btnCrearUsuario.setEnabled(false); // deshabilita el boton de crear usuario
				tabbedPane_2.setTitleAt(1, "crear usuario"); // cambiamos el titulo crear usuario por actualizar
				String parametros[] = { textIDUsu.getText(), textNNUsu.getText(), textEdadUsu.getText(),
						textTelefUsu.getText(), textDirecionUsu.getText(),
						comboBoxRol.getItemAt(comboBoxRol.getSelectedIndex()).toString(), textNomUsu.getText(),
						textClaveUsu.getText(), textIDUsu.getText() };
				GestionUsuario.EjecutaQuery(
						"UPDATE user_login SET id=?, nombre=?,edad=?,telefono=?,direccion=?,rol=?,usuario=?,clave=? WHERE id = ?;",
						parametros);

				// Limpiar campos después de ser actulizados
				LimpiarCampos();

			}
		});
		btnActualizar.setBounds(377, 173, 89, 23);
		panelCrearUsuario.add(btnActualizar);
		// limpia los campos usando el metodo LimpiarCampos();
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LimpiarCampos();

			}
		});
		btnCancelar.setBounds(251, 206, 89, 23);
		panelCrearUsuario.add(btnCancelar);

		JPanel panelConsultaUsuario = new JPanel();
		tabbedPane_2.addTab("Consultar usuario", null, panelConsultaUsuario, null);

		JLabel lblIdentificacion = new JLabel("Identificacion");

		textIdUsuario = new JTextField();
		textIdUsuario.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearTabla();
				try {
					MostrarConsulta();
				} catch (Exception e) {
					System.out.println("Error: " + e);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textIDUsu.setEnabled(false);
				btnActualizar.setEnabled(true);
				// Se optiene el índice del item actualmente seleccionado en la tabla Tconsulta
				int fila_seleccionada = Tconsulta.getSelectedRow(); // Si no se ha seleccionado una fila, se retorna -1
				// Si existe una fila seleccionada en la tabla
				if (fila_seleccionada >= 0) {
					btnCrearUsuario.setEnabled(false); // deshabilita el boton de crear usuario
					tabbedPane_2.setTitleAt(1, "Actualizar"); // cambiamos el titulo crear usuario por actualizar
					DefaultTableModel modelo = (DefaultTableModel) Tconsulta.getModel();
					// Pasamos los datos de la tabla a los campos de texto de la pestaña "Crear
					// Usuario o Actualizar"
					textIDUsu.setText(modelo.getValueAt(fila_seleccionada, 0).toString());
					textNNUsu.setText(modelo.getValueAt(fila_seleccionada, 1).toString());
					textEdadUsu.setText(modelo.getValueAt(fila_seleccionada, 2).toString());
					textTelefUsu.setText(modelo.getValueAt(fila_seleccionada, 3).toString());
					textDirecionUsu.setText(modelo.getValueAt(fila_seleccionada, 4).toString());
					textNomUsu.setText(modelo.getValueAt(fila_seleccionada, 6).toString());
					textClaveUsu.setText(modelo.getValueAt(fila_seleccionada, 7).toString());
					/**
					 * Recorre el ComboBoxRol comparando entre el rol del usuario seleccionado a modificar
					 * y los item's del ComboBox con el objetivo de saber cuál debemos dejar seleccionado y
					 * expresar al usuario que tal rol es el actual asignado al usurio
					 */
					for (int i = 0; i < comboBoxRol.getItemCount(); i++) {
						if (comboBoxRol.getItemAt(i).toString()
								.equals(modelo.getValueAt(fila_seleccionada, 5).toString())) {
							comboBoxRol.setSelectedItem(comboBoxRol.getItemAt(i));
							break;
						} else {
							comboBoxRol.setSelectedItem(comboBoxRol.getItemAt(0));
						}
					}
					tabbedPane_2.setSelectedIndex(1); // Pasamos del panel actual al panel 2
					buscartodo();
				} else {
					System.out.println(fila_seleccionada);
				}
			}
		});

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int fila_seleccionada = Tconsulta.getSelectedRow();
				if (fila_seleccionada >= 0) {
					btnCrearUsuario.setEnabled(false); // deshabilita el boton de crear usuario
					tabbedPane_2.setTitleAt(1, "Actualizar"); // cambiamos el titulo crear usuario por actualizar
					DefaultTableModel modelo = (DefaultTableModel) Tconsulta.getModel();
					int fila = Tconsulta.getSelectedRow();
					String idUsuario = modelo.getValueAt(fila, 0).toString();
					String parametros[] = { idUsuario };
					GestionUsuario.EjecutaQuery("DELETE FROM user_login WHERE id = ?;", parametros);
					buscartodo();
				} else {
					System.out.println(fila_seleccionada);
				}

			}
		});

		JButton btnBuscarTodo = new JButton("Buscar todo");
		btnBuscarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscartodo();
			}
		});

		GroupLayout gl_panelConsultaUsuario = new GroupLayout(panelConsultaUsuario);
		gl_panelConsultaUsuario.setHorizontalGroup(gl_panelConsultaUsuario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelConsultaUsuario.createSequentialGroup().addGroup(gl_panelConsultaUsuario
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelConsultaUsuario.createSequentialGroup().addContainerGap()
								.addGroup(gl_panelConsultaUsuario.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
										.addGroup(gl_panelConsultaUsuario.createSequentialGroup()
												.addComponent(textIdUsuario, GroupLayout.PREFERRED_SIZE, 161,
														GroupLayout.PREFERRED_SIZE)
												.addGap(10)
												.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 89,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnBuscarTodo)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnModificar, GroupLayout.PREFERRED_SIZE, 88,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnEliminar,
														GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panelConsultaUsuario.createSequentialGroup().addGap(38).addComponent(
								lblIdentificacion, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		gl_panelConsultaUsuario.setVerticalGroup(gl_panelConsultaUsuario.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelConsultaUsuario.createSequentialGroup().addGap(16).addComponent(lblIdentificacion)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelConsultaUsuario.createParallelGroup(Alignment.LEADING)
								.addComponent(textIdUsuario, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelConsultaUsuario.createSequentialGroup().addGap(2)
										.addGroup(gl_panelConsultaUsuario.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnBuscar).addComponent(btnBuscarTodo)
												.addComponent(btnModificar).addComponent(btnEliminar))))
						.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		// Se instancia la tabla definida al inicio de la clase (global)
		Tconsulta = new JTable();
		scrollPane.setRowHeaderView(Tconsulta);
		panelConsultaUsuario.setLayout(gl_panelConsultaUsuario);
		// Se añaden los encabezados de las columnas
		Tconsulta.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nom. Persona.", "Edad",
				"Teléfono", "Dirección", "Rol", "Nom. Usuario", "Cláve" }));
		scrollPane.setViewportView(Tconsulta);

		JPanel panelGestionCLiente = new JPanel();
		tabbedPane.addTab("Gestion cliente", null, panelGestionCLiente, null);

		JPanel panelReparaciones = new JPanel();
		tabbedPane.addTab("Reparaciones", null, panelReparaciones, null);
		panelReparaciones.setLayout(null);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 596, 299);
		panelReparaciones.add(tabbedPane_1);

		JPanel panel = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel, null);

		JPanel panel_1 = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel_1, null);

		lbllUserVista.setText(usu.getUsuario());

		// Se le establece ícono al usuario a través de un JLabel
		String path = "/Img/user_icon.png";
		URL url = this.getClass().getResource(path);
		ImageIcon icon = new ImageIcon(url);
		lblIconUser.setIcon(icon);
	}

	// Utiliza las funciones declaradas en la clase GestUsuario para efectuar la consulta por identificación
	private void MostrarConsulta() {
		ArrayListUser = GestionUsuario.getUsuarioPorIdentificacion(this.textIdUsuario.getText());
		DefaultTableModel modelo = (DefaultTableModel) Tconsulta.getModel();
		int num_colums = modelo.getColumnCount();
		for (Usuario usu : ArrayListUser) {
			Object[] fila = new Object[num_colums];
			fila[0] = usu.getId();
			fila[1] = usu.getNombre();
			fila[2] = usu.getEdad();
			fila[3] = usu.getTelefono();
			fila[4] = usu.getDireccion();
			fila[5] = usu.getRol();
			fila[6] = usu.getUsuario();
			fila[7] = usu.getclave();
			// Agregar fila a la tabla Tconsulta
			modelo.addRow(fila);
			UsurioConsultado = usu;
		}
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
	}

	// Limpiar y recargar los datos de la tabla user_login
	private void clearTabla() {
		DefaultTableModel modelo = (DefaultTableModel) Tconsulta.getModel();
		int filas = modelo.getRowCount();
		for (int i = 0; i < filas; i++) {
			// Rmovemos las filas de la tabla eliminando siempre la de la posición 0 
			modelo.removeRow(0);
		}
	}
}