package dam.proyecto.database.repositories;

import android.content.Context;

import androidx.annotation.NonNull;

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
    
    private final MedidaDao DAO;
    
    public MedidaRepository(Context context) {
        super(context);
        DAO = db.medidaDao();
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        DAO.clear();
    }

    /**
     * Inserta un objeto en la tabla
     * @param objeto que se quiere insertar
     */
    public void insert( MedidaEntity objeto ){
        DAO.insert( objeto );
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<MedidaEntity> data ){
        DAO.insertAll( data );
    }

    /**
     * Devuelve la colección completa de medidas.
     * @return colección completa de objetos
     */
    public ArrayList<MedidaEntity> getAll(){
        return (ArrayList<MedidaEntity>) DAO.getAll();
    }

    public MedidaEntity getById( String id ){
        return DAO.findById( id );
    }

    @Override
    @NonNull
    public String toString() {
        return super.toString();
    }
}
