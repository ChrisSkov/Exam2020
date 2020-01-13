/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import DTO.CargoDTO;
import DTO.DeliveryDTO;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Chris
 */
@Entity
@Table(name = "deliveries")
@NamedQueries(
        {
            @NamedQuery(name = "Delivery.getAll", query = "SELECT d FROM Delivery d"),
            @NamedQuery(name = "Delivery.deleteAllRows", query = "DELETE FROM Delivery"),
            @NamedQuery(name = "Delivery.getDeliveryByTruck", query = "SELECT d FROM Delivery d JOIN d.truck t WHERE t.name =:name"),
            @NamedQuery(name = "Delivery.getDeliveryByDate", query = "SELECT d FROM Delivery d WHERE d.shippingDate = :shippingDate"),
            @NamedQuery(name = "Delivery.getDeliveryByFromLocation", query = "SELECT d FROM Delivery d WHERE d.fromLocation = :fromLocation"),
            @NamedQuery(name = "Delivery.getDeliveryByDestination", query = "SELECT d FROM Delivery d WHERE d.destination = :destination"),
            @NamedQuery(name = "Delivery.getDeliveryByCargo", query = "SELECT d FROM Delivery d WHERE d.cargoList = :cargo")

        })
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Truck truck;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Cargo> cargoList;

    private String shippingDate, fromLocation, destination;

    public Delivery()
    {
    }

    public Delivery(Truck truck, List<Cargo> cargo, String shippingDate, String fromLocation, String destination)
    {
        this.truck = truck;
         this.cargoList = cargo;
        this.shippingDate = shippingDate;
        this.fromLocation = fromLocation;
        this.destination = destination;
    }

    public Delivery(DeliveryDTO delivery)
    {
        this.id = delivery.getId();
        this.truck = delivery.getTruck();
        this.shippingDate = delivery.getShippingDate();
        this.fromLocation = delivery.getFromLocation();
        this.destination = delivery.getDestination();
        for (CargoDTO cargo : delivery.getCargoList())
        {
            cargoList.add(new Cargo(cargo));

        }
    }

    public void addCargo(Cargo cargo)
    {
        this.cargoList.add(cargo);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Truck getTruck()
    {
        return truck;
    }

    public void setTruck(Truck truck)
    {
        this.truck = truck;
    }

    public List<Cargo> getCargoList()
    {
        return cargoList;
    }

    public void setCargoList(List<Cargo> cargoList)
    {
        this.cargoList = cargoList;
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
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.truck);
        hash = 19 * hash + Objects.hashCode(this.cargoList);
        hash = 19 * hash + Objects.hashCode(this.shippingDate);
        hash = 19 * hash + Objects.hashCode(this.fromLocation);
        hash = 19 * hash + Objects.hashCode(this.destination);
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
        final Delivery other = (Delivery) obj;
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
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        if (!Objects.equals(this.truck, other.truck))
        {
            return false;
        }
        if (!Objects.equals(this.cargoList, other.cargoList))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Delivery{" + "id=" + id + ", truck=" + truck + ", cargo=" + cargoList + ", shippingDate=" + shippingDate + ", fromLocation=" + fromLocation + ", destination=" + destination + '}';
    }

}
