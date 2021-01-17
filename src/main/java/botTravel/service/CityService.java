package botTravel.service;

import botTravel.entity.City;
import org.springframework.data.domain.Page;

import java.util.List;


public interface CityService {

    City add(City city);

    void delete(int id);

    City getByName(String cityName);

    City getById(int id);

    City edit(City city);

    List<City> getAll();

    Page<City> findPaginated(int page, int pageSize);

}
