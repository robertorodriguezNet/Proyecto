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

            new CompraEntity("8429687801212","2211050000", (float)1,(float)2.85,(float)2.85, (float)0.238,0),
            new CompraEntity("1000087865444","2207150000", (float)0.618,(float)1.95,(float)3.16, (float)3.155,0),
            new CompraEntity("1000087879877","2207150000", (float)1,(float)1.5,(float)1.5, (float)1.5,0),
            new CompraEntity("1000534544656","2207150000", (float)0.864,(float)3.27,(float)3.78, (float)3.785,0),
            new CompraEntity("1000534544656","2208180819", (float)0.77,(float)2.07,(float)2.69, (float)2.688,0),
            new CompraEntity("8410091180048","2211050000", (float)1,(float)2.49,(float)2.49, (float)9.96,0),
            new CompraEntity("8410283001106","2102092032", (float)1,(float)0.79,(float)0.79, (float)3.292,0),
            new CompraEntity("8410500013479","2207202028", (float)1,(float)4.29,(float)4.29, (float)2.97916666666667,0),
            new CompraEntity("8410505272017","2211050000", (float)1,(float)0.66,(float)0.66, (float)1.65,0),
            new CompraEntity("8411396000734","2207150000", (float)1,(float)1.6,(float)1.6, (float)5.86080586080586,0),
            new CompraEntity("8412598000478","2102092032", (float)1,(float)0.55,(float)0.55, (float)1.66666666666667,0),
            new CompraEntity("8413876251166","2211181501", (float)1,(float)2.15,(float)2.15, (float)5.375,0),
            new CompraEntity("8413876277388","2211181501", (float)1,(float)2.55,(float)2.55, (float)4.25,0),
            new CompraEntity("8414516193476","2211181501", (float)1,(float)2.65,(float)2.65, (float)6.625,0),
            new CompraEntity("8431876231694","2211050000", (float)1,(float)3.1,(float)3.1, (float)15.5,0),
            new CompraEntity("8431876296013","2211181500", (float)1,(float)1.94,(float)1.94, (float)4.85,0),
            new CompraEntity("8437003894275","2208180819", (float)1,(float)3.87,(float)3.87, (float)16.125,0),
            new CompraEntity("8437004621061","2207150000", (float)1,(float)0.79,(float)0.79, (float)3.16,0),
            new CompraEntity("8437004621535","2207202028", (float)2,(float)3.2,(float)1.6, (float)5.33333333333333,0),
            new CompraEntity("8480000049544","2207202028", (float)1,(float)1.15,(float)1.15, (float)4.6,0),
            new CompraEntity("8480000053329","2207202028", (float)1,(float)0.65,(float)0.65, (float)3.0952380952381,0),

            new CompraEntity("8480000221636","2207150000", (float)1,(float)1.7,(float)1.7, (float)8.5,0),
            new CompraEntity("8480000260284","2207202028", (float)1,(float)0.6,(float)0.6, (float)2.85714285714286,0),
            new CompraEntity("8480000260390","2207202028", (float)1,(float)0.6,(float)0.6, (float)2.85714285714286,0),
            new CompraEntity("8480000392169","2207202028", (float)2,(float)2,(float)1, (float)0.833333333333333,0),
            new CompraEntity("8480000505460","2208180819", (float)1,(float)2.65,(float)2.65, (float)8.84,0),
            new CompraEntity("8480000512239","2208180819", (float)1,(float)1.15,(float)1.15, (float)3.84,0),

            new CompraEntity("8410500008871","2208251944", (float)2,(float)6.78,(float)3.39, (float)3.53125,0),
            new CompraEntity("8480000103833","2208251944", (float)1,(float)4.62,(float)4.62, (float)0.77,0),
            new CompraEntity("8480000505460","2208251944", (float)1,(float)2.65,(float)2.65, (float)8.84,0),
            new CompraEntity("8437003018008","2208251944", (float)1,(float)0.73,(float)0.73, (float)0.09125,0),
            new CompraEntity("8480000180025","2208251944", (float)1,(float)4.65,(float)4.65, (float)12.92,0),
            new CompraEntity("8480000592477","2208251944", (float)1,(float)3.15,(float)3.15, (float)14,0),

            new CompraEntity("8480000823021","2207202028", (float)1,(float)1.55,(float)1.55, (float)2.15277777777778,0),
            new CompraEntity("8480000826091","2207202028", (float)1,(float)1.4,(float)1.4, (float)5.6,0),
            new CompraEntity("8480000827876","2208251944", (float)2,(float)1.7,(float)0.85, (float)8.5,0),

            new CompraEntity("8480000837837","2207150000", (float)1,(float)1.1,(float)1.1, (float)2.22222222222222,0),
            new CompraEntity("8480024764904","2211050000", (float)1,(float)0.64,(float)0.64, (float)8.53333333333333,0),
            new CompraEntity("8480024820594","2211050000", (float)1,(float)1.69,(float)1.69, (float)5.63333333333333,0),
            new CompraEntity("8717163889169","2211050000", (float)1,(float)1.53,(float)1.53, (float)3.06,0),

            new CompraEntity("8480024820594","2302101928", (float)1,(float)1.69,(float)1.69, (float)5.63333333333333,0),
            new CompraEntity("8717163889169","2302101928", (float)1,(float)1.53,(float)1.53, (float)3.06,0),
            new CompraEntity("8480000213075","2302101928", (float)2,(float)2.7,(float)1.35, (float)1.8,0),
            new CompraEntity("8480000211934","2302101928", (float)1,(float)1.35,(float)1.35, (float)1.8,0),
            new CompraEntity("8480000827876","2302101928", (float)2,(float)1.7,(float)0.85, (float)8.5,0),
            new CompraEntity("8480000505460","2302101928", (float)1,(float)2.65,(float)2.65, (float)8.83333333333333,0),
            new CompraEntity("8720182382696","2302101928", (float)1,(float)2.65,(float)2.65, (float)5.88888888888889,0),
            new CompraEntity("1000000002345","2302101928", (float)0.25,(float)0.6,(float)2.4, (float)2.39,0),
            new CompraEntity("1000087865444","2302101928", (float)0.95,(float)1.89,(float)1.98947368421053, (float)1.99,0),
            new CompraEntity("1000000002346","2302101928", (float)3.556,(float)6.47,(float)1.81946006749156, (float)1.82,0),
            new CompraEntity("1000000002347","2302101928", (float)0.282,(float)0.65,(float)2.30496453900709, (float)2.29,0),
            new CompraEntity("1000000002348","2302101928", (float)1.332,(float)2.29,(float)1.71921921921922, (float)1.72,0),
            new CompraEntity("8411384002009","2302101928", (float)1,(float)2.4,(float)2.4, (float)0.2,0),
            new CompraEntity("7613036039499","2302101928", (float)4,(float)19.96,(float)4.99, (float)9.98,0),

            new CompraEntity("7613036039499","2302141034", (float)4,(float)19.96,(float)4.99, (float)9.98,0),
            new CompraEntity("8410010260042","2302141034", (float)4,(float)34.36,(float)8.59, (float)8.59,0),
            new CompraEntity("8480024764904","2301171451", (float)4,(float)2.56,(float)0.64, (float)8.53333333333333,0),
            new CompraEntity("8717163889169","2301171451", (float)4,(float)9.16,(float)2.29, (float)4.58,0),
            new CompraEntity("8437014689501","2211221644", (float)1,(float)0.9,(float)0.9, (float)1.5,0),
            new CompraEntity("5410076230068","2211221644", (float)1,(float)4.45,(float)4.45, (float)8.9,0),
            new CompraEntity("3574661648026","2211221644", (float)1,(float)6.95,(float)6.95, (float)6.95,0),

            new CompraEntity("8437004621061","2302251145", (float)3,(float)2.95,(float)0.70, (float)2.88,0),
            new CompraEntity("8480000049544","2302251145", (float)1,(float)1.30,(float)1.30, (float)5.20,0),
            new CompraEntity("8480000332493","2302251145", (float)1,(float)1.30,(float)1.05, (float)7.00,0),
            new CompraEntity("8480000053329","2302251145", (float)1,(float)0.90,(float)0.90, (float)4.286,0),
            new CompraEntity("8480000260390","2302251145", (float)1,(float)0.87,(float)0.87, (float)2.175,0),
            new CompraEntity("1000534544660","2302251145", (float)1.96,(float)4.50,(float)2.30, (float)2.30,0),
            new CompraEntity("1000000002347","2302251145", (float)0.394,(float)0.90,(float)2.29, (float)2.29,0),
            new CompraEntity("1000000002345","2302251145", (float)0.376,(float)0.90,(float)2.39, (float)2.39,0),
            new CompraEntity("8480000505460","2302251145", (float)1,(float)2.98,(float)2.98, (float)9.93,0),
            new CompraEntity("8437003894275","2302251145", (float)1,(float)3.87,(float)3.87, (float)16.126,0),
            new CompraEntity("1000534544661","2302251145", (float)0.84,(float)2.48,(float)2.95, (float)2.95,0),
            new CompraEntity("8480000823021","2302251145", (float)1,(float)1.55,(float)1.55, (float)2.15,0),
            new CompraEntity("1000534544663","2302251145", (float)0.526,(float)7.86,(float)14.95, (float)14.95,0),
            new CompraEntity("1000534544662","2302251145", (float)0.5,(float)3.40,(float)6.80, (float)6.80,0),
            new CompraEntity("1000534544664","2302251145", (float)5,(float)4.95,(float)0.99, (float)0.99,0),
            new CompraEntity("8480000837837","2302251145", (float)1,(float)1.11,(float)1.11, (float)2.24,0),
            new CompraEntity("1000534544665","2302251145", (float)1,(float)0.95,(float)0.95, (float)0.95,0),

            new CompraEntity("8480024292193","2302200921", (float)1,(float)1.29,(float)1.29, (float)0.33,0),
            new CompraEntity("8480024750600","2302200921", (float)1,(float)1.99,(float)1.99, (float)50,0),
            new CompraEntity("8480024819055","2302200921", (float)3,(float)2.25,(float)0.75, (float)3.00,0),

            new CompraEntity("1000087865444","2303031858", (float)0.778,(float)1.05,(float)1.35, (float)1.35,0),
            new CompraEntity("1000000002348","2303031858", (float)0.888,(float)1.50,(float)1.69, (float)1.69,0),
            new CompraEntity("24002042","2303031858", (float)1,(float)1.69,(float)1.69, (float)8.45,0),
            new CompraEntity("24031486","2303031858", (float)2,(float)0.65,(float)1.30, (float)2.60,0),
            new CompraEntity("1000534544661","2303031858", (float)1,(float)2.21,(float)2.21, (float)0.19,0),

            new CompraEntity("8437003018008","2303111914",(float) 1.0,(float) 0.0,(float) 0.73,(float) 0.0,0),
            new CompraEntity("8480000211934","2303111914",(float)1.0,(float)1.45,(float)1.45,(float)1.933,0),
            new CompraEntity("1000534544661","2303111914",(float)1.0,(float)0.0,(float)2.95,(float)0.0,0),
            new CompraEntity("8437004621061","2303111914",(float)2.0,(float)1.44,(float)0.72,(float)2.88,0),
            new CompraEntity( "8437003894275","2303111914",(float)1.0,(float)0.0,(float)3.87,(float)0.0,0),
            new CompraEntity("8480000823021","2303111914",(float)1.0,(float)0.0,(float)1.55,(float)0.0,0),
            new CompraEntity("1000087865444","2303111914",(float)1.0,(float)0.0,(float)1.9894737,(float)0.0,0),
            new CompraEntity("8480000505460","2303111914",(float)1.0,(float)0.0,(float)2.98,(float)0.0,0),
            new CompraEntity("8480000049544","2303111914",(float)1.0,(float)0.0,(float)1.3,(float)0.0,0),
            new CompraEntity("1000534544671","2303111914",(float)1.0,(float)1.99,(float)1.99,(float)1.99,0),
            new CompraEntity("8480000038524","2303111914",(float)1.0,(float)1.75,(float)1.75,(float)8.75,0),
            new CompraEntity("8480000213075","2303111914",(float)1.0,(float)1.45,(float)1.45,(float)1.933,0),
            new CompraEntity("8006540948811","2303111914",(float)1.0,(float)3.8,(float)3.8,(float)4.368,0),
            new CompraEntity("8480000430243","2303111914",(float)1.0,(float)1.15,(float)1.15,(float)0.575,0),
            new CompraEntity("3600542379342","2303111914",(float)1.0,(float)3.5,(float)3.5,(float)3.5,0),
            new CompraEntity("8480000829610","2303111914",(float)1.0,(float)5.5,(float)5.5,(float)16.923,0),
            new CompraEntity("1000534544663","2303111914",(float)0.521,(float)4.059,(float)7.79,(float)7.79,0),
            new CompraEntity( "8480000622129","2303111914",(float)2.0,(float)7.7,(float)3.85,(float)9.625,0),




    };

    public static List<CompraEntity> getData() {
        return Arrays.asList(TAG_DATA);
    }
}
