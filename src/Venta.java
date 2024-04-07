import java.util.*;

public class Venta {
    private int codigoProducto;
    private String medioDePago;
    private Date fecha;
    private int cantidad;
    private double total;
    private double descuento;
    private int cedulaCliente;
    private Modalidad modalidad;
    private String direccion;
    private EstadoVenta estadoVenta;

    private static List<Venta> listaVentas = new ArrayList<>();

    public void mostrarVentas() {
        for (Venta venta : listaVentas) {
            System.out.println(venta);
        }
    }

    public double calcularTotalVentas() {
        double totalVentas = 0;
        for (Venta venta : listaVentas) {
            totalVentas += venta.getTotal();
        }
        return totalVentas;
    }

    public double calcularTotalGanancias() {
        Compra compra = new Compra();
        return calcularTotalVentas() - compra.calcularTotalInvertido();
    }

    public double calcularTotalDescuentoGenerado() {
        double totalDescuento = 0;
        for (Venta venta : listaVentas) {
            totalDescuento += venta.getDescuento();
        }
        return totalDescuento;
    }

    public void agregarVenta(Venta venta) {
        listaVentas.add(venta);
    }

    public double calcularPromedioDeVentas() {
        double totalVentas = calcularTotalVentas();
        return totalVentas / listaVentas.size();
    }

    public Venta() {
    }

    public Venta(int codigoProducto, String medioDePago, Date fecha, int cantidad, double total, double descuento,
                 int cedulaCliente, Modalidad modalidad, String direccion, EstadoVenta estadoVenta) {
        this.codigoProducto = codigoProducto;
        this.medioDePago = medioDePago;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.total = total;
        this.descuento = descuento;
        this.cedulaCliente = cedulaCliente;
        this.modalidad = modalidad;
        this.direccion = direccion;
        this.estadoVenta = estadoVenta;
    }


    @Override
    public String toString() {
        return "Venta{" +
                "codigoProducto=" + codigoProducto +
                ", medioDePago='" + medioDePago + '\'' +
                ", fecha=" + fecha +
                ", cantidad=" + cantidad +
                ", total=" + total +
                ", descuento=" + descuento +
                ", cedulaCliente=" + cedulaCliente +
                ", modalidad=" + modalidad +
                ", direccion='" + direccion + '\'' +
                ", estadoVenta=" + estadoVenta +
                '}';
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getMedioDePago() {
        return medioDePago;
    }

    public void setMedioDePago(String medioDePago) {
        this.medioDePago = medioDePago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public int getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(int cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public EstadoVenta getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(EstadoVenta estadoVenta) {
        this.estadoVenta = estadoVenta;
    }
}
