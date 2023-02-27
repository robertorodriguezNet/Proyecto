package dam.proyecto.database.data;

import dam.proyecto.database.entity.ProductoEntity;

import java.util.Arrays;
import java.util.List;

/**
 * @since 2023/01/23
 * @author Roberto Rodríguez
 * @version 2023.02.15
 */
public class ProductoData {

    private static final ProductoEntity[] PRODUCTO_DATA = {
            new ProductoEntity("8437003018008","Agua Mineral Natural Aguadoy 8L",2,1,"l",(float)8),
            new ProductoEntity("8480024764904","Picatostes alteza ajo 75gr",3,1,"k",(float)0.075),
            new ProductoEntity("8480024820594","Mix de verduras microondas 300g",3,1,"k",(float)0.3),
            new ProductoEntity("8413876251166","Ensalada cesar",4,1,"k",(float)0.4),
            new ProductoEntity("8413876277388","Tortilla patatas carrefour",4,1,"k",(float)0.6),
            new ProductoEntity("8431876231694","Salmón ahumado 200gr",4,1,"k",(float)0.2),
            new ProductoEntity("8431876296013","Alcohol limpieza carrefour",4,1,"l",(float)0.4),
            new ProductoEntity("8410500008871","Bifidus activia desnatado natural, pack 8",5,8,"k",(float)0.96),
            new ProductoEntity("8410500013479","Bifidus activia desnatado natural,  pack 12",5,12,"k",(float)1.44),
            new ProductoEntity("8412598000478","Cerveza lata 0,0",6,1,"l",(float)0.33),
            new ProductoEntity("8414516193476","Ensalada gourmet",7,1,"k",(float)0.4),
            new ProductoEntity("1000087865444","Platano",8,0,"k",(float)0),
            new ProductoEntity("1000087879877","Tomate",8,0,"k",(float)0),
            new ProductoEntity("1000534544656","Melocotón amarillo",8,0,"k",(float)0),
            new ProductoEntity("8437004621061","Ensalada 4 estaciones ",9,1,"k",(float)0.25),
            new ProductoEntity("8437004621535","VERDURA 3 VEGETALES microondas",9,1,"k",(float)0.3),
            new ProductoEntity("8480000049544","Vinagre balsámico de módena",9,1,"l",(float)0.25),
            new ProductoEntity("8480000053329","Lenteja Pardina Hacendado 295 g",9,1,"k",(float)0.21),
            new ProductoEntity("8480000103833","Leche desnatada",9,1,"l",(float)6),
            new ProductoEntity("8480000180025","Atún Claro Aceite Oliva Hacendado Pack-6 480 GR",9,6,"k",(float)0.36),
            new ProductoEntity("8480000221636","Cafe soluble natural classic, hacendado, tarro 200 g",9,1,"k",(float)0.2),
            new ProductoEntity("8480000260284","Alubia Blanca Hacendado 295 g",9,1,"k",(float)0.21),
            new ProductoEntity("8480000260390","GARBANZO COCIDO, HACENDADO, TARRO 570 g ESCURRIDO 400 g",9,1,"k",(float)0.21),
            new ProductoEntity("8480000392169","Zumo de melocotón y uva 6x200ml",9,6,"l",(float)1.2),
            new ProductoEntity("8480000505460","Queso havarti light",9,1,"k",(float)0.3),
            new ProductoEntity("8480000512239","Queso untar light",9,1,"k",(float)0.3),
            new ProductoEntity("8480000592477","PECHUGA PAVO FINAS LONCHAS CONTENIDO REDUCIDO de SAL",9,1,"k",(float)0.225),
            new ProductoEntity("8480000823021","Pan molde de centeno, hacendado, paquete 14 rebanadas - 720 g",9,14,"k",(float)0.72),
            new ProductoEntity("8480000826091","Bollo chocolate",9,6,"k",(float)0.25),
            new ProductoEntity("8480000827876","Picatoste Frito en aceite de girasol sabor ajo",9,1,"k",(float)0.1),
            new ProductoEntity("8480000837837","Panecillo horno, hacendado, paquete 11 u - 495 g",9,11,"k",(float)0.495),
            new ProductoEntity("8411396000734","Fflautas pollo y queso, pinchos jovi, paquete 2 u - 273 g",10,2,"k",(float)0.273),
            new ProductoEntity("8717163889169","Crema campesina 500ml",11,1,"l",(float)0.5),
            new ProductoEntity("8410283001106","Gaseosa la casera 0,5l",2,1,"l",(float)0.5),
            new ProductoEntity("8410505272017","Garbanzo cocido Luengo 400g",13,1,"l",(float)0.4),
            new ProductoEntity("8429687801212","Huevos lugareño 12 XL",14,12,"u",(float)12),
            new ProductoEntity("8410091180048","Café Marcilla molido mezcla",15,1,"k",(float)0.25),
            new ProductoEntity("8437003894275","Paleta Curada Lonchas Paletas Marpa 240g Bipack",16,1,"k",(float)0.24),
            new ProductoEntity("8480000213075","Bífidus natural",9,6,"k",(float)0.75),
            new ProductoEntity("8480000211934","Bífidus natural 0,0",9,6,"k",(float)0.75),
            new ProductoEntity("8720182382696","Mayonesa",17,1,"l",(float)0.45),
            new ProductoEntity("1000000002345","Pimiento rojo",8,0,"k",(float)0),
            new ProductoEntity("1000000002346","Tomate pera",8,0,"k",(float)0),
            new ProductoEntity("1000000002347","Pimiento verde",8,0,"k",(float)0),
            new ProductoEntity("1000000002348","Patata",8,0,"k",(float)0),
            new ProductoEntity("8411384002009","Huevos suelo",18,1,"u",(float)12),
            new ProductoEntity("7613036039499","Café mezcla",19,1,"k",(float)0.5),
            new ProductoEntity("8410010260042","Aceite oliva virgen extra",20,1,"l",(float)1),
            new ProductoEntity("8437014689501","Jabón de manos con dosificador",21,2,"l",(float)0.6),
            new ProductoEntity("5410076230068","Champú HS classic",22,1,"l",(float)0.5),
            new ProductoEntity("3574661648026","Colutorio",23,1,"l",(float)1),

            new ProductoEntity("8480000332493","Aceituna negra sin hueso",9,1,"k",(float)0.15),
            new ProductoEntity("1000534544661","Cebolla",8,0,"k",(float)0),
            new ProductoEntity("1000534544663","bacalao desalado",8,0,"k",(float)0),
            new ProductoEntity("1000534544664","Naranja",8,0,"k",(float)0),
            new ProductoEntity("1000534544665","Zanahoria",8,0,"k",(float)0),
            new ProductoEntity("1000534544662","Lomo adobado",8,0,"k",(float)0),
            new ProductoEntity("1000534544660","Tomate ensalada",8,0,"k",(float)0),
            new ProductoEntity("8480000161246","Pimiento asado tiras",9,3,"k",(float)0.180),

            new ProductoEntity("8480024292193","Papel higiénico",5,4,"u",(float)4),
            new ProductoEntity("8480024750600","Perejil tarro",3,1,"k",(float)0.4),
            new ProductoEntity("8480024819055","Ensalada 4 estaciones",3,1,"k",(float)0.25),



    };

    /**
     * Devuelve la colección como un ArrayList
     * @return el listado de productos
     */
    public static List<ProductoEntity> getData(){
        return Arrays.asList( PRODUCTO_DATA );
    }
}


