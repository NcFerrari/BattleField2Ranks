package lp.jpa.dao;

import lp.business.dto.Vehicles;

import java.util.List;

public interface VehiclesDao {

    void saveOrUpdate(Vehicles vehicles);

    Vehicles getVehicles(int id);

    List<Vehicles> getAllVehicles();

    void deleteVehicles(Vehicles vehicles);

    void deleteVehicles(int id);

}