/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTO.CargoDTO;
import DTO.DeliveryDTO;
import DTO.DriverDTO;
import DTO.TruckDTO;
import entities.Driver;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Chris
 */
public class DeliveryFacade implements DeliveryFacadeInterface {

    private static DeliveryFacadeInterface facade;
    private static EntityManagerFactory emf;

    private DeliveryFacade()
    {

    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static DeliveryFacadeInterface getDeliveryFacade(EntityManagerFactory _emf)
    {
        if (facade == null)
        {
            emf = _emf;
            facade = new DeliveryFacade();
        }
        return facade;
    }

    private EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    @Override
    public DriverDTO getDriverByID(int id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return new DriverDTO(em.createNamedQuery("Driver.getDriverByID", Driver.class).setParameter("id", id).getSingleResult());

        }
        catch (IllegalArgumentException e)
        {
            throw new WebApplicationException("No driver with that ID exists", 404);
        }
        catch (Exception ex)
        {
            throw new WebApplicationException(ex.getMessage(), 400);
        }
    }

    @Override
    public DriverDTO getDriverByName(String name)
    {
        EntityManager em = getEntityManager();
        try
        {
            return new DriverDTO(em.createNamedQuery("Driver.getDriverByName", Driver.class).setParameter("name", name).getSingleResult());

        }
        catch (IllegalArgumentException e)
        {
            throw new WebApplicationException("No driver with that name exists", 404);
        }
        catch (Exception ex)
        {
            throw new WebApplicationException(ex.getMessage(), 400);
        }
    }

    @Override
    public DriverDTO getDriverByTruck(String name)
    {
        EntityManager em = getEntityManager();
        try
        {
            return new DriverDTO(em.createNamedQuery("Driver.getDriverByTruck", Driver.class).setParameter("name", name).getSingleResult());

        }
        catch (IllegalArgumentException e)
        {
            throw new WebApplicationException("No driver with that truck exists", 404);
        }
        catch (Exception ex)
        {
            throw new WebApplicationException(ex.getMessage(), 400);
        }
    }

    @Override
    public DriverDTO adminAddDriver(DriverDTO driver)
    {
        if (driver == null || driver.getName() == null || driver.getTruckList() == null)
        {
            throw new WebApplicationException("Invalid person input", 400);
        }
        Driver result = new Driver(driver);
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
           
            em.persist(result);
            em.getTransaction().commit();
            return new DriverDTO(result);
        }
        catch (EntityExistsException e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Driver already exists", 400);
        }
        catch (Exception e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Something went wrong while adding driver", 500);
        } finally
        {
            em.close();
        }
    }

    @Override
    public DriverDTO adminEditDriver(DriverDTO driver)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DriverDTO adminDeleteDriver(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TruckDTO getTruckByName(String name)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TruckDTO getTruckByDriver(String name)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TruckDTO adminAddTruck(TruckDTO truck)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TruckDTO adminEditTruck(TruckDTO truck)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TruckDTO adminDeleteTruck(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DeliveryDTO adminAddDelivery(DeliveryDTO delivery)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DeliveryDTO adminEditDelivery(DeliveryDTO delivery)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DeliveryDTO adminDeleteDelivery(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CargoDTO adminAddCargo(CargoDTO cargo)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CargoDTO adminEditCargo(CargoDTO cargo)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CargoDTO adminDeleteCargo(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DriverDTO> getAllDrivers()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TruckDTO> getAllTrucks()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
