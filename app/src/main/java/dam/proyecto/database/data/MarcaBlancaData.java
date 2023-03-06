package dam.proyecto.database.data;

import java.util.Arrays;
import java.util.List;

import dam.proyecto.database.entity.MarcaBlancaEntity;


/**
 * Marca blanca
 * @author Roberto Rodríguez
 * @since 27/02/2023
 * @version 2023.02.27
 */
public class MarcaBlancaData {

    private static final MarcaBlancaEntity[] MARCA_BLANCA_DATA = {
            new MarcaBlancaEntity(1,2,5),
            new MarcaBlancaEntity(2,3,4),
            new MarcaBlancaEntity(3,4,2),
            new MarcaBlancaEntity(4,4,9),
            new MarcaBlancaEntity(5,9,5),
    };

    public static List<MarcaBlancaEntity> getData(){
        return Arrays.asList( MARCA_BLANCA_DATA );
    }
}
