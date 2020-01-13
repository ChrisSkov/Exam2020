/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Cargo;
import entities.Delivery;
import entities.Truck;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Chris
 */
public class DeliveryDTO {

    private List<CargoDTO> cargoList = new ArrayList();
    private int id;
    private Truck truck;
    private String shippingDate, fromLocation, destination;

    public DeliveryDTO()
    {
    }

    public DeliveryDTO(Delivery delivery)
    {
        this.id = delivery.getId();
        this.truck = delivery.getTruck();
        this.shippingDate = delivery.getShippingDate();
        this.fromLocation = delivery.getFromLocation();
        this.destination = delivery.getDestination();

        for (Cargo c : delivery.getCargoList())
        {
            cargoList.add(new CargoDTO(c));
        }
    }

    public Truck getTruck()
    {
        return truck;
    }

    public void setTruck(Truck truck)
    {
        this.truck = truck;
    }

    public void addDeliveryDTO(CargoDTO c)
    {
        this.cargoList.add(c);
    }

    public List<CargoDTO> getCargoList()
    {
        return cargoList;
    }

    public void setCargoList(List<CargoDTO> cargoList)
    {
        this.cargoList = cargoList;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getShippingDate()
    {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate)
    {
        this.shippingDate = shippingDate;
    }

    public String getFromLocation()
    {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation)
    {
        this.fromLocation = fromLocation;
    }

    public String getDestination()
    {
        return destination;
    }

    public void setDestination(String destination)
    {
        this.destination = destination;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.cargoList);
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.truck);
        hash = 53 * hash + Objects.hashCode(this.shippingDate);
        hash = 53 * hash + Objects.hashCode(this.fromLocation);
        hash = 53 * hash + Objects.hashCode(this.destination);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final DeliveryDTO other = (DeliveryDTO) obj;
        if (this.id != other.id)
        {
            return false;
        }
        if (!Objects.equals(this.shippingDate, other.shippingDate))
        {
            return false;
        }
        if (!Objects.equals(this.fromLocation, other.fromLocation))
        {
            return false;
        }
        if (!Objects.equals(this.destination, other.destination))
        {
            return false;
        }
        if (!Objects.equals(this.cargoList, other.cargoList))
        {
            return false;
        }
        if (!Objects.equals(this.truck, other.truck))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "DeliveryDTO{" + "cargoList=" + cargoList + ", id=" + id + ", truck=" + truck + ", shippingDate=" + shippingDate + ", fromLocation=" + fromLocation + ", destination=" + destination + '}';
    }

}
