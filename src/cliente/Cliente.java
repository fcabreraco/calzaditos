package cliente;

public class Cliente {

	private int idCliente = 0;
	private String Cedula = "";
	private String Nombre = "";
	private String Fe_naci = "";
	private String Telefono = "";
	private String Direccion = "";
	private int Estado = 1;

	/**
	 * Constructor por defecto
	 */
	public Cliente() {
	}

	public Cliente(int idCliente, String cedula, String nombre, String fe_naci, String telefono, String direccion,
			int estado) {
		this.idCliente = idCliente;
		this.Cedula = cedula;
		this.Nombre = nombre;
		this.Fe_naci = fe_naci;
		this.Telefono = telefono;
		this.Direccion = direccion;
		this.Estado = estado;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getCedula() {
		return Cedula;
	}

	public void setCedula(String cedula) {
		Cedula = cedula;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getFe_naci() {
		return Fe_naci;
	}

	public void setFe_naci(String fe_naci) {
		Fe_naci = fe_naci;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public int getEstado() {
		return Estado;
	}

	public void setEstado(int estado) {
		Estado = estado;
	}

	// Muestra el valor de todos los atributos de la clase usuario
	public String MostrarCliente() {
		return "idCliente = " + this.getIdCliente() + "\n" + "Cedula = " + this.getCedula() + "\n" + "Nombre = "
				+ this.getNombre() + "\n" + "Fe_naci = " + this.getFe_naci() + "\n" + "Telefono = " + this.getTelefono()
				+ "\n" + "Direción = " + this.getDireccion() + "\n" + "Estado = " + this.getEstado();
	}
}
/*
 * private int idCliente = 0; private String Cedula = ""; private String Nombre
 * = ""; private String Fe_naci = ""; private String Telefono = ""; private
 * String Direccion = ""; private int Estado = 1;
 */
