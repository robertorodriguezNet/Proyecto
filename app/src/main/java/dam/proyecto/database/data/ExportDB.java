package dam.proyecto.database.data;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import dam.proyecto.activities.MainActivity;
import dam.proyecto.database.entity.ComercioEntity;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.MarcaEntity;
import dam.proyecto.database.entity.MedidaEntity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.entity.OfertaEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.entity.TagEntity;
import dam.proyecto.database.entity.TagsProductoEntity;
import dam.proyecto.database.repositories.ComercioRespository;
import dam.proyecto.database.repositories.CompraRepository;
import dam.proyecto.database.repositories.MarcaRepository;
import dam.proyecto.database.repositories.MedidaRepository;
import dam.proyecto.database.repositories.NombreCompraRepository;
import dam.proyecto.database.repositories.OfertaRespository;
import dam.proyecto.database.repositories.ProductoRepository;
import dam.proyecto.database.repositories.TagProductoRepository;
import dam.proyecto.database.repositories.TagRepository;

/**
 *
 * @author  Roberto Rodríguez Jiménez
 * @since 17/02/2023
 * @version 2023.02.17
 */
public class ExportDB {

    private static Context context;

    public static void export( Context c ){

        context = c;

        // Debemos obtner todos los registros de todas las tablas
        String producto_data = leerProductos();
        String nombre_Compra_data = leerNombreCompra();
        String tag_data = leerTag();
        String comercio_data = leerComercio();
        String medidas_data =leerMedidas();
        String compra_data =leerCompraEntitys();
        String marca_data =leerMarcas();
        String tagsProducto_data =leerTagsProductoEntity();
        String oferta_data =leerOfertaEntity();

        Log.d("LDLC","ExportDB: exportar la base de datos: \n"
        + producto_data );

    }

    private static String leerProductos() {
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
        return code;
    }

    private static String leerNombreCompra() {
        NombreCompraRepository repository = new NombreCompraRepository(context);
        ArrayList<NombreCompraEntity> data = repository.getAll();

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

        return code;
    }

    private static String leerTag() {
        TagRepository repository = new TagRepository(context);
        ArrayList<TagEntity> data = repository.getAll();

        boolean salto = false;
        String code = "";
        for( TagEntity objeto : data){
            code += objeto.getId() + ","
                    + objeto.getName();
            salto = true;
            if( salto ){
                code += "\n";
            }
        }

        return code;
    }

    private static String leerComercio() {
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

        return code;
    }

    private static String leerMedidas() {
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

        return code;
    }

    private static String leerCompraEntitys() {
        CompraRepository repository = new CompraRepository(context);
        ArrayList<CompraEntity> data = repository.getAll();

        boolean salto = false;
        String code = "";
        for( CompraEntity objeto : data){
            code += objeto.getId() + ","
                    + objeto.getProducto()
                    + objeto.getFecha()
                    + objeto.getPrecio()
                    + objeto.getCantidad()
                    + objeto.getPagado()
                    + objeto.getPrecioMedido()
                    + objeto.getOferta();
            salto = true;
            if( salto ){
                code += "\n";
            }
        }

        return code;
    }

    private static String leerMarcas() {
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

        return code;
    }


    private static String leerTagsProductoEntity() {
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

        return code;
    }

    private static String leerOfertaEntity() {
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

        return code;
    }


}
