package dam.proyecto.database.data;


import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import dam.proyecto.controllers.NombreCompraController;
import dam.proyecto.controllers.TagController;
import dam.proyecto.database.entity.ComercioEntity;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.MarcaBlancaEntity;
import dam.proyecto.database.entity.MarcaEntity;
import dam.proyecto.database.entity.MedidaEntity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.entity.OfertaEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.entity.TagEntity;
import dam.proyecto.database.entity.TagsProductoEntity;
import dam.proyecto.database.repositories.ComercioRespository;
import dam.proyecto.database.repositories.CompraRepository;
import dam.proyecto.database.repositories.MarcaBlancaRepository;
import dam.proyecto.database.repositories.MarcaRepository;
import dam.proyecto.database.repositories.MedidaRepository;
import dam.proyecto.database.repositories.OfertaRespository;
import dam.proyecto.database.repositories.ProductoRepository;
import dam.proyecto.database.repositories.TagProductoRepository;
import dam.proyecto.database.repositories.TagRepository;

/**
 *
 * @see "https://youtu.be/KvM65JoaeFE"
 * @author  Roberto Rodríguez Jiménez
 * @since 17/02/2023
 * @version 2023.02.17
 */
public class ExportDB {

    private static Context context;

    public static void exportDB( Context c ){

        context = c;

        // Debemos obtner todos los registros de todas las tablas
//        leerComercio();
        exportarCompraEntity();
//        leerMarcaBlanca();
//        leerMarcas();
//        leerMedidas();
        exportarNombreCompraEntity();
//        leerOfertaEntity();
        exportarProductoEntity();
        exportarTagEnity();
//        leerTagsProductoEntity();

    }

    /**
     * Grabar el contenido de data en el archivo file
     * @param file
     * @param data
     */
    private static void grabar( String file, String data ){
        OutputStreamWriter osr = null;
        try{
            osr = new OutputStreamWriter(
                            context.openFileOutput( file , Context.MODE_PRIVATE )
                    );

            osr.write( data );
            osr.flush();
        } catch ( IOException e ){
            Toast.makeText(context, "No se pudo crear el archivo " + file, Toast.LENGTH_SHORT).show();
        }finally {
            if( osr != null ){
                try{
                    osr.close();
                }catch ( Exception e ){
                    Toast.makeText(context, "Error al cerrar el fulujo de datos", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private static void leerComercio() {
        ComercioRespository repository = new ComercioRespository(context);
        ArrayList<ComercioEntity> data = repository.getAll();

        boolean salto = false;
        String code = "";
        for( ComercioEntity objeto : data){
            code += objeto.getId() + ","
                    + objeto.getName();
            salto = true;
            if( salto ){
                code += "\n";
            }
        }
        grabar( "ComercioEntity.csv", code );
    }

    /**
     * Creamos el archivo de productos.
     * El id de cada registro es relevante, no puede cambiar, su formato
     * es el código de barras.
     */
    private static void exportarProductoEntity() {
        ProductoRepository repository = new ProductoRepository(context);
        ArrayList<ProductoEntity> data = repository.getAll();

        boolean salto = false;
        String code = "";
        for( ProductoEntity objeto : data){
            code += objeto.getId() + ","
                    + objeto.getDenominacion() + ","
                    + objeto.getMarca() + ","
                    + objeto.getUnidades() + ","
                    + objeto.getMedida() + ","
                    + objeto.getCantidad();
            salto = true;
            if( salto ){
                code += "\n";
            }
        }
        grabar( "ProductoEntity.csv", code );
    }

    private static void exportarNombreCompraEntity() {
        NombreCompraController controller = new NombreCompraController(context);
        ArrayList<NombreCompraEntity> data = controller.getAll();

        boolean salto = false;
        String code = "";
        for( NombreCompraEntity objeto : data){
            code += objeto.getId() + ","
                    + objeto.getNombre() + ","
                    + objeto.getComercio();
            salto = true;
            if( salto ){
                code += "\n";
            }
        }
        grabar( "NombreCompraEntity.csv", code );
    }

    /**
     * Exportar los tags.
     * Hay que tener cuidado, porque los id son generados automáticamente,
     * debemos asegurarnos de que no se han cambiado.
     */
    private static void exportarTagEnity() {
        TagController controller = new TagController(context);
        ArrayList<TagEntity> data = controller.getAll();

        boolean salto = false;
        String code = "";
        for( TagEntity objeto : data){
            code += objeto.getName();
            salto = true;
            if( salto ){
                code += "\n";
            }
        }
        grabar( "TagEntity.csv", code );
    }

    private static void leerMarcaBlanca() {
        MarcaBlancaRepository repository = new MarcaBlancaRepository(context);
        ArrayList<MarcaBlancaEntity> data = repository.getAll();

        boolean salto = false;
        String code = "";
        for( MarcaBlancaEntity objeto : data){
            code += objeto.getMarca() + ","
                 + objeto.getComercio();
            salto = true;
            if( salto ){
                code += "\n";
            }
        }
        grabar( "TagEntity.csv", code );
    }

    private static void leerMedidas() {
        MedidaRepository repository = new MedidaRepository(context);
        ArrayList<MedidaEntity> data = repository.getAll();

        boolean salto = false;
        String code = "";
        for( MedidaEntity objeto : data){
            code += objeto.getId() + ","
                    + objeto.getDescription();
            salto = true;
            if( salto ){
                code += "\n";
            }
        }
        grabar( "MedidaEntity.csv", code );
    }

    /**
     * El id de la compra no se exporta ya que se genera de forma
     * automática al crearse el objeto.
     */
    private static void exportarCompraEntity() {
        CompraRepository repository = new CompraRepository(context);
        ArrayList<CompraEntity> data = repository.getAll();

        boolean salto = false;
        String code = "";
        for( CompraEntity objeto : data){
            code += objeto.getProducto() + ","
                    + objeto.getFecha() + ","
                    + objeto.getCantidad() + ","
                    + objeto.getPagado() + ","
                    + objeto.getPrecio() + ","
                    + objeto.getPrecioMedido() + ","
                    + objeto.getOferta();
            salto = true;
            if( salto ){
                code += "\n";
            }
        }
        grabar( "CompraEntity.csv", code );
    }

    private static void leerMarcas() {
        MarcaRepository repository = new MarcaRepository(context);
        ArrayList<MarcaEntity> data = repository.getAll();

        boolean salto = false;
        String code = "";
        for( MarcaEntity objeto : data){
            code += objeto.getId() + ","
                    + objeto.getName();
            salto = true;
            if( salto ){
                code += "\n";
            }
        }
        grabar( "MarcaEntity.csv", code );
    }


    private static void leerTagsProductoEntity() {
        TagProductoRepository repository = new TagProductoRepository(context);
        ArrayList<TagsProductoEntity> data = repository.getAll();

        boolean salto = false;
        String code = "";
        for( TagsProductoEntity objeto : data){
            code += objeto.getId() + ","
                    + objeto.getProducto()
                    + objeto.getTag();
            salto = true;
            if( salto ){
                code += "\n";
            }
        }
        grabar( "TagsProductoEntity.csv", code );
    }

    private static void leerOfertaEntity() {
        OfertaRespository repository = new OfertaRespository(context);
        ArrayList<OfertaEntity> data = repository.getAll();

        boolean salto = false;
        String code = "";
        for( OfertaEntity objeto : data){
            code += objeto.getId() + ","
                    + objeto.getAbbr()
                    + objeto.getTexto();
            salto = true;
            if( salto ){
                code += "\n";
            }
        }
        grabar( "OfertaEntity.csv", code );
    }

}
