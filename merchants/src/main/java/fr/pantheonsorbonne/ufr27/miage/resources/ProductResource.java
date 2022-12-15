package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.model.Product;
import fr.pantheonsorbonne.ufr27.miage.service.ProductService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("product")
public class ProductResource {

    @Inject
    ProductService productService;

    @Path("/{value}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Product getProduct(@PathParam("value") int productId) {
        if (productId <= 0) {
            return null;
        }
        return productService.getProduct(productId);
    }
}
