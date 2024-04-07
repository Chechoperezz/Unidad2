import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Clientes {

    private static final double IVA_VALUE = 0.16;

    private int cedula;
    private String nombre;
    private String apellido;
    private String genero;
    private Date nacimiento;
    private long telefono;
    private String email;
    private String direccion;
    private int cantidadCompras = 0;
    private Modalidad modalidad;
    private EstadoCliente estado = EstadoCliente.REFERIDO;

    private static List<Clientes> listaClientes = new ArrayList<>();


    public void getClientes(){
        for (Clientes clientes : listaClientes){
            System.out.println(clientes);
        }
    }
    public void registrarCliente() {
        Scanner scn = new Scanner(System.in);

        System.out.println("Ingrese la cédula del cliente:");
        int cedula = scn.nextInt();
        scn.nextLine();

        for (Clientes clienteExistente : listaClientes) {
            if (clienteExistente.getCedula() == cedula) {
                System.out.println("El cliente con cédula " + cedula + " ya está registrado.");
                return;
            }
        }

        System.out.println("Ingrese el nombre del cliente:");
        String nombre = scn.nextLine();

        System.out.println("Ingrese el apellido del cliente:");
        String apellido = scn.nextLine();

        System.out.println("Ingrese el género del cliente:");
        String genero = scn.nextLine();

        System.out.println("Ingrese la fecha de nacimiento del cliente (yyyy/MM/dd):");
        String fechaNacimientoStr = scn.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date fechaNacimiento;
        try {
            fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
        } catch (ParseException e) {
            System.out.println("Formato de fecha incorrecto. Use yyyy/MM/dd.");
            return;
        }

        System.out.println("Ingrese el número telefónico del cliente:");
        long telefono = scn.nextLong();
        scn.nextLine();

        System.out.println("Ingrese el email del cliente:");
        String email = scn.nextLine();

        System.out.println("Ingrese la dirección del cliente:");
        String direccion = scn.nextLine();

        Clientes clienteNuevo = new Clientes();
        clienteNuevo.setCedula(cedula);
        clienteNuevo.setNombre(nombre);
        clienteNuevo.setApellido(apellido);
        clienteNuevo.setGenero(genero);
        clienteNuevo.setNacimiento(fechaNacimiento);
        clienteNuevo.setTelefono(telefono);
        clienteNuevo.setEmail(email);
        clienteNuevo.setDireccion(direccion);

        listaClientes.add(clienteNuevo);
        System.out.println("Cliente registrado exitosamente.");
    }

    public void actualizarCliente() {
        Scanner scn = new Scanner(System.in);

        System.out.println("INGRESE NUMERO DE CEDULA: ");
        int cedula = scn.nextInt();

        for (Clientes cliente : listaClientes) {
            if (cliente.getCedula() == cedula) {
                System.out.println("NOMBRE");
                String nombre = scn.nextLine();
                if (!nombre.isEmpty()) {
                    cliente.setNombre(nombre);
                }

                System.out.println("APELLIDO");
                String apellido = scn.nextLine();
                if (!apellido.isEmpty()) {
                    cliente.setApellido(apellido);
                }

                System.out.println("GENERO");
                String genero = scn.nextLine();
                if (!genero.isEmpty()) {
                    cliente.setGenero(genero);
                }

                System.out.println("EMAIL");
                String email = scn.nextLine();
                if (!email.isEmpty()) {
                    cliente.setEmail(email);
                }

                System.out.println("DIRECCION");
                String direccion = scn.nextLine();
                if (!direccion.isEmpty()) {
                    cliente.setDireccion(direccion);
                }

                System.out.println("TELEFONO");
                String telefonoStr = scn.nextLine();
                if (!telefonoStr.isEmpty()) {
                    long telefono = Long.parseLong(telefonoStr);
                    cliente.setTelefono(telefono);
                }

                System.out.println("Cliente actualizado exitosamente.");
                return;
            }
        }
        System.out.println("CÉDULA NO REGISTRADA");
    }

    public void realizarCompra() {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Scanner scn = new Scanner(System.in);
        System.out.println("INGRESE SU NUMERO DE CÉDULA: ");
        int cedula = scn.nextInt();
        scn.nextLine();

        Clientes clienteEncontrado = null;
        for (Clientes cliente : listaClientes) {
            if (cliente.getCedula() == cedula) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado == null) {
            System.out.println("CÉDULA NO REGISTRADA");
            return;
        }

        System.out.println("INGRESE CÓDIGO DE PRODUCTO");
        int codigoProducto = scn.nextInt();
        scn.nextLine();

        Producto producto = new Producto();
        Producto productoEncontrado = producto.buscarProducto(codigoProducto);

        if (productoEncontrado == null) {
            System.out.println("El producto con código " + codigoProducto + " no existe.");
            return;
        }
        System.out.println(productoEncontrado.toString());

        Venta venta = new Venta();
        venta.setEstadoVenta(EstadoVenta.ESPERA);

        System.out.println("INGRESE MEDIO DE PAGO: ");
        String medioDePago = scn.nextLine();

        System.out.println("INGRESE MODALIDAD");
        String modalidad = scn.nextLine();
        Modalidad modalidad1 = Modalidad.DIRECTA;
        if (modalidad.equalsIgnoreCase("Domicilio")) {
            modalidad1 = Modalidad.DOMICILIO;
        }

        System.out.println("INGRESE DIRECCION");
        String direccion = scn.nextLine();

        System.out.println("CANTIDAD DESEADA: ");
        int cantidad = scn.nextInt();
        scn.nextLine();

        if (cantidad > productoEncontrado.getUnidades()) {
            System.out.println("La cantidad deseada es mayor a la disponible. Cantidad disponible: " + productoEncontrado.getUnidades());
            return;
        }

        double total = productoEncontrado.getPrecioDeVenta() * cantidad;
        double porcentajeDescuento = productoEncontrado.getPercentage() / 100.0;

        double descuento = total * porcentajeDescuento;
        double totalConDescuento = total - descuento;

        double totalIVA = totalConDescuento * (1 + IVA_VALUE);
        System.out.println("TOTAL A PAGAR: " + totalConDescuento);

        System.out.println("PRESIONE : 1[CONFIRMAR PAGO] | 2 [CANCELAR]");
        int confirmation = scn.nextInt();
        scn.nextLine();

        if (confirmation == 1) {
            venta.setFecha(date);
            venta.setCantidad(cantidad);
            venta.setCodigoProducto(codigoProducto);
            venta.setMedioDePago(medioDePago);
            venta.setCedulaCliente(clienteEncontrado.getCedula());
            venta.setDireccion(direccion);
            venta.setModalidad(modalidad1);
            venta.setTotal(total);

            if (productoEncontrado.registrarVenta(venta)) {
                System.out.println("TRANSACCIÓN EXITOSA");
                clienteEncontrado.setEstado(EstadoCliente.VIP);
                clienteEncontrado.setCantidadCompras(clienteEncontrado.getCantidadCompras() + 1);
                venta.setEstadoVenta(EstadoVenta.CONFIRMADA);
                venta.agregarVenta(venta);
                System.out.println("VENTA EXITOSA " + venta.toString());
            } else {
                System.out.println("LA CANTIDAD DE PRODUCTO ES MAYOR A LA DISPONIBLE");
            }
        } else {
            System.out.println("TRANSACCIÓN CANCELADA");
            venta.setEstadoVenta(EstadoVenta.DECLINADA);
        }
    }

    public void aptoDescuentoExtra() {
        Venta venta = new Venta();
        ArrayList<Clientes> diezPorciento = new ArrayList<>();
        ArrayList<Clientes> quincePorciento = new ArrayList<>();
        ArrayList<Clientes> veinticincoPorciento = new ArrayList<>();

        double promedio = venta.calcularPromedioDeVentas();

        for (Clientes cliente : listaClientes) {
            if (cliente.getCantidadCompras() > promedio) {
                diezPorciento.add(cliente);
            } else if (cliente.getCantidadCompras() > 0 && cliente.getCantidadCompras() < promedio) {
                quincePorciento.add(cliente);
            } else {
                veinticincoPorciento.add(cliente);
            }
        }

        System.out.println("CLIENTES APTOS PARA 10%: " + diezPorciento.toString());
        System.out.println("CLIENTES APTOS PARA 15%: " + quincePorciento.toString());
        System.out.println("CLIENTES APTOS PARA 25%: " + veinticincoPorciento.toString());
    }




    public Clientes() {
    }

    public Clientes(int cedula, String nombre, String apellido, String genero, Date nacimiento, long telefono,
                    String email, String direccion, int cantidadCompras, Modalidad modalidad, EstadoCliente estado) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.nacimiento = nacimiento;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.cantidadCompras = cantidadCompras;
        this.modalidad = modalidad;
        this.estado = estado;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCantidadCompras() {
        return cantidadCompras;
    }

    public void setCantidadCompras(int cantidadCompras) {
        this.cantidadCompras = cantidadCompras;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public EstadoCliente getEstado() {
        return estado;
    }

    public void setEstado(EstadoCliente estado) {
        this.estado = estado;
    }
}
