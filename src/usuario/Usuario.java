package usuario;

public class Usuario {

	private long id = 0;
	private String nombre = "";
	private int edad = 0;
	private long telefono = 0;
	private String direccion = "";
	private String rol = "";
	private String usuario = "";
	private String clave = "";

	// constructor
	public Usuario(long id, String nombre, int edad, long telefono, String direccion, String rol, String usuario,
			String clave) {
		// super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.telefono = telefono;
		this.direccion = direccion;
		this.rol = rol;
		this.usuario = usuario;
		this.clave = clave;
	}

	public Usuario() {
	}

	// get and set
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getclave() {
		return clave;
	}

	public void setclave(String clave) {
		this.clave = clave;
	}

	// Muestra el valor de todos los atributos de la clase usuario
	public String MostrarUsuario() {
		return "id = " + this.id + "\n" + "nombre = " + this.nombre + "\n" + "edad = " + this.edad + "\n"
				+ "telefono = " + this.telefono + "\n" + "direccion = " + this.direccion + "\n" + "rol = " + this.rol
				+ "\n" + "usuario = " + this.usuario + "\n" + "clave = " + this.clave;
	}
}