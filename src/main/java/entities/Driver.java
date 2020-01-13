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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Chris
 */
@Entity
@NamedQueries(
{
    @NamedQuery(name = "Driver.getAll", query = "SELECT d FROM Driver d"),
    @NamedQuery(name = "Driver.getDriverByName", query = "SELECT d FROM Driver d WHERE d.name = :name"),
    @NamedQuery(name = "Driver.getDriverByTrcuk", query = "SELECT d FROM Driver d JOIN d.trucks t WHERE t.name = :name"),
    @NamedQuery(name = "Driver.getDriverByID", query = "SELECT d FROM Driver d WHERE d.id = :id")
})
public class Driver implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Truck> trucks;

    public Driver(String name, List<Truck> trucks)
    {
        this.name = name;
        this.trucks = trucks;
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

    public List<Truck> getTrucks()
    {
        return trucks;
    }

    public void setTrucks(List<Truck> trucks)
    {
        this.trucks = trucks;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.trucks);
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
        if (!Objects.equals(this.trucks, other.trucks))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Driver{" + "id=" + id + ", name=" + name + ", trucks=" + trucks + '}';
    }

}
