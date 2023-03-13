package dam.proyecto.database.repositories;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.ProductoDao;
import dam.proyecto.database.entity.ProductoEntity;

/**
 * Repositorio para los productos
 * @author Roberto Rodríguez
 * @since 23/01/2023
 * @version 2023.03.08
 */
public class ProductoRepository extends Repositorio {

    private final ProductoDao DAO;

    public ProductoRepository(Context context) {
        super(context);
        DAO = db.productoDao();
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        DAO.clear();
    }

    /**
     * Devuelve un listado completo de los registros.
     * @return listado compde registros
     */
    public ArrayList<ProductoEntity> getAll(){
        return (ArrayList<ProductoEntity>) DAO.getAll();
    }

    /**
     * Devuelve la denominación de un producto a partir del id
     * @param id el id buscado
     * @return denominación
     */
    public String getDenominacionProducto( String id ){
        try {
            ProductoEntity producto = DAO
                    .findById(id);
            return producto.getDenominacion();
        }catch ( Exception e ){
            return "";
        }
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

        // Devolvemos el id incrementado
        return incrementarValor( getMaxId( list ) );
    }

    /**
     * Devuelve el valor más alto del campo id de la colección de productos.
     * @param list la colección de productos n la que buscar
     * @return el valor más alto del campo id
     */
    private String getMaxId( ArrayList<ProductoEntity> list){

        // Queremos ordenar la lista de mayor a menor por el campo "id"
        // Para ello debemos sobre escribir el método compare de la clase Collections
        // dentro de collection.sort
        list.sort((pe1, pe2) -> Long.valueOf(pe2.getId()).compareTo(Long.valueOf(pe1.getId())));

        return list.get( 0 ).getId();
    }



    /**
     * Elimina el producto de la base de datos a partir de su id
     * @param id buscado para ser eliminado
     */
    public void deleteById( String id ){
        DAO.deleteById( id );
    }

    /**
     * Comprueba si un prooducto, por su id, exista en la base de datos
     * @param id el producto buscao
     * @return true si el producto existe
     */
    public boolean existsProducto(String id) {
        ProductoEntity objeto = db
                .productoDao()
                .findById(id);
        return objeto != null;
    }

    /**
     * Devuelve un producto a través del id
     * @param id id del producto
     * @return el producto buscado
     */
    public ProductoEntity getById( String id ){
        return DAO.findById( id );
    }


    /**
     * Inserta una colección de productos
     */
    public void insertAll( List<ProductoEntity> data ){
        DAO.insertAll( data );
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
     * Inserta un producto en la base de datos
     * @param producto producto que se quiere insertar
     */
    public void insertProducto( ProductoEntity producto ){
        DAO.insert( producto );
    }

    /**
     * Actualiza un producto
     * @param objeto producto que se actualiza
     */
    public void update( ProductoEntity objeto ){
        DAO.update( objeto );
    }

    @Override
    @NonNull
    public String toString() {
        return super.toString();
    }
}
