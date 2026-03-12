public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private double multaAcumulada;

    public Usuario(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.multaAcumulada = 0;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public double getMultaAcumulada() { return multaAcumulada; }

    public void agregarMulta(double monto) {
        this.multaAcumulada += monto;
    }

    public void mostrarDatos() {
        System.out.println("------------------------------------------------");
        System.out.println("Usuario ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
        System.out.println("Multa Total: $" + String.format("%.2f", multaAcumulada));
        System.out.println("------------------------------------------------");
    }
}
