/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import DTO.DriverDTO;
import utils.EMF_Creator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.DeliveryFacade;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Chris
 */
@Path("deliver")
public class DeliveryResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final DeliveryFacade FACADE = DeliveryFacade.getDeliveryFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo()
    {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("id/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin", "user"})
    public DriverDTO getDriverByID(@PathParam("id") int id)
    {
        return FACADE.getDriverByID(id);
    }
    
    @GET
    @Path("driver/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin", "user"})
    public DriverDTO getDriverByName(@PathParam("name") String name) {
        return FACADE.getDriverByName(name);
    }
    
}
