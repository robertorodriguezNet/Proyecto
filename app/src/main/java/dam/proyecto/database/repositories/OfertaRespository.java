package dam.proyecto.database.repositories;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.OfertaDao;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.OfertaEntity;
import dam.proyecto.database.entity.TagsProductoEntity;

/**
 * Repositorio para OfertaEntity
 * @since 2023/02/16
 * @author Roberto Rodríguez
 * @version 2023.02.25
 */
public class OfertaRespository extends Repositorio {

    private Context context;
    private OfertaDao dao;

    public OfertaRespository(Context context) {
        super(context);
        this.context = context;
        dao = db.ofertaDao();
    }

    public ArrayList<OfertaEntity> getAll(){
        return (ArrayList<OfertaEntity>) dao.getAll();
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        dao.clear();
    }


    /**
     * Devuelve el objeto marca a partir de su id
     * @param idObjeto id de la marca
     * @return MarcaEntity relacionado con el idComercio
     */
    public OfertaEntity findById( String idObjeto ){
        return dao.findById( idObjeto );
    }

    /**
     * Inserta un objeto en la tabla
     * @param objeto
     */
    public void insert( OfertaEntity objeto ){
        dao.insert( objeto );
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll(List<OfertaEntity> data ){
        dao.insertAll( data );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
