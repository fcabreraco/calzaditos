package formulario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Gestor.GestUsuario;
import cliente.Cliente;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

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
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

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
	private JTextField textIDCCliente;
	private JTextField textNNCliente;
	private JTextField textTellClient;
	private JTextField textDirecClient;
	private JTable TconsultaCliente;
	private JTextField textIDCliente;
	private JTextField textIDreparCliente;
	private JTextField textNNclienteRepar;
	private JTextField textCosto;
	private JTextField textIDClienteRepar;
	private JTable table;
	private JDateChooser dateFechaN;
	private JScrollPane scrollPane1 = new JScrollPane();
	private JButton btnActualizarCliente;
	private JButton btnCrearCliente;
	private JRadioButton rdbtnEstado;

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
	private void buscartodoUsuario() {
		clearTabla(this.Tconsulta);
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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 429);
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
		tabbedPane.setBounds(0, 11, 653, 389);
		contentPane.add(tabbedPane);

		panelGestionUsuarios = new JPanel();
		panelGestionUsuarios.setBackground(new Color(240, 240, 240));
		panelGestionUsuarios.setForeground(new Color(0, 0, 0));
		tabbedPane.addTab("Gestion usuario", null, panelGestionUsuarios, null);
		panelGestionUsuarios.setLayout(null);

		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(0, 0, 648, 361);
		panelGestionUsuarios.add(tabbedPane_2);

		JPanel panelConsultaUsuario = new JPanel();
		tabbedPane_2.addTab("Consultar usuario", null, panelConsultaUsuario, null);

		JLabel lblIdentificacion = new JLabel("Identificacion");

		textIdUsuario = new JTextField();
		textIdUsuario.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearTabla(Tconsulta);
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
				/*
				 * btnActualizar.setEnabled(true); btnCrearUsuario.setEnabled(false);
				 */
				// Se optiene el índice del item actualmente seleccionado en la tabla Tconsulta
				int fila_seleccionada = Tconsulta.getSelectedRow(); // Si no se ha seleccionado una fila, se retorna -1
				// Si existe una fila seleccionada en la tabla
				if (fila_seleccionada >= 0) {
					btnActualizar.setEnabled(true);
					textIDUsu.setEnabled(false);
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
					 * Recorre el ComboBoxRol comparando entre el rol del usuario seleccionado a
					 * modificar y los item's del ComboBox con el objetivo de saber cuál debemos
					 * dejar seleccionado y expresar al usuario que tal rol es el actual asignado al
					 * usurio
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
					buscartodoUsuario();
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
					DefaultTableModel modelo = (DefaultTableModel) Tconsulta.getModel();
					String idUsuario = modelo.getValueAt(fila_seleccionada, 0).toString();
					String parametros[] = { idUsuario };
					GestionUsuario.EjecutaQuery("DELETE FROM user_login WHERE id = ?;", parametros);
					buscartodoUsuario();
				} else {
					System.out.println(fila_seleccionada);
				}
			}
		});

		JButton btnBuscarTodo = new JButton("Buscar todo");
		btnBuscarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscartodoUsuario();
			}
		});

		GroupLayout gl_panelConsultaUsuario = new GroupLayout(panelConsultaUsuario);
		gl_panelConsultaUsuario.setHorizontalGroup(gl_panelConsultaUsuario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelConsultaUsuario.createSequentialGroup().addGroup(gl_panelConsultaUsuario
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelConsultaUsuario.createSequentialGroup().addContainerGap()
								.addComponent(textIdUsuario, GroupLayout.PREFERRED_SIZE, 161,
										GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnBuscarTodo)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnModificar, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelConsultaUsuario.createSequentialGroup().addGap(38).addComponent(
								lblIdentificacion, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(92, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panelConsultaUsuario.createSequentialGroup().addGap(20)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)));
		gl_panelConsultaUsuario.setVerticalGroup(gl_panelConsultaUsuario.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelConsultaUsuario.createSequentialGroup().addGap(16)
						.addComponent(lblIdentificacion).addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelConsultaUsuario.createParallelGroup(Alignment.LEADING)
								.addComponent(textIdUsuario, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelConsultaUsuario.createSequentialGroup().addGap(2)
										.addGroup(gl_panelConsultaUsuario.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnBuscar).addComponent(btnBuscarTodo)
												.addComponent(btnModificar).addComponent(btnEliminar))))
						.addPreferredGap(ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
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
				buscartodoUsuario();
			}
		});
		btnActualizar.setBounds(377, 173, 89, 23);
		panelCrearUsuario.add(btnActualizar);
		btnActualizar.setEnabled(false);
		// limpia los campos usando el metodo LimpiarCampos();
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane_2.setTitleAt(1, "Crear usuario"); // cambiamos el titulo crear usuario por actualizar
				LimpiarCampos();

			}
		});
		btnCancelar.setBounds(251, 206, 89, 23);
		panelCrearUsuario.add(btnCancelar);

		JPanel panelGestionCLiente = new JPanel();
		tabbedPane.addTab("Gestion cliente", null, panelGestionCLiente, null);
		panelGestionCLiente.setLayout(null);

		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_3.setBounds(0, 0, 648, 361);
		panelGestionCLiente.add(tabbedPane_3);

		JPanel panelConsultaCliente = new JPanel();
		tabbedPane_3.addTab("Consultar cliente", null, panelConsultaCliente, null);
		panelConsultaCliente.setLayout(null);

		// scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(10, 163, 633, 170);
		panelConsultaCliente.add(scrollPane1);

		TconsultaCliente = new JTable();
		scrollPane1.setRowHeaderView(TconsultaCliente);

		// panelConsultaUsuario.setLayout(gl_panelConsultaUsuario);
		// panelConsultaCliente.setLayout();

		// Creamos los encabezados para la tabla donde se listan los clientes
		TconsultaCliente.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "idCliente", "Cédula",
				"Nombre", "Fecha nacimiento", "Teléfono", "Dirección", "Estado" }));
		scrollPane1.setViewportView(TconsultaCliente);

		JLabel lblIdentificacion_3 = new JLabel("Identificacion");
		lblIdentificacion_3.setBounds(10, 30, 159, 14);
		panelConsultaCliente.add(lblIdentificacion_3);

		textIDCliente = new JTextField();
		textIDCliente.setBounds(225, 27, 203, 17);
		panelConsultaCliente.add(textIDCliente);
		textIDCliente.setColumns(10);

		// Buscar clientes por identificación (cédula)
		JButton btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTabla(TconsultaCliente);
				String parametros[] = { textIDCliente.getText() };
				ResultSet rs = GestionUsuario.Consultas("SELECT * FROM cliente WHERE Cedula = ?", parametros);
				ArrayList<Cliente> ALC = new ArrayList<Cliente>();
				try {
					while (rs.next()) {
						ALC.add(new Cliente(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
								rs.getString(4), rs.getString(5), rs.getString(6), Integer.parseInt(rs.getString(7))));
					}
					// Se optiene el DefaultTableModel de la tabla Tconsulta
					DefaultTableModel modelo = (DefaultTableModel) TconsultaCliente.getModel();
					int num_colums = modelo.getColumnCount();
					for (Cliente clie : ALC) {
						Object[] fila = new Object[num_colums];
						fila[0] = clie.getIdCliente();
						fila[1] = clie.getCedula();
						fila[2] = clie.getNombre();
						fila[3] = clie.getFe_naci();
						fila[4] = clie.getTelefono();
						fila[5] = clie.getDireccion();
						fila[6] = clie.getEstado();
						// Añade la fila al modelo de la tabla
						modelo.addRow(fila);
					}
				} catch (Exception ex) {
					System.out.println("Error: " + ex);
				}
			}
		});
		btnBuscarCliente.setBounds(10, 73, 89, 23);
		panelConsultaCliente.add(btnBuscarCliente);

		JButton btnBuscarTodoClie = new JButton("Buscar todo");
		btnBuscarTodoClie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearTabla(TconsultaCliente);
				String parametros[] = {};
				ResultSet rs = GestionUsuario.Consultas("SELECT * FROM cliente", parametros);
				ArrayList<Cliente> ALC = new ArrayList<Cliente>();
				try {
					while (rs.next()) {
						ALC.add(new Cliente(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
								rs.getString(4), rs.getString(5), rs.getString(6), Integer.parseInt(rs.getString(7))));
					}
					// Se optiene el DefaultTableModel de la tabla TconsultaCliente
					DefaultTableModel modelo = (DefaultTableModel) TconsultaCliente.getModel();
					int num_colums = modelo.getColumnCount();
					for (Cliente clie : ALC) {
						Object[] fila = new Object[num_colums];
						fila[0] = clie.getIdCliente();
						fila[1] = clie.getCedula();
						fila[2] = clie.getNombre();
						fila[3] = clie.getFe_naci();
						fila[4] = clie.getTelefono();
						fila[5] = clie.getDireccion();
						fila[6] = clie.getEstado();
						// Añade la fila al modelo de la tabla
						modelo.addRow(fila);
					}
				} catch (Exception ex) {
					System.out.println("Error: " + ex);
				}
			}
		});
		btnBuscarTodoClie.setBounds(121, 73, 89, 23);
		panelConsultaCliente.add(btnBuscarTodoClie);

		JButton btnModificarCliente = new JButton("Modificar");

		btnModificarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Se optiene el índice del item actualmente seleccionado en la tabla Tconsulta
				int fila_seleccionada = TconsultaCliente.getSelectedRow(); // Si no se ha seleccionado una fila, se
																			// retorna -1
				// Si existe una fila seleccionada en la tabla
				if (fila_seleccionada >= 0) {
					btnActualizarCliente.setEnabled(true);
					textIDCCliente.setEnabled(false);
					btnCrearCliente.setEnabled(false); // deshabilita el boton de crear usuario
					tabbedPane_3.setTitleAt(1, "Actualizar"); // cambiamos el titulo crear usuario por actualizar
					DefaultTableModel modelo = (DefaultTableModel) TconsultaCliente.getModel();
					// Pasamos los datos de la tabla a los campos de texto de la pestaña "Crear
					// Crear o Actualizar"
					textIDCCliente.setText(modelo.getValueAt(fila_seleccionada, 1).toString());
					textNNCliente.setText(modelo.getValueAt(fila_seleccionada, 2).toString());

					// "2018-11-22"
					String fecha = modelo.getValueAt(fila_seleccionada, 3).toString();
					int dia, mes, anio;
					anio = Integer.parseInt(fecha.substring(0, fecha.indexOf("-")));
					fecha = fecha.substring(fecha.indexOf("-") + 1, fecha.length());
					mes = Integer.parseInt(fecha.substring(0, fecha.indexOf("-")));
					fecha = fecha.substring(fecha.indexOf("-") + 1, fecha.length());
					dia = Integer.parseInt(fecha);

					System.out.println(dia);
					System.out.println(mes);
					System.out.println(anio);

					GregorianCalendar Calendario = new GregorianCalendar();
					Calendario.set(anio, (mes - 1), dia);
					dateFechaN.setDateFormatString("YYYY-MM-dd");
					dateFechaN.setCalendar(Calendario);
					// dateFechaN.setDate(FechaIngreso);
					// setText(modelo.getValueAt(fila_seleccionada, 3).toString());
					textTellClient.setText(modelo.getValueAt(fila_seleccionada, 4).toString());
					textDirecClient.setText(modelo.getValueAt(fila_seleccionada, 5).toString());
					/**
					 * Controla la representación del estado del cliente en un componente
					 * RadioButton
					 */
					if (Integer.parseInt(modelo.getValueAt(fila_seleccionada, 6).toString()) == 1) {
						rdbtnEstado.setText("Habilitado");
						rdbtnEstado.setSelected(true);
						System.out.println("Entra cuando es 1");
					} else {
						// De lo contrario es cero
						rdbtnEstado.setText("Deshabilitado");
						rdbtnEstado.setSelected(false);
						System.out.println("Entra cuando no es 1");
					}
					tabbedPane_3.setSelectedIndex(1); // Pasamos del panel actual al panel 2
					buscartodoCliente();

				} else {
					System.out.println(fila_seleccionada);
				}

			}
		});
		btnModificarCliente.setBounds(246, 73, 89, 23);
		panelConsultaCliente.add(btnModificarCliente);

		JButton btnEliminarCliente = new JButton("Eliminar");
		btnEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila_seleccionada = TconsultaCliente.getSelectedRow();
				if (fila_seleccionada >= 0) {
					btnCrearCliente.setEnabled(false); // deshabilita el boton de crear usuario
					DefaultTableModel modelo = (DefaultTableModel) TconsultaCliente.getModel();
					String idCliente = modelo.getValueAt(fila_seleccionada, 0).toString();
					String parametros[] = { idCliente };
					GestionUsuario.EjecutaQuery("DELETE FROM cliente WHERE idCliente = ?;", parametros);
					clearTabla(TconsultaCliente);
					buscartodoCliente(); // Equivalente a actualizar los registros existentes
				} else {
					System.out.println(fila_seleccionada);
				}

			}
		});
		btnEliminarCliente.setBounds(371, 73, 89, 23);
		panelConsultaCliente.add(btnEliminarCliente);

		JPanel panelCrearCliente = new JPanel();
		tabbedPane_3.addTab("Crear cliente", null, panelCrearCliente, null);
		panelCrearCliente.setLayout(null);

		JLabel lblIdentificacion_2 = new JLabel("Identificacion");
		lblIdentificacion_2.setBounds(20, 78, 103, 14);
		panelCrearCliente.add(lblIdentificacion_2);

		textIDCCliente = new JTextField();
		textIDCCliente.setBounds(162, 75, 116, 20);
		panelCrearCliente.add(textIDCCliente);
		textIDCCliente.setColumns(10);

		JLabel lblNombreCompleto = new JLabel("Nombre completo");
		lblNombreCompleto.setBounds(305, 78, 103, 14);
		panelCrearCliente.add(lblNombreCompleto);

		textNNCliente = new JTextField();
		textNNCliente.setBounds(418, 75, 199, 20);
		panelCrearCliente.add(textNNCliente);
		textNNCliente.setColumns(10);

		JLabel lblEdad_1 = new JLabel("Fecha nacimiento");
		lblEdad_1.setBounds(20, 98, 103, 25);
		panelCrearCliente.add(lblEdad_1);

		JLabel lblTelefono_1 = new JLabel("Telefono");
		lblTelefono_1.setBounds(343, 109, 46, 14);
		panelCrearCliente.add(lblTelefono_1);

		textTellClient = new JTextField();
		textTellClient.setBounds(418, 106, 199, 20);
		panelCrearCliente.add(textTellClient);
		textTellClient.setColumns(10);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setBounds(30, 134, 46, 14);
		panelCrearCliente.add(lblDireccin);

		textDirecClient = new JTextField();
		textDirecClient.setBounds(162, 131, 116, 20);
		panelCrearCliente.add(textDirecClient);
		textDirecClient.setColumns(10);
		// ClIENTE
		btnCrearCliente = new JButton("Crear cliente");
		btnCrearCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**
				 * Haciendo uso de un procedimiento almacenado, se insertan datos a la tabla
				 * cliente, validando (desde base de datos) si existen previamente la cédula de
				 * cliente
				 */
				String sql = "CALL InsertarCliente(?, ?, ?, ?, ?, ?);";
				String parametros[] = { textIDCCliente.getText(), textNNCliente.getText(),
						new SimpleDateFormat("yyyy-MM-dd").format(dateFechaN.getDate()), textTellClient.getText(),
						textDirecClient.getText(), "1" };
				GestionUsuario.EjecutaQuery(sql, parametros);
				LimpiarCamposCliente();
			}
		});
		btnCrearCliente.setBounds(81, 206, 133, 23);
		panelCrearCliente.add(btnCrearCliente);

		JButton btnCancelarCliente = new JButton("Cancelar");
		btnCancelarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LimpiarCamposCliente();
			}
		});
		btnCancelarCliente.setBounds(269, 206, 89, 23);
		panelCrearCliente.add(btnCancelarCliente);

		btnActualizarCliente = new JButton("Actualizar");
		btnActualizarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textIDCCliente.setEnabled(true);
				btnCrearCliente.setEnabled(true); // deshabilita el boton de crear usuario
				tabbedPane_3.setTitleAt(1, "crear cliente"); // cambiamos el titulo crear cliente por actualizar
				String estado = (rdbtnEstado.isSelected()) ? "1" : "0";
				String parametros[] = { textIDCCliente.getText(), textNNCliente.getText(),
						new SimpleDateFormat("yyyy-MM-dd").format(dateFechaN.getDate()), textTellClient.getText(),
						textDirecClient.getText(), estado, textIDCCliente.getText() };
				GestionUsuario.EjecutaQuery(
						"UPDATE cliente SET Cedula = ?, Nombre = ?, Fe_naci = ?, Telefono = ? ,Direccion = ?, estado = ? WHERE Cedula = ?;",
						parametros);

				// Limpiar campos después de ser actulizados
				LimpiarCamposCliente();
				buscartodoCliente();

			}
		});
		btnActualizarCliente.setEnabled(false);
		btnActualizarCliente.setBounds(433, 206, 89, 23);
		panelCrearCliente.add(btnActualizarCliente);

		dateFechaN = new JDateChooser();
		dateFechaN.setBounds(162, 106, 116, 20);
		panelCrearCliente.add(dateFechaN);

		rdbtnEstado = new JRadioButton("Estado");
		rdbtnEstado.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (ev.getStateChange() == 1) {
					// Habilitado
					rdbtnEstado.setText("Habilitado");
				} else {
					// Deshabilitado
					rdbtnEstado.setText("Deshabilitado");
				}
				// 1 Habilitado
				// 2 Deshabilitado
			}
		});

		rdbtnEstado.setBounds(418, 146, 109, 23);
		panelCrearCliente.add(rdbtnEstado);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(343, 150, 46, 14);
		panelCrearCliente.add(lblEstado);

		JPanel panelReparaciones = new JPanel();
		tabbedPane.addTab("Reparaciones", null, panelReparaciones, null);
		panelReparaciones.setLayout(null);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 648, 361);
		panelReparaciones.add(tabbedPane_1);

		JPanel panelConsultRepara = new JPanel();
		tabbedPane_1.addTab("Consultar reparacion", null, panelConsultRepara, null);
		panelConsultRepara.setLayout(null);

		JLabel label = new JLabel("Identificacion");
		label.setBounds(49, 33, 159, 14);
		panelConsultRepara.add(label);

		textIDClienteRepar = new JTextField();
		textIDClienteRepar.setColumns(10);
		textIDClienteRepar.setBounds(264, 30, 203, 17);
		panelConsultRepara.add(textIDClienteRepar);

		JButton btnBuscarRepar = new JButton("Buscar");
		btnBuscarRepar.setBounds(49, 76, 89, 23);
		panelConsultRepara.add(btnBuscarRepar);

		JButton btnBuscaTodoRepar = new JButton("Buscar todo");
		btnBuscaTodoRepar.setBounds(160, 76, 89, 23);
		panelConsultRepara.add(btnBuscaTodoRepar);

		JButton btnModificarRepara = new JButton("Modificar");
		btnModificarRepara.setBounds(285, 76, 89, 23);
		panelConsultRepara.add(btnModificarRepara);

		JButton btnEliminaRepar = new JButton("Eliminar");
		btnEliminaRepar.setBounds(410, 76, 89, 23);
		panelConsultRepara.add(btnEliminaRepar);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 128, 623, 205);
		panelConsultRepara.add(scrollPane_1);

		table = new JTable();
		scrollPane_1.setRowHeaderView(table);

		JPanel panelCrearRepara = new JPanel();
		tabbedPane_1.addTab("Crear reparacion", null, panelCrearRepara, null);
		panelCrearRepara.setLayout(null);

		JTextArea textAreaObs = new JTextArea();
		textAreaObs.setBounds(111, 163, 508, 104);
		panelCrearRepara.add(textAreaObs);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(322, 39, 130, 19);
		panelCrearRepara.add(lblCliente);

		textIDreparCliente = new JTextField();
		textIDreparCliente.setBounds(111, 38, 169, 19);
		panelCrearRepara.add(textIDreparCliente);
		textIDreparCliente.setColumns(10);

		JLabel lblIdentificacion_4 = new JLabel("Identificacion");
		lblIdentificacion_4.setBounds(23, 41, 107, 14);
		panelCrearRepara.add(lblIdentificacion_4);

		textNNclienteRepar = new JTextField();
		textNNclienteRepar.setBounds(379, 38, 254, 19);
		panelCrearRepara.add(textNNclienteRepar);
		textNNclienteRepar.setColumns(10);

		JLabel lblTipoCalzado = new JLabel("Tipo calzado");
		lblTipoCalzado.setBounds(21, 88, 97, 14);
		panelCrearRepara.add(lblTipoCalzado);

		JLabel lblTipoDeArreglo = new JLabel("Tipo arreglo");
		lblTipoDeArreglo.setBounds(294, 88, 88, 14);
		panelCrearRepara.add(lblTipoDeArreglo);

		JComboBox comboTipoCalz = new JComboBox();
		comboTipoCalz.setBounds(111, 85, 163, 20);
		panelCrearRepara.add(comboTipoCalz);

		JComboBox comboTipoArreg = new JComboBox();
		comboTipoArreg.setBounds(392, 86, 241, 19);
		panelCrearRepara.add(comboTipoArreg);

		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setBounds(33, 132, 46, 14);
		panelCrearRepara.add(lblCosto);

		textCosto = new JTextField();
		textCosto.setBounds(105, 129, 175, 17);
		panelCrearRepara.add(textCosto);
		textCosto.setColumns(10);

		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(23, 168, 130, 14);
		panelCrearRepara.add(lblObservaciones);

		lbllUserVista.setText(usu.getUsuario());

		// Se le establece ícono al usuario a través de un JLabel
		String path = "/Img/user_icon.png";
		URL url = this.getClass().getResource(path);
		ImageIcon icon = new ImageIcon(url);
		lblIconUser.setIcon(icon);
	}

	protected void LimpiarCamposCliente() {
		textIDCCliente.setText("");
		textNNCliente.setText("");
		dateFechaN.setDate(null);
		textTellClient.setText("");
		textDirecClient.setText("");
		rdbtnEstado.setSelected(false);
		rdbtnEstado.setText("Deshabilitado");
	}

	// Utiliza las funciones declaradas en la clase GestUsuario para efectuar la
	// consulta por identificación
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

	/*
	 * Limpiar y recargar los datos de la tabla user_login private void
	 * clearTablaUsuario() { DefaultTableModel modelo = (DefaultTableModel)
	 * Tconsulta.getModel(); int filas = modelo.getRowCount(); for (int i = 0; i <
	 * filas; i++) { // Rmovemos las filas de la tabla eliminando siempre la de la
	 * posición 0 modelo.removeRow(0); } }
	 */

	// Limpiar y los datos de la tabla que se pase por parámetro
	private void clearTabla(JTable tabla) {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		int filas = modelo.getRowCount();
		for (int i = 0; i < filas; i++) {
			// Rmovemos las filas de la tabla eliminando siempre la de la posición 0
			modelo.removeRow(0);
		}
	}

	private void buscartodoCliente() {
		clearTabla(this.TconsultaCliente);
		String parametros[] = {};
		/**
		 * Se hace uso de una estructa de datos dinámica de Java (ArrayList<Cliente>)
		 */
		ArrayList<Cliente> ALC = new ArrayList<Cliente>();
		ResultSet rs = GestionUsuario.Consultas("SELECT * FROM cliente;", parametros);
		try {
			while (rs.next()) {
				// Se agregan al ArrayList<Usuario> todos los registros de la tabla
				// TconsultaCliente (por fila)
				ALC.add(new Cliente(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), Integer.parseInt(rs.getString(7))));
			}
			// Se optiene el DefaultTableModel de la tabla TconsultaCliente
			DefaultTableModel modelo = (DefaultTableModel) TconsultaCliente.getModel();
			int num_colums = modelo.getColumnCount();
			for (Cliente cli : ALC) {
				Object[] fila = new Object[num_colums];
				fila[0] = cli.getIdCliente();
				fila[1] = cli.getCedula();
				fila[2] = cli.getNombre();
				fila[3] = cli.getFe_naci();
				fila[4] = cli.getTelefono();
				fila[5] = cli.getDireccion();
				fila[6] = cli.getEstado();
				// Añade la fila al modelo de la tabla
				modelo.addRow(fila);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
}