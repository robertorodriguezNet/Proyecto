package dam.proyecto.database.data;

import java.util.Arrays;
import java.util.List;

import dam.proyecto.database.entity.OfertaEntity;
import dam.proyecto.database.entity.OfertaEntity;


/**
 * @since 2023/01/23
 * @author Roberto Rodríguez
 * @version 2023.02.15
 */
public class OfertaData {

    private static final OfertaEntity[] TAG_DATA = {
            new OfertaEntity("2x1","2 X 1"),
            new OfertaEntity("3x2","3 X 2"),
            new OfertaEntity("2-50","2ª 50%"),
            new OfertaEntity("2-70","2ª 70%"),
    };

    public static List<OfertaEntity> getData(){
        return Arrays.asList( TAG_DATA );
    }
}
