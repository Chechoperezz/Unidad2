import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Producto {

    private static int generadorCodigo = 1000;
    private int codigo;
    private String nombre;
    private String marca;
    private String color;
    private double precioDeVenta;
    private double precioDeCompra;
    private double percentage;
    private int unidades;
    private double medidas;
    private String categoria;
    private int cantidadDeVentas;

    private static List<Producto> productos = new ArrayList<>();

    public void showProducts() {
        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }
    }

    public void registrarProducto() {
        Scanner scn = new Scanner(System.in);

        System.out.println("Ingrese el Nombre del producto:");
        String nombre = scn.nextLine();

        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("PRODUCTO ANTERIORMENTE REGISTRADO: " + producto.toString());
                System.out.println("DESEA MODIFICAR EL PRODUCTO? PRESIONE 1(SI) | 2(NO)");
                int response = scn.nextInt();
                scn.nextLine();

                if (response == 1) {
                    update(producto.getCodigo());
                    System.out.println("PRODUCTO MODIFICADO EXITOSAMENTE");
                    return;
                } else {
                    System.out.println("PRODUCTO YA REGISTRADO. NO ES NECESARIO AGREGARLO NUEVAMENTE.");
                    return;
                }
            }
        }

        System.out.println("Ingrese la marca del producto:");
        String marca = scn.nextLine();

        System.out.println("Ingrese el color del producto:");
        String color = scn.nextLine();

        System.out.println("Ingrese el precio de venta del producto:");
        double precioVenta = scn.nextDouble();

        System.out.println("Ingrese el precio de compra del producto:");
        double precioCompra = scn.nextDouble();

        System.out.println("Ingrese el porcentaje de descuento del producto:");
        double porcentajeDescuento = scn.nextDouble();

        System.out.println("Ingrese las unidades del producto:");
        int unidades = scn.nextInt();

        System.out.println("Ingrese las medidas del producto:");
        double medidas = scn.nextDouble();
        scn.nextLine();

        System.out.println("Ingrese la categoría del producto:");
        String categoria = scn.nextLine();

        Producto nuevoProducto = new Producto();
        nuevoProducto.setCodigo();
        nuevoProducto.setNombre(nombre);
        nuevoProducto.setMarca(marca);
        nuevoProducto.setColor(color);
        nuevoProducto.setPrecioDeVenta(precioVenta);
        nuevoProducto.setPrecioDeCompra(precioCompra);
        nuevoProducto.setPercentage(porcentajeDescuento);
        nuevoProducto.setUnidades(unidades);
        nuevoProducto.setMedidas(medidas);
        nuevoProducto.setCategoria(categoria);

        productos.add(nuevoProducto);
        System.out.println("PRODUCTO REGISTRADO EXITOSAMENTE:\n" + nuevoProducto);
    }

    public void update(int codigo) {
        Scanner scn = new Scanner(System.in);

        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                System.out.println("CATEGORIA DE PRODUCTO:");
                String categoria = scn.nextLine();
                if (!categoria.isEmpty()) {
                    producto.setCategoria(categoria);
                }

                System.out.println("COLOR DEL PRODUCTO:");
                String color = scn.nextLine();
                if (!color.isEmpty()) {
                    producto.setColor(color);
                }

                System.out.println("UNIDADES COMPRADAS:");
                int unidades = scn.nextInt();

                if (unidades > 0) {
                    producto.setUnidades(unidades);
                }

                System.out.println("PRECIO DE COMPRA:");
                double precioCompra = scn.nextDouble();
                scn.nextLine();
                if (precioCompra > 0) {
                    producto.setPrecioDeCompra(precioCompra);
                }

                System.out.println("PRECIO DE VENTA:");
                double precioVenta = scn.nextDouble();
                scn.nextLine();
                if (precioVenta > 0) {
                    producto.setPrecioDeVenta(precioVenta);
                }

                System.out.println("PORCENTAJE DE DESCUENTO:");
                double porcentajeDescuento = scn.nextDouble();
                scn.nextLine();
                if (porcentajeDescuento >= 0 && porcentajeDescuento <= 100) {
                    producto.setPercentage(porcentajeDescuento);
                }

                System.out.println("MEDIDAS DE PRODUCTO:");
                double medidas = scn.nextDouble();
                scn.nextLine();
                if (medidas > 0) {
                    producto.setMedidas(medidas);
                }

                System.out.println("PRODUCTO ACTUALIZADO CORRECTAMENTE");
                return;
            }
        }

        System.out.println("No se encontró ningún producto con el código especificado.");
    }

    public Producto buscarProducto(int codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    public boolean actualizarProductoCompra(int codigo, double precioCompra, double precioVenta, int unidades) {
        boolean passed = false;
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                producto.setPrecioDeCompra(precioCompra);
                producto.setPrecioDeVenta(precioVenta);
                producto.setUnidades(producto.getUnidades() + unidades);
                passed = true;
                break;
            }
        }
        return passed;
    }

    public boolean registrarVenta(Venta venta) {
        boolean passed = false;
        if (venta.getCantidad() > 0) {
            for (Producto producto : productos) {
                if (venta.getCodigoProducto() == producto.getCodigo() && producto.getUnidades() >= venta.getCantidad()) {
                    producto.setUnidades(producto.getUnidades() - venta.getCantidad());
                    producto.setCantidadDeVentas(producto.getCantidadDeVentas() + venta.getCantidad());
                    passed = true;
                    break;
                }
            }
        }
        return passed;
    }

    public ArrayList<Producto> InvertirMas() {
        Venta venta = new Venta();
        ArrayList<Producto> productosAptos = new ArrayList<>();
        double setentaPorCientoVentas = venta.calcularTotalVentas() * 0.70;

        for (Producto producto : productos) {
            if (producto.getCantidadDeVentas() > setentaPorCientoVentas) {
                productosAptos.add(producto);
            }
        }

        return productosAptos;
    }

    public ArrayList<Producto> InvertirMenos() {
        Venta venta = new Venta();
        ArrayList<Producto> productosAptos = new ArrayList<>();
        double setentaPorCientoVentas = venta.calcularTotalVentas() * 0.70;

        for (Producto producto : productos) {
            if (producto.getCantidadDeVentas() < setentaPorCientoVentas) {
                double precioVentaConDescuento = producto.getPrecioDeVenta() * 0.65;
                producto.setPrecioDeVenta(precioVentaConDescuento);
                productosAptos.add(producto);
            }
        }

        return productosAptos;
    }

    public Producto() {

    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                ", color='" + color + '\'' +
                ", precioDeVenta=" + precioDeVenta +
                ", precioDeCompra=" + precioDeCompra +
                ", percentage=" + percentage +
                ", unidades=" + unidades +
                ", medidas=" + medidas +
                ", categoria='" + categoria + '\'' +
                ", cantidadDeVentas=" + cantidadDeVentas +
                '}';
    }

    public Producto(int codigo, String nombre, String marca, String color,
                    double precioDeVenta, double precioDeCompra, double percentage, int unidades, double medidas,
                    String categoria, int cantidadDeVentas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.marca = marca;
        this.color = color;
        this.precioDeVenta = precioDeVenta;
        this.precioDeCompra = precioDeCompra;
        this.percentage = percentage;
        this.unidades = unidades;
        this.medidas = medidas;
        this.categoria = categoria;
        this.cantidadDeVentas = cantidadDeVentas;
    }

    public static int getGeneradorCodigo() {
        return generadorCodigo;
    }

    public static void setGeneradorCodigo(int generadorCodigo) {
        Producto.generadorCodigo = generadorCodigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo() {
        this.codigo = ++generadorCodigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrecioDeVenta() {
        return precioDeVenta;
    }

    public void setPrecioDeVenta(double precioDeVenta) {
        this.precioDeVenta = precioDeVenta;
    }

    public double getPrecioDeCompra() {
        return precioDeCompra;
    }

    public void setPrecioDeCompra(double precioDeCompra) {
        this.precioDeCompra = precioDeCompra;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getMedidas() {
        return medidas;
    }

    public void setMedidas(double medidas) {
        this.medidas = medidas;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidadDeVentas() {
        return cantidadDeVentas;
    }

    public void setCantidadDeVentas(int cantidadDeVentas) {
        this.cantidadDeVentas = cantidadDeVentas;
    }
}
