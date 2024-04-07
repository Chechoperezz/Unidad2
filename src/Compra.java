import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Compra {

    private double subtotal;
    private double iva;
    private double total;
    private String consecutivo;
    private Date fecha;
    private String proveedor;
    private int codigoProducto;
    private double precioDeCompra;
    private double precioDeVenta;
    private int unidades;
    private static final double IVA_VALUE = 0.19;
    private static List<Compra> listaCompras = new ArrayList<>();

    public void getCompras(){
        for (Compra compra : listaCompras){
            System.out.println(compra);
        }
    }

    public void registrarCompra() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Scanner scn = new Scanner(System.in);

        System.out.println("CÓDIGO DEL PRODUCTO:");
        int codigoProducto = scn.nextInt();
        scn.nextLine();

        System.out.println("CONSECUTIVO:");
        String consecutivo = scn.nextLine();

        System.out.println("FECHA DE COMPRA [yyyy/MM/dd]:");
        String fechaStr = scn.nextLine();
        Date fecha;
        try {
            fecha = dateFormat.parse(fechaStr);
        } catch (ParseException e) {
            System.out.println("Formato de fecha incorrecto. Use yyyy/MM/dd.");
            return;
        }

        System.out.println("PROVEEDOR:");
        String proveedor = scn.nextLine();

        System.out.println("PRECIO DE COMPRA:");
        double precioCompra = scn.nextDouble();

        if (precioCompra <= 0) {
            System.out.println("El precio de compra debe ser mayor que cero.");
            return;
        }

        double precioVenta = precioCompra * 1.4;

        System.out.println("UNIDADES:");
        int unidades = scn.nextInt();

        if (unidades <= 0) {
            System.out.println("Las unidades deben ser mayores que cero.");
            return;
        }

        double subtotal = precioCompra * unidades;
        double iva = subtotal * IVA_VALUE;
        double total = subtotal + iva;

        Producto producto = new Producto();

        if (producto.actualizarProductoCompra(codigoProducto, precioCompra, precioVenta, unidades)) {
            Compra compra = new Compra();
            compra.setCodigoProducto(codigoProducto);
            compra.setConsecutivo(consecutivo);
            compra.setFecha(fecha);
            compra.setProveedor(proveedor);
            compra.setPrecioDeCompra(precioCompra);
            compra.setPrecioDeVenta(precioVenta);
            compra.setUnidades(unidades);
            compra.setSubtotal(subtotal);
            compra.setIva(iva);
            compra.setTotal(total);

            listaCompras.add(compra);
            System.out.println("COMPRA REGISTRADA EXITOSAMENTE:\n" + compra.toString());

        } else {
            System.out.println("COMPRA NO REGISTRADA");
        }
    }

    public double calcularTotalInvertido() {
        double totalInvertido = 0;
        for (Compra compra : listaCompras) {
            totalInvertido += compra.getTotal();
        }
        return totalInvertido;
    }

    public double calcularTotalIVAPagado() {
        double total = 0;
        for (Compra compra : listaCompras) {
            total += compra.getIva();
        }
        return total;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public double getPrecioDeCompra() {
        return precioDeCompra;
    }

    public void setPrecioDeCompra(double precioDeCompra) {
        this.precioDeCompra = precioDeCompra;
    }

    public double getPrecioDeVenta() {
        return precioDeVenta;
    }

    public void setPrecioDeVenta(double precioDeVenta) {
        this.precioDeVenta = precioDeVenta;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "subtotal=" + subtotal +
                ", iva=" + iva +
                ", total=" + total +
                ", consecutivo='" + consecutivo + '\'' +
                ", fecha=" + fecha +
                ", proveedor='" + proveedor + '\'' +
                ", codigoProducto=" + codigoProducto +
                ", precioDeCompra=" + precioDeCompra +
                ", precioDeVenta=" + precioDeVenta +
                ", unidades=" + unidades +
                ", IVA=" + IVA_VALUE +
                '}';
    }
}

