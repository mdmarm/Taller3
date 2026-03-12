import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<MaterialBibliografico> biblioteca = new ArrayList<>();
    private static ArrayList<Prestamo> prestamosRealizados = new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static int contadorIdMaterial = 1;
    private static int contadorIdUsuario = 1;
    private static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        cargarMaterialesEjemplo();

        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción (1-6): ", 1, 6);

            switch (opcion) {
                case 1:
                    listarMateriales();
                    break;
                case 2:
                    simularPrestamo();
                    break;
                case 3:
                    simularDevolucion();
                    break;
                case 4:
                    mostrarMultasUsuarios();
                    break;
                case 5:
                    agregarMaterial();
                    break;
                case 6:
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("=== BIBLIOTECA - MENU PRINCIPAL ===");
        System.out.println("1. Listar Materiales");
        System.out.println("2. Prestar Material");
        System.out.println("3. Devolver Material");
        System.out.println("4. Mostrar Multas de Usuarios");
        System.out.println("5. Agregar Material");
        System.out.println("6. Salir");
    }

    private static int leerEntero(String mensaje, int min, int max) {
        int valor = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                scanner.nextLine();
                if (valor >= min && valor <= max) {
                    valido = true;
                } else {
                    System.out.println("Error: El número debe estar entre " + min + " y " + max);
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.nextLine();
            }
        }
        return valor;
    }

    private static void cargarMaterialesEjemplo() {
        System.out.println("=== CARGANDO MATERIALES DE EJEMPLO ===");
        
        // 10 Libros
        biblioteca.add(new Libro(contadorIdMaterial++, "El Principito", 96, "Antoine de Saint-Exupéry"));
        biblioteca.add(new Libro(contadorIdMaterial++, "Cien Años de Soledad", 417, "Gabriel García Márquez"));
        biblioteca.add(new Libro(contadorIdMaterial++, "1984", 328, "George Orwell"));
        biblioteca.add(new Libro(contadorIdMaterial++, "Don Quijote", 863, "Miguel de Cervantes"));
        biblioteca.add(new Libro(contadorIdMaterial++, "La Odisea", 541, "Homero"));
        biblioteca.add(new Libro(contadorIdMaterial++, "Moby Dick", 635, "Herman Melville"));
        biblioteca.add(new Libro(contadorIdMaterial++, "Orgullo y Prejuicio", 432, "Jane Austen"));
        biblioteca.add(new Libro(contadorIdMaterial++, "El Señor de los Anillos", 1178, "J.R.R. Tolkien"));
        biblioteca.add(new Libro(contadorIdMaterial++, "Crónica de una Muerte Anunciada", 160, "Gabriel García Márquez"));
        biblioteca.add(new Libro(contadorIdMaterial++, "Fahrenheit 451", 249, "Ray Bradbury"));

        // 10 Revistas
        biblioteca.add(new Revista(contadorIdMaterial++, "National Geographic", "Mensual", 101));
        biblioteca.add(new Revista(contadorIdMaterial++, "Time", "Semanal", 102));
        biblioteca.add(new Revista(contadorIdMaterial++, "Vogue", "Mensual", 103));
        biblioteca.add(new Revista(contadorIdMaterial++, "Forbes", "Semanal", 104));
        biblioteca.add(new Revista(contadorIdMaterial++, "National Geographic Kids", "Mensual", 105));
        biblioteca.add(new Revista(contadorIdMaterial++, "People", "Semanal", 106));
        biblioteca.add(new Revista(contadorIdMaterial++, "Rolling Stone", "Semanal", 107));
        biblioteca.add(new Revista(contadorIdMaterial++, "Scientific American", "Mensual", 108));
        biblioteca.add(new Revista(contadorIdMaterial++, "The New Yorker", "Semanal", 109));
        biblioteca.add(new Revista(contadorIdMaterial++, "Wired", "Mensual", 110));

        // 10 Películas
        biblioteca.add(new Pelicula(contadorIdMaterial++, "El Padrino", 175, "Francis Ford Coppola"));
        biblioteca.add(new Pelicula(contadorIdMaterial++, "Pulp Fiction", 154, "Quentin Tarantino"));
        biblioteca.add(new Pelicula(contadorIdMaterial++, "El Señor de los Anillos: La Comunidad del Anillo", 178, "Peter Jackson"));
        biblioteca.add(new Pelicula(contadorIdMaterial++, "El Resplandor", 146, "Stanley Kubrick"));
        biblioteca.add(new Pelicula(contadorIdMaterial++, "Matrix", 136, "Lana Wachowski, Lilly Wachowski"));
        biblioteca.add(new Pelicula(contadorIdMaterial++, "Interstellar", 169, "Christopher Nolan"));
        biblioteca.add(new Pelicula(contadorIdMaterial++, "El Laberinto del Fauno", 118, "Guillermo del Toro"));
        biblioteca.add(new Pelicula(contadorIdMaterial++, "Parásitos", 132, "Bong Joon-ho"));
        biblioteca.add(new Pelicula(contadorIdMaterial++, "Coco", 105, "Lee Unkrich, Adrian Molina"));
        biblioteca.add(new Pelicula(contadorIdMaterial++, "Toy Story", 81, "John Lasseter"));

        System.out.println("\n✓ " + biblioteca.size() + " materiales cargados exitosamente.");
        System.out.println("========================================\n");
    }

    private static void listarMateriales() {
        System.out.println("\n--- Listado de Materiales ---");
        if (biblioteca.isEmpty()) {
            System.out.println("No hay materiales en la biblioteca.");
            return;
        }
        System.out.println("Total de materiales: " + biblioteca.size());
        System.out.println("------------------------------------------------");
        for (MaterialBibliografico m : biblioteca) {
            m.mostrarInformacion();
        }
    }

    private static void simularPrestamo() {
        System.out.println("\n--- Prestar Material ---");
        if (biblioteca.isEmpty()) {
            System.out.println("No hay materiales para prestar.");
            return;
        }

            // --- Registro de Usuario obligatorio ---
        System.out.println("\n--- Datos del Usuario ---");
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();

        if (nombre.trim().isEmpty() || email.trim().isEmpty()) {
            System.out.println("Error: Los campos no pueden estar vacíos.");
            return;
        }      

        Usuario usuarioSeleccionado = new Usuario(contadorIdUsuario++, nombre, email);
        usuarios.add(usuarioSeleccionado);
        System.out.println("✓ Usuario registrado (ID: " + usuarioSeleccionado.getId() + ")");

        // Mostrar materiales disponibles
        System.out.println("\nSeleccione el ID del material a prestar:");
        for (MaterialBibliografico m : biblioteca) {
            System.out.println("- ID: " + m.getId() + " | " + m.getTitulo() + " | " + (m.isDisponible() ? "Disponible" : "Prestado"));
        }

        int idMaterial = leerEntero("Ingrese ID del Material: ", 1, 99999);
        MaterialBibliografico materialSeleccionado = null;
        for (MaterialBibliografico m : biblioteca) {
            if (m.getId() == idMaterial) {
                materialSeleccionado = m;
                break;
            }
        }

        if (materialSeleccionado != null) {
            if (!materialSeleccionado.isDisponible()) {
                System.out.println("Error: Este material ya está prestado.");
                return;
            }

            System.out.print("Ingrese fecha de préstamo (dd/MM/yyyy): ");
            String fechaPrestamoStr = scanner.nextLine();
            LocalDate fechaPrestamo = LocalDate.parse(fechaPrestamoStr, formatoFecha);

            // Crear el préstamo
            Prestamo prestamo = new Prestamo(usuarioSeleccionado, materialSeleccionado, fechaPrestamo);
            prestamosRealizados.add(prestamo);

            // Marcar material como no disponible
            materialSeleccionado.setDisponible(false);
            materialSeleccionado.incrementarContadorPrestamos();

            System.out.println("------------------------------------------------");
            System.out.println("✓ Préstamo registrado exitosamente");
            System.out.println("Usuario: " + usuarioSeleccionado.getNombre());
            System.out.println("Material: " + materialSeleccionado.getTitulo());
            System.out.println("Fecha de préstamo: " + fechaPrestamo.format(formatoFecha));
            System.out.println("------------------------------------------------");
        } else {
            System.out.println("Material no encontrado.");
        }
    }

    private static void simularDevolucion() {
        System.out.println("\n--- Devolver Material ---");
        if (prestamosRealizados.isEmpty()) {
            System.out.println("No hay préstamos pendientes para devolver.");
            return;
        }

        // Mostrar préstamos activos
        System.out.println("Seleccione el ID del préstamo a devolver:");
        for (int i = 0; i < prestamosRealizados.size(); i++) {
            Prestamo p = prestamosRealizados.get(i);
            if (!p.isDevuelto()) {
                System.out.println("- ID Préstamo: " + (i + 1) + " | Material: " + p.getMaterial().getTitulo() + " | Usuario: " + p.getUsuario().getNombre());
            }
        }

        int idPrestamo = leerEntero("Ingrese ID del Préstamo (1-" + prestamosRealizados.size() + "): ", 1, prestamosRealizados.size());
        Prestamo prestamoSeleccionado = prestamosRealizados.get(idPrestamo - 1);

        if (prestamoSeleccionado.isDevuelto()) {
            System.out.println("Error: Este material ya ha sido devuelto.");
            return;
        }

        System.out.print("Ingrese fecha de devolución (dd/MM/yyyy): ");
        String fechaDevolucionStr = scanner.nextLine();
        LocalDate fechaDevolucion = LocalDate.parse(fechaDevolucionStr, formatoFecha);

        // Devolver el material
        prestamoSeleccionado.devolver(fechaDevolucion);

        System.out.println("------------------------------------------------");
        System.out.println("✓ Devolución registrada exitosamente");
        System.out.println("Material: " + prestamoSeleccionado.getMaterial().getTitulo());
        System.out.println("Usuario: " + prestamoSeleccionado.getUsuario().getNombre());
        System.out.println("Fecha de devolución: " + fechaDevolucion.format(formatoFecha));
        System.out.printf("Multa Generada: $%.2f pesos%n", prestamoSeleccionado.getMultaCalculada());
        System.out.println("------------------------------------------------");
    }

    private static void mostrarMultasUsuarios() {
        System.out.println("\n=== MULTAS DE USUARIOS ===");
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        System.out.println("------------------------------------------------");
        System.out.printf("%-5s %-20s %-15s %-10s%n", "ID", "Usuario", "Multa Total", "Estado");
        System.out.println("------------------------------------------------");

        for (Usuario u : usuarios) {
            String estado = u.getMultaAcumulada() > 0 ? "Deudor" : "Al día";
            System.out.printf("%-5d %-20s $%-14.2f %-10s%n", 
                u.getId(), 
                u.getNombre(), 
                u.getMultaAcumulada(), 
                estado);
        }

        System.out.println("------------------------------------------------");
        System.out.println("Total de usuarios registrados: " + usuarios.size());
    }

    private static void agregarMaterial() {
        System.out.println("\n--- Agregar Material ---");
        System.out.println("Seleccione el tipo de material:");
        System.out.println("1. Libro");
        System.out.println("2. Revista");
        System.out.println("3. Película");
        int tipo = leerEntero("Tipo (1-3): ", 1, 3);

        System.out.print("Ingrese título: ");
        String titulo = scanner.nextLine();

        switch (tipo) {
            case 1:
                System.out.print("Ingrese número de páginas: ");
                int paginas = leerEntero("", 1, 10000);
                System.out.print("Ingrese autor: ");
                String autor = scanner.nextLine();
                biblioteca.add(new Libro(contadorIdMaterial++, titulo, paginas, autor));
                System.out.println("✓ Libro agregado exitosamente.");
                break;
            case 2:
                System.out.print("Ingrese periodicidad: ");
                String periodicidad = scanner.nextLine();
                System.out.print("Ingrese número de edición: ");
                int numeroEdicion = leerEntero("", 1, 100000);
                biblioteca.add(new Revista(contadorIdMaterial++, titulo, periodicidad, numeroEdicion));
                System.out.println("✓ Revista agregada exitosamente.");
                break;
            case 3:
                System.out.print("Ingrese duración en minutos: ");
                int duracion = leerEntero("", 1, 10000);
                System.out.print("Ingrese director: ");
                String director = scanner.nextLine();
                biblioteca.add(new Pelicula(contadorIdMaterial++, titulo, duracion, director));
                System.out.println("✓ Película agregada exitosamente.");
                break;
            default:
                System.out.println("Tipo no válido.");
        }
    }
}