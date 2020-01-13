/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Driver;
import entities.Truck;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Chris
 */
public class DriverDTO {

    private String name;
    private int id;
    private List<TruckDTO> truckList = new ArrayList();

    public DriverDTO()
    {
    }

    public DriverDTO(Driver driver)
    {
        this.id = driver.getId();
        this.name = driver.getName();
        for(Truck t : driver.getTruckList())
        {
            truckList.add(new TruckDTO(t));
        }

    }

    public void addTruckDTO(TruckDTO t)
    {
        this.truckList.add(t);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public List<TruckDTO> getTruckList()
    {
        return truckList;
    }

    public void setTruckList(List<TruckDTO> truckList)
    {
        this.truckList = truckList;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + this.id;
        hash = 73 * hash + Objects.hashCode(this.truckList);
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
        final DriverDTO other = (DriverDTO) obj;
        if (this.id != other.id)
        {
            return false;
        }
        if (!Objects.equals(this.name, other.name))
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
        return "DriverDTO{" + "name=" + name + ", id=" + id + ", truckList=" + truckList + '}';
    }

}
