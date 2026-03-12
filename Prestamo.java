import java.time.LocalDate;

public class Prestamo {
    private Usuario usuario;
    private MaterialBibliografico material;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private double multaCalculada;
    private boolean devuelto;

    public Prestamo(Usuario usuario, MaterialBibliografico material, LocalDate fechaPrestamo) {
        this.usuario = usuario;
        this.material = material;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = null;
        this.multaCalculada = 0;
        this.devuelto = false;
    }

    public void devolver(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = true;
        this.material.setDisponible(true); // El material vuelve a estar disponible
        
        // Calcular días de préstamo
        long diasPrestado = java.time.temporal.ChronoUnit.DAYS.between(fechaPrestamo, fechaDevolucion);
        int diasMaximos = material.getDiasMaximosPrestamo();
        
        if (diasPrestado > diasMaximos) {
            int diasRetraso = (int) (diasPrestado - diasMaximos);
            this.multaCalculada = material.calcularMulta(diasRetraso);
            
            // Actualizar usuario con la multa
            usuario.agregarMulta(this.multaCalculada);
        }
    }

    public Usuario getUsuario() { return usuario; }
    public MaterialBibliografico getMaterial() { return material; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public double getMultaCalculada() { return multaCalculada; }
    public boolean isDevuelto() { return devuelto; }
}


