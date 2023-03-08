package dam.proyecto.database.repositories;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.MarcaBlancaDao;
import dam.proyecto.database.entity.MarcaBlancaEntity;

public class MarcaBlancaRepository extends Repositorio {

    private final MarcaBlancaDao DAO;

    public MarcaBlancaRepository(Context context) {
        super(context);
        DAO = db.marcaBlancaDao();
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        DAO.clear();
    }

    /**
     * Devuelve el id más alto registrado
     * @return el id más alto registrado
     */
    public int getMaxId(){
        return DAO.getMaxId();
    }

    /**
     * Inserta un objeto en la tabla
     * @param objeto que se inserta
     */
    public void insert( MarcaBlancaEntity objeto ){
        DAO.insert( objeto );
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<MarcaBlancaEntity> data ){
        DAO.insertAll( data );
    }

    /**
     * Devuelve la colección entera de objetos guardados.
     * @return los datos.
     */
    public ArrayList<MarcaBlancaEntity> getAll(){
        return (ArrayList<MarcaBlancaEntity>) DAO.getAll();
    }

}
