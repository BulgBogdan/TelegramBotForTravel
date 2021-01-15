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
        return cityRepository.saveAndFlush(city);
    }

    @Override
    public void deleteCity(int id) {
        cityRepository.deleteById(id);
    }

    @Override
    public City getById(int id) {
        return cityRepository.getOne(id);
    }

    @Override
    public City getByNameCity(String cityName) {
        return cityRepository.findByCityName(cityName);
    }

    @Override
    public City editCity(City city) {
        return  cityRepository.saveAndFlush(city);
    }

    @Override
    public List<City> getAllCities() {
        Sort sort = Sort.by("cityName").ascending();
        return cityRepository.findAll(sort);
    }

    @Override
    public Page<City> findPaginated(int page, int pageSize) {
        Sort sort = Sort.by("cityName").ascending();
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        return cityRepository.findAll(pageable);
    }
}
