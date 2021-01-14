package botTravel.service;

import botTravel.entity.City;
import org.springframework.data.domain.Page;


public interface CityService {

    City addCity(City city);

    void deleteCity(int id);

    City getByNameCity(String cityName);

    City getById(int id);

    City editCity(City city);

    Page<City> findPaginated(int page, int pageSize);

}
