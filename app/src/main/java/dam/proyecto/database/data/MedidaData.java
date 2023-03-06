package dam.proyecto.database.data;

import dam.proyecto.database.entity.MedidaEntity;

import java.util.Arrays;
import java.util.List;


/**
 * @since 2023/01/23
 * @author Roberto Rodríguez
 * @version 2023.02.15
 */
public class MedidaData {

    private static final MedidaEntity[] TAG_DATA = {
            new MedidaEntity("a", ""),
            new MedidaEntity("u", "Unidades"),
            new MedidaEntity("k", "Kilos"),
            new MedidaEntity("l", "Litros"),
            new MedidaEntity("g", "granel"),
    };

    public static List<MedidaEntity> getData(){
        return Arrays.asList( TAG_DATA );
    }
}
