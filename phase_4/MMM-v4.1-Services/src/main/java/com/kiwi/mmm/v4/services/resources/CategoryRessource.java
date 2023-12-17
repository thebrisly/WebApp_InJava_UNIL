/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.mmm.v4.services.resources;

import com.kiwi.mmm.v4.services.models.Categories;
import com.kiwi.mmm.v4.services.models.Operations;
import com.kiwi.mmm.v4.services.models.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author laura
 */
@Path("category")
public class CategoryRessource {
    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;

    

    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Categories find(@PathParam("id") Integer id) {
        return em.find(Categories.class, id);
    }



    @GET
    @Path("/findAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Categories> findAll() {
        Query query = em.createNamedQuery("Categories.findAll");
        return query.getResultList();
    }
    

    
    
}
