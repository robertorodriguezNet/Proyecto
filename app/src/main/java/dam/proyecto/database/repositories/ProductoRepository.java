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
     * Devuelve un listado completo de los registros.
     * @return listado compde registros
     */
    public ArrayList<ProductoEntity> getAll(){
        return (ArrayList<ProductoEntity>) DAO.getAll();
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
     * Devuelve el último id que se ha generado de forma automática.
     * Los id generados de forma automática tienen un id de 13 dígitos
     * y comienzan por 1.
     * @return el último id.
     */
    public String getUltimoIdAutomatico(){
        return DAO.getUltimoIdAutomatico();
   }


    /**
     * Inserta una colección de productos
     */
    public void insertAll( List<ProductoEntity> data ){
        DAO.insertAll( data );
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
