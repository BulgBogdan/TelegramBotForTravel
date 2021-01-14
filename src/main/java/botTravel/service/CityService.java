package botTravel.service;

import botTravel.entity.City;

import java.util.List;

public interface CityService {

    City addCity(City city);

    void deleteCity(int id);

    City getByCity(String city);

    City editCity(City city);

    List<City> getAll();

}
