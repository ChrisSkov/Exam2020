/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Chris
 */
@Entity
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne( cascade = CascadeType.PERSIST)
    private Delivery delivery;
    private String name, weight, units;

    public Cargo()
    {
    }

    public Cargo(Delivery delivery, String name, String weight, String units)
    {
        this.delivery = delivery;
        this.name = name;
        this.weight = weight;
        this.units = units;
    }

    
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Delivery getDelivery()
    {
        return delivery;
    }

    public void setDelivery(Delivery delivery)
    {
        this.delivery = delivery;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getWeight()
    {
        return weight;
    }

    public void setWeight(String weight)
    {
        this.weight = weight;
    }

    public String getUnits()
    {
        return units;
    }

    public void setUnits(String units)
    {
        this.units = units;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.delivery);
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.weight);
        hash = 43 * hash + Objects.hashCode(this.units);
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
        final Cargo other = (Cargo) obj;
        if (!Objects.equals(this.name, other.name))
        {
            return false;
        }
        if (!Objects.equals(this.weight, other.weight))
        {
            return false;
        }
        if (!Objects.equals(this.units, other.units))
        {
            return false;
        }
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        if (!Objects.equals(this.delivery, other.delivery))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Cargo{" + "id=" + id + ", delivery=" + delivery + ", name=" + name + ", weight=" + weight + ", units=" + units + '}';
    }

    
}
