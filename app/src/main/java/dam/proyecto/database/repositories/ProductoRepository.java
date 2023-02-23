package dam.proyecto.database.repositories;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.entity.ProductoEntity;

/**
 * Repositorio para los productos
 * @since 2023/01/23
 * @author Roberto Rodríguez
 * @version 2023.02.17
 */
public class ProductoRepository extends Repositorio {

    public ProductoRepository(Context context) {
        super(context);
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        db.productoDao().clear();
    }

    /**
     * Inserta una colección de productos
     */
    public void insertAll( List<ProductoEntity> data ){
        db.productoDao().insertAll( data );
    }

    /**
     * Devuelve un listado completo de los registros.
     * @return
     */
    public ArrayList<ProductoEntity> getAll(){
        return (ArrayList<ProductoEntity>) db.productoDao().getAll();
    }

    /**
     * Devuelve la denominación de un producto a partir del id
     * @param id el id buscado
     * @return
     */
    public String getDenominacionProducto( String id ){
        try {
            ProductoEntity producto = db.productoDao()
                    .findById(id);
            return producto.getDenominacion();
        }catch ( Exception e ){
            return "";
        }
    }
    /**
     * Inserta un producto en la base de datos
     * @param producto
     */
    public void insertProducto( ProductoEntity producto ){
        db.productoDao().insert( producto );
    }

    /**
     * Devuelve un id automático válido.
     * Los id's automáticos comienzan con
     * PROBLEMA:
     *      los códigos de barra tienen 13 dígitos, demasiados para un Long.
     *      Como el id automático comienza por 1, no podemos convertir el String en número.
     * SOLUCIÓN:
     *      Antes de validar el String como Long, hay que dividirlo.
     *
     * @return el id generado
     */
    public String getIdAutomatico(){

        // Obtenemos todos los productos cuyo id comience por 1
        ArrayList<ProductoEntity> list = ( ArrayList<ProductoEntity> ) db
                .productoDao()
                .getAutomaticId();

        // Pedimos el valor máximo del campo id
        String idStr = getMaxId( list );

        // Incrementamos el valor
        String idIncrementado = incrementarValor( idStr );

        //Debemos rellenar con "0" hasta trece caracteres
//        idStr = String.format( "%13s", idIncrementado).replace(' ', '0');

        // Devolvemos el id
        return idIncrementado;
    }

    /**
     * Devuelve el valor más alto del campo id de la colección de productos.
     * @param list la colección de productos n la que buscar
     * @return el valor más alto del campo id
     */
    private String getMaxId( ArrayList<ProductoEntity> list){

        ArrayList<ProductoEntity> coleccion = list;

        // Queremos ordenar la lista de mayor a menor por el campo "id"
        // Para ello debemos sobre escribir el método compare de la clase Collections
        // dentro de collection.sort
        Collections.sort(coleccion, new Comparator<ProductoEntity>() {
            @Override
            public int compare(ProductoEntity pe1, ProductoEntity pe2) {
                return new Long( pe2.getId() ).compareTo(new Long( pe1.getId() ) );
            }
        });

        return coleccion.get( 0 ).getId();
    }

    /**
     * Recibe un valor numérico en formato String y lo devuelve incrementado en 1
     * @param idStr El valor que se debe icrementar
     * @return el valor incrementado
     */
    private static String incrementarValor( String idStr ){

        // Debemos obtener un valor numérico válido y lo incrementamos
        Long idL = Long.valueOf( idStr );
        idL++;

        return String.valueOf( idL );
    }

    /**
     * Comprueba si un prooducto, por su id, exista en la base de datos
     * @param id el producto buscao
     * @return true si el producto existe
     */
    public boolean existsProducto(String id) {
        ProductoEntity objeto = null;
        objeto = db
                .productoDao()
                .findById(id);
        return objeto != null;
    }

    /**
     * Elimina el producto de la base de datos a partir de su id
     * @param id buscado para ser eliminado
     */
    public void deleteById( String id ){
        db.productoDao().deleteById( id );
    }

    /**
     * Devuelve un producto a través del id
     * @param id
     * @return
     */
    public ProductoEntity getById( String id ){
        return db.productoDao().findById( id );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
