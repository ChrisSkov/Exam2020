/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Truck;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Chris
 */
public class TruckDTO {

    private Integer id;
    private String name, capacity;

    private List<DriverDTO> driverList = new ArrayList();

    TruckDTO(Truck truck)
    {
        this.id = truck.getId();
        this.name = truck.getName();
        this.capacity = truck.getCapacity();
        truck.getDrivers().forEach((d) -> //lambda expression. can be passed around like an object
        {
            driverList.add(new DriverDTO(d));
        });
    }

    public TruckDTO()
    {
    }

    public void addDriverDTO(DriverDTO d)
    {
        this.driverList.add(d);
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

    public List<DriverDTO> getDriverList()
    {
        return driverList;
    }

    public void setDriverList(List<DriverDTO> driverList)
    {
        this.driverList = driverList;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.capacity);
        hash = 97 * hash + Objects.hashCode(this.driverList);
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
        final TruckDTO other = (TruckDTO) obj;
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
        if (!Objects.equals(this.driverList, other.driverList))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "TruckDTO{" + "id=" + id + ", name=" + name + ", capacity=" + capacity + ", driverList=" + driverList + '}';
    }

}
