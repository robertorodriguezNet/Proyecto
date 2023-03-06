package dam.proyecto.database.repositories;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.MedidaDao;
import dam.proyecto.database.entity.MedidaEntity;

/**
 * Repositorio para las medidas
 * @since 2023/02/06
 * @author Roberto Rodríguez
 * @version 2023.02.18
 */
public class MedidaRepository extends Repositorio{

    private Context context;
    private MedidaDao dao;

    public MedidaRepository(Context context) {
        super(context);
        this.context = context;
        this.dao = db.medidaDao();
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        dao.clear();
    }


    /**
     * Devuelve la colección completa de medidas.
     * @return
     */
    public ArrayList<MedidaEntity> getAll(){
        return (ArrayList<MedidaEntity>) dao.getAll();
    }

    public MedidaEntity getById( String id ){
        return ( MedidaEntity ) dao.findById( id );
    }

    /**
     * Inserta objeto en MedidaEntity
     * @param objeto que se inserta
     */
    public void insert( MedidaEntity objeto ){
        dao.insert( objeto );
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<MedidaEntity> data ){
        dao.insertAll( data );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
