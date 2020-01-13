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
import java.util.List;

/**
 *
 * @author Chris
 */
public interface DeliveryFacadeInterface {

    public DriverDTO getDriverByID(int id);

    public DriverDTO getDriverByName(String driverName);

    public DriverDTO getDriverByTruck(String truckName);

    public DriverDTO adminAddDriver(DriverDTO driver);

    public DriverDTO adminEditDriver(DriverDTO driver);

    public DriverDTO adminDeleteDriver(int id);

    public List<DriverDTO> getAllDrivers();

    public TruckDTO getTruckByName(String truckName);

    public TruckDTO getTruckByDriver(String driverName);

    public TruckDTO adminAddTruck(TruckDTO truck);

    public TruckDTO adminEditTruck(TruckDTO truck);

    public TruckDTO adminDeleteTruck(int id);
    
    public List<TruckDTO> getAllTrucks();

    public DeliveryDTO adminAddDelivery(DeliveryDTO delivery);

    public DeliveryDTO adminEditDelivery(DeliveryDTO delivery);

    public DeliveryDTO adminDeleteDelivery(int id);

    public CargoDTO adminAddCargo(CargoDTO cargo);

    public CargoDTO adminEditCargo(CargoDTO cargo);

    public CargoDTO adminDeleteCargo(int id);

}
