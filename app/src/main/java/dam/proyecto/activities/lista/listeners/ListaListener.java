package dam.proyecto.activities.lista.listeners;

import android.view.View;

import dam.proyecto.activities.lista.adapters.ProductoCompraListAdapter;
import dam.proyecto.database.entity.CompraEntity;

public interface ListaListener {
    void onProductoCompradoClick(CompraEntity compra);
    void onProductoCompradoLongClick(CompraEntity compra);
}
