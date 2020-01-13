/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import DTO.TruckDTO;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Chris
 */
@Entity
@NamedQueries(
        {
            @NamedQuery(name = "Truck.getAll", query = "SELECT t FROM Truck t"),
            @NamedQuery(name = "Truck.getTruckByName", query = "SELECT t FROM Truck t WHERE t.name = :name"),
            @NamedQuery(name = "Truck.getTruckByDriver", query = "SELECT t FROM Truck t JOIN t.drivers d WHERE d.name = :name"),
            @NamedQuery(name = "Truck.getTruckByID", query = "SELECT t FROM Truck t WHERE t.id = :id"),
            @NamedQuery(name = "Truck.getTruckByShippingDate", query = "SELECT t FROM Truck t JOIN t.deliveries d WHERE d.shippingDate = :shippingDate")
        })
public class Truck implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name, capacity;
    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "trucks")
    private List<Driver> drivers;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Delivery> deliveries;

    public Truck(String name, String capacity, List<Driver> drivers, List<Delivery> deliveries)
    {
        this.name = name;
        this.capacity = capacity;
        this.drivers = drivers;
        this.deliveries = deliveries;
    }

    public Truck(TruckDTO truck)
    {
        this.id = truck.getId();
        this.name = truck.getName();
        this.capacity = truck.getCapacity();
        truck.getDriverList().forEach((d) -> //lambda expression. can be passed around like an object
        {
            drivers.add(new Driver(d));
        });
    }

    public Truck()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCapacity()
    {
        return capacity;
    }

    public void setCapacity(String capacity)
    {
        this.capacity = capacity;
    }

    public List<Driver> getDrivers()
    {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers)
    {
        this.drivers = drivers;
    }

    public List<Delivery> getDeliveries()
    {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries)
    {
        this.deliveries = deliveries;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.capacity);
        hash = 53 * hash + Objects.hashCode(this.drivers);
        hash = 53 * hash + Objects.hashCode(this.deliveries);
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
        final Truck other = (Truck) obj;
        if (!Objects.equals(this.name, other.name))
        {
            return false;
        }
        if (!Objects.equals(this.capacity, other.capacity))
        {
            return false;
        }
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        if (!Objects.equals(this.drivers, other.drivers))
        {
            return false;
        }
        if (!Objects.equals(this.deliveries, other.deliveries))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Truck{" + "id=" + id + ", name=" + name + ", capacity=" + capacity + ", drivers=" + drivers + ", deliveries=" + deliveries + '}';
    }

}
