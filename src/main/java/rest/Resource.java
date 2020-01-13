/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

/**
 *
 * @author Chris
 */


import DTO.CargoDTO;
import DTO.DeliveryDTO;
import DTO.DriverDTO;
import DTO.TruckDTO;
import utils.EMF_Creator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.DeliveryFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
//import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Chris
 */
@Path("deliver")
public class Resource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final DeliveryFacade FACADE = DeliveryFacade.getDeliveryFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
   // @Context
   // private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo()
    {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("driver/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin", "user"})
       public DriverDTO getDriverByID(@PathParam("id")int id)
       {
           return FACADE.getDriverByID(id);
       }
    
    @GET
    @Path("driver/{name}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin", "user"})
    public DriverDTO getDriverByName(@PathParam("name") String name)
    {
        return FACADE.getDriverByName(name);
    }
    
    @GET
    @Path("whodrives/{name}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin", "user"})
    public DriverDTO getDriverByTruck(@PathParam("name") String name)
    {
        return FACADE.getDriverByTruck(name);
    }
    
    @POST
    @Path("admin/driver/add")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
     public DriverDTO adminAddDriver(DriverDTO driver)
     {
         return FACADE.adminAddDriver(driver);
     }
     
    @PUT
    @Path("admin/driver/edit")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
      public DriverDTO adminEditDriver(DriverDTO driver)
      {
          return FACADE.adminEditDriver(driver);
      }
    
    @DELETE
    @Path("admin/driver/delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
    public DriverDTO adminDeleteDriver(@PathParam("id") int id)
    {
          return FACADE.adminDeleteDriver(id);
    }
    
    @GET
    @Path("truck/{name}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin", "user"})
    public TruckDTO getTruckByName(@PathParam("name")String name)
    {
        return FACADE.getTruckByName(name);
    }
    
    @GET
    @Path("truckDriver/{name}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin", "user"})
    public TruckDTO getTruckByDriver(@PathParam("name")String name)
    {
        return FACADE.getTruckByDriver(name);
    }
    
    @POST
    @Path("admin/truck/add")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
    public TruckDTO adminAddTruck(TruckDTO truck)
    {
        return FACADE.adminAddTruck(truck);
    }
    
    @PUT
    @Path("admin/truck/edit")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
     public TruckDTO adminEditTruck(TruckDTO truck)
     {
         return FACADE.adminEditTruck(truck);
     }
     
    @DELETE
    @Path("admin/truck/delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
    public TruckDTO adminDeleteTruck(@PathParam("id")int id)
     {
         return FACADE.adminDeleteTruck(id);
     }
    
    
    @POST
    @Path("admin/delivery/add")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
    public DeliveryDTO adminAddDelivery(DeliveryDTO delivery)
    {
        return FACADE.adminAddDelivery(delivery);
    }
    
    @PUT
    @Path("admin/delivery/edit")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
     public DeliveryDTO adminEditDelivery(DeliveryDTO delivery)
     {
         return FACADE.adminEditDelivery(delivery);
     }
    
    @DELETE
    @Path("admin/delivery/delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
     public DeliveryDTO adminDeleteDelivery(@PathParam("id")int id)
     {
         return FACADE.adminDeleteDelivery(id);
     }
     
    @POST
    @Path("admin/cargo/add")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
     public CargoDTO adminAddCargo(CargoDTO cargo)
     {
         return FACADE.adminAddCargo(cargo);
     }
     
    @PUT
    @Path("admin/cargo/edit")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
     public CargoDTO adminEditCargo(CargoDTO cargo)
     {
         return FACADE.adminEditCargo(cargo);
     }
     
    @DELETE
    @Path("admin/cargo/delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
     public CargoDTO adminDeleteCargo(@PathParam("id")int id)
     {
         return FACADE.adminDeleteCargo(id);
     }
     
    @GET
    @Path("driver/all")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin", "user"})
     public List<DriverDTO> getAllDrivers()
     {
         return FACADE.getAllDrivers();
     }
     
    @GET
    @Path("truck/all")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin", "user"})
      public List<TruckDTO> getAllTrucks()
      {
          return FACADE.getAllTrucks();
      }
      
    @GET
    @Path("populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populateDatabase() {

        boolean success = FACADE.populate();

        if (success) {
            return "{\"message\":\"Database has been populated\"}";
        } else {
            return "{\"message\":\"Failed to populate database. feelsbadman\"}";
        }
    }
}
