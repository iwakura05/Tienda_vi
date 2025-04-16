package com.tienda.service;

import com.tienda.domain.Item;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    //Se utiliza una variable de session para guardar una lista
    @Autowired
    private HttpSession session;

    //El siguiente metodo crea un item en la variable de session
    //Si la variable no existe, se crea...
    public void save(Item item) {
        //Se recupera la variable de session
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");

        //Se valida si la lista ya etsaba como variable de session
        if (lista == null) {
            lista = new ArrayList<>();
        }

        //Se busca si el idProducto ya esta en la lista...
        boolean existe = false;
        for (Item i : lista) {
            if (Objects.equals(item.getIdProducto(), i.getIdProducto())) {
                existe = true;
                if (i.getCantidad() < i.getExistencias()) {
                    i.setCantidad(i.getCantidad() + 1);
                }
                break;
            }
        }
        if (!existe) {//Si no estaba en la lista, se crea en ella
            item.setCantidad(1);
            lista.add(item);
        }
        session.setAttribute("listaItems", lista);
    }

    //El siguiente metodo crea un item de la variable de session
    //Si no esta, retorna null
    public Item getItem(Item item) {
        //Se recupera la variable de session
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");

        //Se la lista no esta como variable de session, no hay items
        if (lista == null) {
            return null;
        }

        //Se busca si el idProducto ya esta en la lista...
        for (Item i : lista) {
            if (Objects.equals(item.getIdProducto(), i.getIdProducto())) {
                return i;
            }
        }
        return null;
    }

    //El siguiente metodo recupera el total de compra segun la lista
    public double getTotal() {
        //Se recupera la variable de session
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");

        //Se la lista no esta como variable de session, total es 0
        if (lista == null) {
            return 0;
        }

        //Se busca si el idProducto ya esta en la lista...
        double total = 0;
        for (Item i : lista) {
            total += i.getCantidad() * i.getPrecio();
        }
        return total;
    }

    //El siguiente metodo recupera la lista completa desde la variable de session
    public List<Item> getItems() {
        //Se recupera la lista de session
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");
        return lista;
    }

}
