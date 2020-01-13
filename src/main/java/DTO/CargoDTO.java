/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Cargo;
import entities.Delivery;
import java.util.Objects;

/**
 *
 * @author Chris
 */
public class CargoDTO {

    private Delivery delivery;
    private String name, weight, units;
    private int id;

    public CargoDTO()
    {
    }

    public CargoDTO(Cargo cargo)
    {
        this.delivery = cargo.getDelivery();
        this.name = cargo.getName();
        this.weight = cargo.getWeight();
        this.units = cargo.getUnits();
        this.id = cargo.getId();
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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.delivery);
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.weight);
        hash = 23 * hash + Objects.hashCode(this.units);
        hash = 23 * hash + this.id;
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
        final CargoDTO other = (CargoDTO) obj;
        if (this.id != other.id)
        {
            return false;
        }
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
        if (!Objects.equals(this.delivery, other.delivery))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "CargoDTO{" + "delivery=" + delivery + ", name=" + name + ", weight=" + weight + ", units=" + units + ", id=" + id + '}';
    }

}
