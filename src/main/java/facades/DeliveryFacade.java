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
import entities.Cargo;
import entities.Delivery;
import entities.Driver;
import entities.Truck;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
            throw new WebApplicationException("Invalid driver input", 400);
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
        if (driver == null || driver.getName() == null || driver.getId() <= 0)
        {
            throw new WebApplicationException("Invalid driver input", 400);
        }
        EntityManager em = getEntityManager();
        Driver result = new Driver(driver);
        try
        {
            em.getTransaction().begin();
            result = em.merge(result);
            em.getTransaction().commit();
            return new DriverDTO(result);
        }
        catch (IllegalArgumentException e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("driver does not comply with database standards", 400);
        }
        catch (Exception e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Something went wrong while editing driver", 500);
        } finally
        {
            em.close();
        }

    }

    @Override
    public DriverDTO adminDeleteDriver(int id)
    {
        Driver result;
        EntityManager em = getEntityManager();
        try
        {
            result = em.find(Driver.class, id);
            em.getTransaction().begin();
            em.remove(result);
            em.getTransaction().commit();
            return new DriverDTO(result);
        }
        catch (IllegalArgumentException e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Driver does not comply with database standards", 400);
        }
        catch (Exception e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Something went wrong while deleting driver", 500);
        } finally
        {
            em.close();
        }
    }

    @Override
    public TruckDTO getTruckByName(String name)
    {
        EntityManager em = getEntityManager();
        try
        {
            return new TruckDTO(em.createNamedQuery("Truck.getTruckByName", Truck.class).setParameter("name", name).getSingleResult());

        }
        catch (IllegalArgumentException e)
        {
            throw new WebApplicationException("No truck with that name exists", 404);
        }
        catch (Exception ex)
        {
            throw new WebApplicationException(ex.getMessage(), 400);
        }
    }

    @Override
    public TruckDTO getTruckByDriver(String name)
    {
        EntityManager em = getEntityManager();
        try
        {
            return new TruckDTO(em.createNamedQuery("Truck.getTruckByDriver", Truck.class).setParameter("name", name).getSingleResult());

        }
        catch (IllegalArgumentException e)
        {
            throw new WebApplicationException("No truck with that driver exists", 404);
        }
        catch (Exception ex)
        {
            throw new WebApplicationException(ex.getMessage(), 400);
        }
    }

    @Override
    public TruckDTO adminAddTruck(TruckDTO truck)
    {
        if (truck == null || truck.getName() == null || truck.getDriverList() == null)
        {
            throw new WebApplicationException("Invalid truck input", 400);
        }
        Truck result = new Truck(truck);
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();

            em.persist(result);
            em.getTransaction().commit();
            return new TruckDTO(result);
        }
        catch (EntityExistsException e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Truck already exists", 400);
        }
        catch (Exception e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Something went wrong while adding truck", 500);
        } finally
        {
            em.close();
        }
    }

    @Override
    public TruckDTO adminEditTruck(TruckDTO truck)
    {
        if (truck == null || truck.getName() == null || truck.getId() <= 0)
        {
            throw new WebApplicationException("Invalid truck input", 400);
        }
        EntityManager em = getEntityManager();
        Truck result = new Truck(truck);
        try
        {
            em.getTransaction().begin();
            result = em.merge(result);
            em.getTransaction().commit();
            return new TruckDTO(result);
        }
        catch (IllegalArgumentException e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("truck does not comply with database standards", 400);
        }
        catch (Exception e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Something went wrong while editing truck", 500);
        } finally
        {
            em.close();
        }
    }

    @Override
    public TruckDTO adminDeleteTruck(int id)
    {
        Truck result;
        EntityManager em = getEntityManager();
        try
        {
            result = em.find(Truck.class, id);
            em.getTransaction().begin();
            em.remove(result);
            em.getTransaction().commit();
            return new TruckDTO(result);
        }
        catch (IllegalArgumentException e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Truck does not comply with database standards", 400);
        }
        catch (Exception e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Something went wrong while deleting Truck", 500);
        } finally
        {
            em.close();
        }
    }

    @Override
    public DeliveryDTO adminAddDelivery(DeliveryDTO delivery)
    {
        if (delivery == null || delivery.getTruck() == null || delivery.getCargoList() == null)
        {
            throw new WebApplicationException("Invalid delivery input", 400);
        }
        Delivery result = new Delivery(delivery);
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();

            em.persist(result);
            em.getTransaction().commit();
            return new DeliveryDTO(result);
        }
        catch (EntityExistsException e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Delivery already exists", 400);
        }
        catch (Exception e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Something went wrong while adding delivery", 500);
        } finally
        {
            em.close();
        }
    }

    @Override
    public DeliveryDTO adminEditDelivery(DeliveryDTO delivery)
    {
        if (delivery == null || delivery.getTruck() == null || delivery.getCargoList() == null)
        {
            throw new WebApplicationException("Invalid delivery input", 400);
        }
        EntityManager em = getEntityManager();
        Delivery result = new Delivery(delivery);
        try
        {
            em.getTransaction().begin();
            result = em.merge(result);
            em.getTransaction().commit();
            return new DeliveryDTO(result);
        }
        catch (IllegalArgumentException e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("delivery does not comply with database standards", 400);
        }
        catch (Exception e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Something went wrong while editing delivery", 500);
        } finally
        {
            em.close();
        }
    }

    @Override
    public DeliveryDTO adminDeleteDelivery(int id)
    {
        Delivery result;
        EntityManager em = getEntityManager();
        try
        {
            result = em.find(Delivery.class, id);
            em.getTransaction().begin();
            em.remove(result);
            em.getTransaction().commit();
            return new DeliveryDTO(result);
        }
        catch (IllegalArgumentException e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Delivery does not comply with database standards", 400);
        }
        catch (Exception e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Something went wrong while deleting Delivery", 500);
        } finally
        {
            em.close();
        }
    }

    @Override
    public CargoDTO adminAddCargo(CargoDTO cargo)
    {
        if (cargo == null || cargo.getDelivery() == null || cargo.getName() == null)
        {
            throw new WebApplicationException("Invalid Cargo input", 400);
        }
        Cargo result = new Cargo(cargo);
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();

            em.persist(result);
            em.getTransaction().commit();
            return new CargoDTO(result);
        }
        catch (EntityExistsException e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Cargo already exists", 400);
        }
        catch (Exception e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Something went wrong while adding Cargo", 500);
        } finally
        {
            em.close();
        }
    }

    @Override
    public CargoDTO adminEditCargo(CargoDTO cargo)
    {
        if (cargo == null || cargo.getDelivery() == null || cargo.getName() == null)
        {
            throw new WebApplicationException("Invalid Cargo input", 400);
        }
        Cargo result = new Cargo(cargo);
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            result = em.merge(result);
            em.getTransaction().commit();
            return new CargoDTO(result);
        }
        catch (IllegalArgumentException e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Cargo does not comply with database standards", 400);
        }
        catch (Exception e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Something went wrong while editing Cargo", 500);
        } finally
        {
            em.close();
        }
    }

    @Override
    public CargoDTO adminDeleteCargo(int id)
    {
        Cargo result;
        EntityManager em = getEntityManager();
        try
        {
            result = em.find(Cargo.class, id);
            em.getTransaction().begin();
            em.remove(result);
            em.getTransaction().commit();
            return new CargoDTO(result);
        }
        catch (IllegalArgumentException e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Cargo does not comply with database standards", 400);
        }
        catch (Exception e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("Something went wrong while deleting Cargo", 500);
        } finally
        {
            em.close();
        }
    }

    @Override
    public List<DriverDTO> getAllDrivers()
    {
        EntityManager em = getEntityManager();
        try
        {
            List<Driver> drivers = em.createNamedQuery("Driver.getAll").getResultList();
            List<DriverDTO> result = new ArrayList();
            for (Driver d : drivers)
            {
                result.add(new DriverDTO(d));
            }
            return result;
        }
        catch (NoResultException e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("No Driver found", 404);
        }
        catch (Exception ex)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException(ex.getMessage(), 400);
        } finally
        {
            em.close();
        }
    }

    @Override
    public List<TruckDTO> getAllTrucks()
    {
        EntityManager em = getEntityManager();
        try
        {
            List<Truck> trucks = em.createNamedQuery("Truck.getAll").getResultList();
            List<TruckDTO> result = new ArrayList();
            for (Truck t : trucks)
            {
                result.add(new TruckDTO(t));
            }
            return result;
        }
        catch (NoResultException e)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException("No Truck found", 404);
        }
        catch (Exception ex)
        {
            em.getTransaction().rollback();
            throw new WebApplicationException(ex.getMessage(), 400);
        } finally
        {
            em.close();
        }
    }

}
