/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import DTO.DriverDTO;
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
import javax.persistence.Table;

/**
 *
 * @author Chris
 */
@Entity
@Table(name = "drivers")
@NamedQueries(
        {
            @NamedQuery(name = "Driver.getAll", query = "SELECT d FROM Driver d"),
            @NamedQuery(name = "Driver.deleteAllRows", query = "DELETE FROM Driver"),
            @NamedQuery(name = "Driver.getDriverByName", query = "SELECT d FROM Driver d WHERE d.name = :name"),
            @NamedQuery(name = "Driver.getDriverByTruck", query = "SELECT d FROM Driver d JOIN d.truckList t WHERE t.name = :name"),
            @NamedQuery(name = "Driver.getDriverByID", query = "SELECT d FROM Driver d WHERE d.id = :id")
        })
public class Driver implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Truck> truckList;

    public Driver(String name/*,List<Truck> trucks*/)
    {
        this.name = name;
        //this.truckList = trucks;
    }

    public Driver(DriverDTO driver)
    {
        this.id = driver.getId();
        this.name = driver.getName();
        for (TruckDTO truck : driver.getTruckList())
        {
            truckList.add(new Truck(truck));

        }

    }

    public Driver()
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

    public List<Truck> getTruckList()
    {
        return truckList;
    }

    public void setTruckList(List<Truck> truckList)
    {
        this.truckList = truckList;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.truckList);
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
        final Driver other = (Driver) obj;
        if (!Objects.equals(this.name, other.name))
        {
            return false;
        }
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        if (!Objects.equals(this.truckList, other.truckList))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Driver{" + "id=" + id + ", name=" + name + ", trucks=" + truckList + '}';
    }

}
