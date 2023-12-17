/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.mmm.v4.services.resources;

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
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author laura
 */
@Path("operation")
public class OperationRessource {
    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;

    @POST
    @Path("/create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Transactional
    public void create(Operations entity) {
        em.persist(entity);
    }

    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Users find(@PathParam("id") Integer id) {
        return em.find(Users.class, id);
    }

    @GET
    @Path("/findByUserId/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Operations> findByUserId(@PathParam("userId") Integer userId) {
        Query query = em.createNamedQuery("Operations.findByUserId", Operations.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }


    @GET
    @Path("/findAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Operations> findAll() {
        Query query = em.createNamedQuery("Operations.findAll");
        return query.getResultList();
    }
    
    
    @DELETE
    @Path("/remove/{id}")
    @Transactional
    public void delete(@PathParam("id") Integer id) {
        em.remove(em.merge(em.find(Operations.class, id)));
    }

    
    
}
