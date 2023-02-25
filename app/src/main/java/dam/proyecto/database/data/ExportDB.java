package dam.proyecto.database.data;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import dam.proyecto.activities.MainActivity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.entity.TagEntity;
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
        String producto_data = leerProductos( "ProductoEntity" );
        String nombre_Compra_data = leerNombreCompra( "NombreCompraEntity");
        String tag_data = leerTag( "TagEntity" );
//        leerComercio();
//        leerMedidas();
//        leerCompraEntitys();
//        leerMarcas();
//        leerTagsProductoEntity();
//        leerOfertaEntity();

        Log.d("LDLC","ExportDB: exportar la base de datos: \n"
        + tag_data );

    }

    private static String leerProductos( String tipo) {
        ProductoRepository repository = new ProductoRepository(context);
        ArrayList<ProductoEntity> data = repository.getAll();

        String code = "\nprivate static final " + tipo + "[] PRODUCTO_DATA = {";
        for( ProductoEntity objeto : data){
            code += "\n    new " + tipo + "("
                    + "\"" + objeto.getId() + "\","
                    + "\"" + objeto.getDenominacion() + "\","
                    + objeto.getMarca() + ","
                    + objeto.getUnidades() + ","
                    + "\"" +  objeto.getMedida() + "\","
                    + "(float)" + objeto.getCantidad() + "),";
        }
        code += "\n};";

        return code;
    }

    private static String leerNombreCompra( String tipo) {
        NombreCompraRepository repository = new NombreCompraRepository(context);
        ArrayList<NombreCompraEntity> data = repository.getAll();

        String code = "\nprivate static final " + tipo + "[] PRODUCTO_DATA = {";
        for( NombreCompraEntity objeto : data){
            code += "\n    new " + tipo + "("
                    + "\"" + objeto.getId() + "\","
                    + "\"" + objeto.getNombre() + "\","
                    + objeto.getComercio() + "),";
        }
        code += "\n};";

        return code;
    }

    private static String leerTag( String tipo) {
        TagRepository repository = new TagRepository(context);
        ArrayList<TagEntity> data = repository.getAll();

        String code = "\nprivate static final " + tipo + "[] PRODUCTO_DATA = {";
        for( TagEntity objeto : data){
            code += "\n    new " + tipo + "("
                    + "\"" + objeto.getName() + "\"),";
        }
        code += "\n};";

        return code;
    }

    private static void leerComercio() {
        ComercioRespository repository = new ComercioRespository(context);
        repository.clear();
        repository.insertAll(ComercioData.getData());
    }

    private static void leerMedidas() {
        MedidaRepository repository = new MedidaRepository(context);
        repository.clear();
        repository.insertAll(MedidaData.getData());
    }

    private static void leerCompraEntitys() {
        CompraRepository repository = new CompraRepository(context);
        repository.clear();
        repository.insertAll(CompraEntityData.getData());
    }

    private static void leerMarcas() {
        MarcaRepository repository = new MarcaRepository(context);
        repository.clear();
        repository.insertAll(MarcaData.getData());
    }


    private static void leerTagsProductoEntity() {
        TagProductoRepository repository = new TagProductoRepository(context);
        repository.clear();
        repository.insertAll(TagProductoData.getData());
    }

    private static void leerOfertaEntity() {
        OfertaRespository repository = new OfertaRespository(context);
        repository.clear();
        repository.insertAll(OfertaData.getData());
    }


}
