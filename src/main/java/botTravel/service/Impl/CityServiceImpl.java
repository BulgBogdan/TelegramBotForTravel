package botTravel.service.Impl;

import botTravel.entity.City;
import botTravel.repository.CityRepository;
import botTravel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City addCity(City city) {
        City saveCity = cityRepository.saveAndFlush(city);
        return saveCity;
    }

    @Override
    public void deleteCity(int id) {
        cityRepository.deleteById(id);
    }

    @Override
    public City getById(int id) {
        City cityFindById = cityRepository.getOne(id);
        return cityFindById;
    }

    @Override
    public City getByNameCity(String cityName) {
        City findCity = cityRepository.findByCity(cityName);
        return findCity;
    }

    @Override
    public City editCity(City city) {
        City editedCity = cityRepository.saveAndFlush(city);
        return editedCity;
    }

    @Override
    public Page<City> findPaginated(int page, int pageSize) {
        Sort sort = Sort.by("cityName").ascending();

        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        return cityRepository.findAll(pageable);
    }
}
