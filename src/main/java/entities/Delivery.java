/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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

/**
 *
 * @author Chris
 */
@Entity
@NamedQueries(
{
    @NamedQuery(name = "Delivery.getAll", query = "SELECT d FROM Delivery d"),
    @NamedQuery(name = "Delivery.getDeliveryByTruck", query = "SELECT d FROM Delivery d JOIN d.truck t WHERE t.name =:name"),
    @NamedQuery(name = "Delivery.getDeliveryByDate", query = "SELECT d FROM Delivery d WHERE d.shippingDate = :shippingDate"),
    @NamedQuery(name = "Delivery.getDeliveryByFromLocation", query = "SELECT d FROM Delivery d WHERE d.fromLocation = :fromLocation"),
    @NamedQuery(name = "Delivery.getDeliveryByDestination", query = "SELECT d FROM Delivery d WHERE d.destination = :destination"),
    @NamedQuery(name = "Delivery.getDeliveryByCargo", query = "SELECT d FROM Delivery d WHERE d.cargo = :cargo")
    
})
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Truck truck;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Cargo> cargo;
    
    private String shippingDate, fromLocation, destination;

    public Delivery()
    {
    }

    public Delivery(Truck truck, List<Cargo> cargo, String shippingDate, String fromLocation, String destination)
    {
        this.truck = truck;
        this.cargo = cargo;
        this.shippingDate = shippingDate;
        this.fromLocation = fromLocation;
        this.destination = destination;
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

    public List<Cargo> getCargo()
    {
        return cargo;
    }

    public void setCargo(List<Cargo> cargo)
    {
        this.cargo = cargo;
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
        hash = 19 * hash + Objects.hashCode(this.cargo);
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
        if (!Objects.equals(this.cargo, other.cargo))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Delivery{" + "id=" + id + ", truck=" + truck + ", cargo=" + cargo + ", shippingDate=" + shippingDate + ", fromLocation=" + fromLocation + ", destination=" + destination + '}';
    }

}
