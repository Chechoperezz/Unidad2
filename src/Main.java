public class Main {
    public static void main(String[] args) {

                Producto producto = new Producto();
                producto.registrarProducto();
                producto.registrarProducto();
                producto.registrarProducto();

                producto.showProducts();


                Compra compra = new Compra();
                compra.registrarCompra();
                compra.registrarCompra();
                compra.registrarCompra();
                compra.getCompras();


                System.out.println("PRODUCTOS ACTUALIZADOS DESPUES DE COMPRA: ");
                producto.showProducts();


                Clientes clientes = new Clientes();
                clientes.registrarCliente();
                clientes.realizarCompra();
                clientes.realizarCompra();
                clientes.realizarCompra();


                System.out.println("CLIENTES REGSITRADOS");
                clientes.getClientes();

                clientes.actualizarCliente();

                System.out.println("CLIENTES REGISTRADOS ACTUALIZADOS: ");
                clientes.getClientes();

                System.out.println("CLIENTES APTOS PARA DESCUENTOS: ");
                clientes.aptoDescuentoExtra();

                Venta venta = new Venta();

                System.out.println("LISTA DE VENTAS: ");
                venta.mostrarVentas();

                System.out.println("PROMEDIO DE VENTAS: "+ venta.calcularPromedioDeVentas());
                System.out.println("TOTAL DE VENTAS: "+venta.calcularTotalVentas());
                System.out.println("TOTAL DE GANANCIAS: " + venta.calcularTotalGanancias());
                System.out.println("TOTAL EN DESCUENTO GENERADO"+ venta.calcularTotalDescuentoGenerado());

                System.out.println("INVERSIONES FUTURAS PARA PRODUCTOS: ");
                System.out.println("INVERTIR MENOS: " + producto.InvertirMenos());
                System.out.println("INVERTIR MAS: " + producto.InvertirMas());




            }
        }


