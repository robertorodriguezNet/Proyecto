package dam.proyecto.database.data;

import java.util.Arrays;
import java.util.List;

import dam.proyecto.database.entity.NombreCompraEntity;

/**
 * @since 2023/01/23
 * @author Roberto Rodríguez
 * @version 2023.02.15
 */
public class NombreCompraData {

    private static final NombreCompraEntity[] NOMBRE_COMPRA_DATA = {
            new NombreCompraEntity("2102092032","semanal",3),
            new NombreCompraEntity("2207150000","mediados de mes",5),
            new NombreCompraEntity("2207202028","urgente",5),
            new NombreCompraEntity("2208180819","2208180819",5),
            new NombreCompraEntity("2208251944","tarde",5),
            new NombreCompraEntity("2211050000","para el desayuno",4),
            new NombreCompraEntity("2211181500","semanal",2),
            new NombreCompraEntity("2211181501","última hora",2),
            new NombreCompraEntity("2302101928","semana 2",5),
            new NombreCompraEntity("2302141034","ofertas carrefour",2),
            new NombreCompraEntity("2301171451","2301171451",4),
            new NombreCompraEntity("2211221644","2211221644",8),
            new NombreCompraEntity("2302251145","2302251145",5),
            new NombreCompraEntity("2302200921","2302200921",4),
            new NombreCompraEntity("2303231858","2303231858",10),



    };

    public static List<NombreCompraEntity> getData(){
        return Arrays.asList( NOMBRE_COMPRA_DATA );
    }
}
