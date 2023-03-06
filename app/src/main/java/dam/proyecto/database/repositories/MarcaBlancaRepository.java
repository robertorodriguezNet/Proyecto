package dam.proyecto.database.repositories;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.MarcaBlancaDao;
import dam.proyecto.database.entity.MarcaBlancaEntity;

/**
 * @author Roberto Rodríguez Jiménez
 * @since 04/03/2023
 * @version 2023.03.06
 */
public class MarcaBlancaRepository extends Repositorio {

    private Context context;
    private MarcaBlancaDao dao;

    public MarcaBlancaRepository(Context context) {
        super(context);
        this.context = context;
        dao = db.marcaBlancaDao();
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        dao.clear();
    }

    /**
     * Inserta un objeto en la tabla
     * @param objeto
     */
    public void insert( MarcaBlancaEntity objeto ){
        dao.insert( objeto );
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<MarcaBlancaEntity> data ){
        dao.insertAll( data );
    }

    /**
     * Devuelve la colección entera de objetos guardados.
     * @return los datos.
     */
    public ArrayList<MarcaBlancaEntity> getAll(){
        return (ArrayList<MarcaBlancaEntity>) dao.getAll();
    }

    /**
     * Devuelve el id más alto
     * @return
     */
    public int getMaxId(){
        return dao.getMaxId();
    }

}
