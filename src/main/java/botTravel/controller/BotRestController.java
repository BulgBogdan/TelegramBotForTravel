package botTravel.controller;

import botTravel.entity.City;
import botTravel.service.CityService;
import botTravel.service.Impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/city")
public class BotRestController {

    @Autowired
    private CityService cityService = new CityServiceImpl();

    @GetMapping("/")
    @ResponseBody
    public List<City> getAll() {
        return cityService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable("id") int id) {
        City city = cityService.getById(id);
        if (Objects.isNull(city)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(city);
        }
    }

    @PostMapping("/")
    public ResponseEntity<City> createCity(@RequestBody() City city) {
        //is there already such a city
        City foundById = cityService.getByName(city.getCityName());
        if (Objects.isNull(foundById)) {
            return addCity(city);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<City> editCity(@RequestBody City editCity, @PathVariable("id") int id) {
        City city = cityService.getById(id);

        city.setCityName(editCity.getCityName());
        city.setCityInfo(editCity.getCityInfo());

        cityService.edit(city);
        return ResponseEntity.ok(city);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable("id") int id) {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<City> addCity(City city) {
        City createCity = cityService.add(city);
        if (Objects.nonNull(createCity)) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createCity.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(createCity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}