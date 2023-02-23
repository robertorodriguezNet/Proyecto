package dam.proyecto.activities.almacen;

import android.view.View;

import dam.proyecto.database.entity.ProductoEntity;

public interface AlmacenListener {
    void editarProducto( ProductoEntity producto );
    void addProductoALaLista( ProductoEntity producto );
    void addNuevoProducto();
}
