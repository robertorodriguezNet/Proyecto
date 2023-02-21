package dam.proyecto.database.data;

import dam.proyecto.database.entity.CompraEntity;

import java.util.Arrays;
import java.util.List;

/**
 * @since 2023/01/23
 * @author Roberto Rodríguez
 * @version 2023.02.15
 */
public class CompraEntityData {

    private static final CompraEntity[] TAG_DATA = {

            new CompraEntity("8429687801212","2211050000", (float)1,(float)2.85,(float)2.85, (float)0.238),
            new CompraEntity("1000087865444","2207150000", (float)0.618,(float)1.95,(float)3.16, (float)3.155),
            new CompraEntity("1000087879877","2207150000", (float)1,(float)1.5,(float)1.5, (float)1.5),
            new CompraEntity("1000534544656","2207150000", (float)0.864,(float)3.27,(float)3.78, (float)3.785),
            new CompraEntity("1000534544656","2208180819", (float)0.77,(float)2.07,(float)2.69, (float)2.688),
            new CompraEntity("8410091180048","2211050000", (float)1,(float)2.49,(float)2.49, (float)9.96),
            new CompraEntity("8410283001106","2102092032", (float)1,(float)0.79,(float)0.79, (float)3.292),
            new CompraEntity("8410500008871","2208221944", (float)2,(float)6.78,(float)3.39, (float)3.53125),
            new CompraEntity("8410500013479","2207202028", (float)1,(float)4.29,(float)4.29, (float)2.97916666666667),
            new CompraEntity("8410505272017","2211050000", (float)1,(float)0.66,(float)0.66, (float)1.65),
            new CompraEntity("8411396000734","2207150000", (float)1,(float)1.6,(float)1.6, (float)5.86080586080586),
            new CompraEntity("8412598000478","2102092032", (float)1,(float)0.55,(float)0.55, (float)1.66666666666667),
            new CompraEntity("8413876251166","2211181501", (float)1,(float)2.15,(float)2.15, (float)5.375),
            new CompraEntity("8413876277388","2211181501", (float)1,(float)2.55,(float)2.55, (float)4.25),
            new CompraEntity("8414516193476","2211181501", (float)1,(float)2.65,(float)2.65, (float)6.625),
            new CompraEntity("8431876231694","2211050000", (float)1,(float)3.1,(float)3.1, (float)15.5),
            new CompraEntity("8431876296013","2211181500", (float)1,(float)1.94,(float)1.94, (float)4.85),
            new CompraEntity("8437003018008","2208251944", (float)1,(float)0.73,(float)0.73, (float)0.09125),
            new CompraEntity("8437003894275","2208180819", (float)1,(float)3.87,(float)3.87, (float)16.125),
            new CompraEntity("8437004621061","2207150000", (float)1,(float)0.79,(float)0.79, (float)3.16),
            new CompraEntity("8437004621535","2207202028", (float)2,(float)3.2,(float)1.6, (float)5.33333333333333),
            new CompraEntity("8480000049544","2207202028", (float)1,(float)1.15,(float)1.15, (float)4.6),
            new CompraEntity("8480000053329","2207202028", (float)1,(float)0.65,(float)0.65, (float)3.0952380952381),
            new CompraEntity("8480000103833","2208221944", (float)1,(float)4.62,(float)4.62, (float)0.77),
            new CompraEntity("8480000180025","2208251944", (float)1,(float)4.65,(float)4.65, (float)12.9166666666667),
            new CompraEntity("8480000221636","2207150000", (float)1,(float)1.7,(float)1.7, (float)8.5),
            new CompraEntity("8480000260284","2207202028", (float)1,(float)0.6,(float)0.6, (float)2.85714285714286),
            new CompraEntity("8480000260390","2207202028", (float)1,(float)0.6,(float)0.6, (float)2.85714285714286),
            new CompraEntity("8480000392169","2207202028", (float)2,(float)2,(float)1, (float)0.833333333333333),
            new CompraEntity("8480000505460","2208180819", (float)1,(float)2.65,(float)2.65, (float)8.83333333333333),
            new CompraEntity("8480000505460","2208251944", (float)1,(float)2.65,(float)2.65, (float)8.83333333333333),
            new CompraEntity("8480000512239","2208180819", (float)1,(float)1.15,(float)1.15, (float)3.83333333333333),
            new CompraEntity("8480000592477","2208251944", (float)1,(float)3.15,(float)3.15, (float)14),
            new CompraEntity("8480000823021","2207202028", (float)1,(float)1.55,(float)1.55, (float)2.15277777777778),
            new CompraEntity("8480000826091","2207202028", (float)1,(float)1.4,(float)1.4, (float)5.6),
            new CompraEntity("8480000827876","2208251944", (float)2,(float)1.7,(float)0.85, (float)8.5),

            new CompraEntity("8480000837837","2207150000", (float)1,(float)1.1,(float)1.1, (float)2.22222222222222),
            new CompraEntity("8480024764904","2211050000", (float)1,(float)0.64,(float)0.64, (float)8.53333333333333),
            new CompraEntity("8480024820594","2211050000", (float)1,(float)1.69,(float)1.69, (float)5.63333333333333),
            new CompraEntity("8717163889169","2211050000", (float)1,(float)1.53,(float)1.53, (float)3.06),

            new CompraEntity("8480024820594","2302101928", (float)1,(float)1.69,(float)1.69, (float)5.63333333333333),
            new CompraEntity("8717163889169","2302101928", (float)1,(float)1.53,(float)1.53, (float)3.06),
            new CompraEntity("8480000213075","2302101928", (float)2,(float)2.7,(float)1.35, (float)1.8),
            new CompraEntity("8480000211934","2302101928", (float)1,(float)1.35,(float)1.35, (float)1.8),
            new CompraEntity("8480000827876","2302101928", (float)2,(float)1.7,(float)0.85, (float)8.5),
            new CompraEntity("8480000505460","2302101928", (float)1,(float)2.65,(float)2.65, (float)8.83333333333333),
            new CompraEntity("8720182382696","2302101928", (float)1,(float)2.65,(float)2.65, (float)5.88888888888889),
            new CompraEntity("1000000002345","2302101928", (float)0.25,(float)0.6,(float)2.4, (float)2.39),
            new CompraEntity("1000087865444","2302101928", (float)0.95,(float)1.89,(float)1.98947368421053, (float)1.99),
            new CompraEntity("1000000002346","2302101928", (float)3.556,(float)6.47,(float)1.81946006749156, (float)1.82),
            new CompraEntity("1000000002347","2302101928", (float)0.282,(float)0.65,(float)2.30496453900709, (float)2.29),
            new CompraEntity("1000000002348","2302101928", (float)1.332,(float)2.29,(float)1.71921921921922, (float)1.72),
            new CompraEntity("8411384002009","2302101928", (float)1,(float)2.4,(float)2.4, (float)0.2),
            new CompraEntity("7613036039499","2302101928", (float)4,(float)19.96,(float)4.99, (float)9.98),

            new CompraEntity("7613036039499","2302141034", (float)4,(float)19.96,(float)4.99, (float)9.98),
            new CompraEntity("8410010260042","2302141034", (float)4,(float)34.36,(float)8.59, (float)8.59),
            new CompraEntity("8480024764904","2301171451", (float)4,(float)2.56,(float)0.64, (float)8.53333333333333),
            new CompraEntity("8717163889169","2301171451", (float)4,(float)9.16,(float)2.29, (float)4.58),
            new CompraEntity("8437014689501","2211221644", (float)1,(float)0.9,(float)0.9, (float)1.5),
            new CompraEntity("5410076230068","2211221644", (float)1,(float)4.45,(float)4.45, (float)8.9),
            new CompraEntity("3574661648026","2211221644", (float)1,(float)6.95,(float)6.95, (float)6.95),


            
    };

    public static List<CompraEntity> getData() {
        return Arrays.asList(TAG_DATA);
    }
}
