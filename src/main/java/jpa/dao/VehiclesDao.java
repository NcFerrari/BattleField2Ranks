package jpa.dao;

import java.util.List;

public interface VehiclesDao<Vehicles> {

    void saveOrUpdate(Vehicles vehicles);

    Vehicles getVehicles(int id);

    List<Vehicles> getAllVehicles();

    void deleteVehicles(Vehicles vehicles);

    void deleteVehicles(int id);

}