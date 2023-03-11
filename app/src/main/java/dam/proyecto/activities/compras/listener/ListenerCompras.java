package dam.proyecto.activities.compras.listener;

import dam.proyecto.database.entity.NombreCompraEntity;

public interface ListenerCompras {

    /**
     * Elimina una compra de la lista de compras
     *
     * @param compra la compra que se quiere borrar
     */
    public void cli_img_deleteOnClik(NombreCompraEntity compra, int posicion);

    /**
     * Duplica una compra de la lista de compras
     *
     * @param compra la compra que se quiere borrar
     */
    public void cli_img_copyOnClik(NombreCompraEntity compra );

    /**
     * Edita una lista
     */
    public void cli_lly_datosCompraOnClick( NombreCompraEntity compra);

}
