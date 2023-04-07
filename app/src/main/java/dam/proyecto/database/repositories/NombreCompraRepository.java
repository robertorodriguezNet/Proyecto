package dam.proyecto.database.repositories;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.NombreCompraDao;
import dam.proyecto.database.entity.NombreCompraEntity;

/**
 * @author Roberto Rodríguez
 * @version 2023.03.07
 * @since 23/01/2023
 */
public class NombreCompraRepository extends Repositorio {

    private final NombreCompraDao DAO;

    public NombreCompraRepository(Context context) {
        super(context);
        DAO = db.nombreCompraDao();
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear() {
        DAO.clear();
    }

    /**
     * Elimina la compra de la base de datos
     *
     * @param compra que se debe eliminar
     */
    public void delete(NombreCompraEntity compra) {
        DAO.delete(compra);

        Log.d("LDLC", "Se ha eliminado el objeto");
    }

    /**
     * Devuelve un objeto a partir del id
     *
     * @param id buscado
     * @return el objeto con el id buscado
     */
    public NombreCompraEntity findById(String id) {
        return DAO.findById(id);
    }

    /**
     * Devuelve un listado completo de los registros.
     *
     * @return listado completo de objetos
     */
    public ArrayList<NombreCompraEntity> getAll() {
        return (ArrayList<NombreCompraEntity>) DAO.getAll();
    }

    /**
     * Devuelve un listado con las compras hechas en un comercio dado
     *
     * @param idComercio buscado
     * @return listado de compras en el comercio
     */
    public ArrayList<String> getAllByIdComercio(int idComercio) {
        return (ArrayList<String>) DAO.getAllByComercio(idComercio);
    }

    /**
     * Obtener un nombre de la compra a partir del id
     *
     * @param id el id de la compra, con formato aammddhhmm
     * @return nombre de la compra
     */
    public NombreCompraEntity getById(String id) {
        return DAO.findById(id);
    }

    /**
     * Devuleve el id del comercio asociado a la compra dada
     *
     * @param idCompra compra de la que se quiere obtener el comercio
     * @return int el id de la compra
     */
    public int getIdComercio(String idCompra) {
        return DAO.getIdComercio(idCompra);
    }

    public String getNombreComercioByCompra(String idCompra) {
        return DAO.getNombreComercio(idCompra);
    }

    /**
     * Inserta un objeto en la base de datos
     *
     * @param objeto que se debe insertar
     */
    public void insert(NombreCompraEntity objeto) {

        Log.d("LDLC", "Se ha insertado el objeto");
        DAO.insert(objeto);
    }

    /**
     * Inserta una colección de objetos
     *
     * @param data colección de objetos
     */
    public void insertAll(List<NombreCompraEntity> data) {
        DAO.insertAll(data);
    }

    public void update(NombreCompraEntity nombreCompra) {
        DAO.update(nombreCompra);
    }

    /**
     * Actualiza el id de un NombreCompra.
     * Se exige que el id esté validado.
     *
     * @param idAnterior    el id que se quiere actualizar.
     * @param idNuevo       el id nuevo.
     * @param valido        indica si el valor de del id nuevo es válido.
     *                      Debe ser proporcionado por el método que lo invoque.
     * @param cambiarNombre especifica si ha de cambiarse el nombre y escribir el mismo que el id.
     */
    public void updateId(String idAnterior, String idNuevo, boolean valido, boolean cambiarNombre) {
        if (valido) {
            if (cambiarNombre) {
                DAO.updateId(idAnterior, idNuevo, idNuevo);
                Log.d("LDLC","Repository, cambiar nombre: \n" + idNuevo);

            }else{
                DAO.updateId(idAnterior, idNuevo);
                Log.d("LDLC","Repository, sin cambio de nombre");
            }
        }

    }


    @Override
    @NonNull
    public String toString() {
        return super.toString();
    }

}
