package botTravel.service.Impl;

import botTravel.entity.City;
import botTravel.repository.CityRepository;
import botTravel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public City getByCity(String city) {
        City findCity = cityRepository.findByCity(city);
        return findCity;
    }

    @Override
    public City editCity(City city) {
        City editedCity = cityRepository.saveAndFlush(city);
        return editedCity;
    }

    @Override
    public List<City> getAll() {
        List<City> cityList = cityRepository.findAll();
        return cityList;
    }
}
