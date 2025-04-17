package com.tienda.controller;

import com.tienda.domain.Item;
import com.tienda.domain.Producto;
import com.tienda.service.ItemService;
import com.tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ProductoService productoService;

    @GetMapping("/agregar/{idProducto}")
    public ModelAndView agregar(Model model, Item item) {
        Item item2 = itemService.getItem(item);

        //Si no existe el item en el carrito, se busca el producto
        if (item2 == null) {
            Producto p = productoService.getProducto(item);
            item2 = new Item(p);
        }
        //Finalmente se guarda en la variable de session el producto del carrito
        itemService.save(item2);

        //Se crean elementos para mostrar en el fragmento "verCarrito"
        var lista = itemService.getItems();
        var totalCompra = itemService.getTotal();
        model.addAttribute("listaItems", lista);
        model.addAttribute("totalProductos", lista.size());
        model.addAttribute("totalCompra", totalCompra);

        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = itemService.getItems();
        var totalCompra = itemService.getItems();
        model.addAttribute("listaItems", lista);
        model.addAttribute("totalCompra", totalCompra);
        return "/carrito/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String eliminar(Model model, Item item) {
        itemService.delete(item);
        return "redirect:/carrito/listado";
    }

    @GetMapping("/modificar/{idProducto}")
    public String modificar(Model model, Item item) {
        item = itemService.getItem(item);
        model.addAttribute("item",item);
        return "/carrito/modifica";
    }
    
    @PostMapping("/guardar")
    public String guardar(Item item) {
        itemService.update(item);
        return "redirect:/carrito/listado";
    }
}