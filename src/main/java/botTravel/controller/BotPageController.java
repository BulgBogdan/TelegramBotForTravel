package botTravel.controller;

import botTravel.entity.City;
import botTravel.service.CityService;
import botTravel.service.Impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/jsp/cities")
public class BotPageController {

    @Autowired
    private CityService cityService = new CityServiceImpl();

    private ModelAndView modelAndView = new ModelAndView();

    @GetMapping("/")
    public ModelAndView allCitiesPage(@RequestParam(defaultValue = "1") int page) {
        int pageSize = 5;
        Page<City> pages = cityService.findPaginated(page, pageSize);
        List<City> cityList = pages.getContent();
        modelAndView.setViewName("cities");
        if (!cityList.isEmpty()) {
            modelAndView.addObject("page", page);
            modelAndView.addObject("pagesCount", pages.getTotalPages());
            modelAndView.addObject("countCities", pages.getTotalElements());

            modelAndView.addObject("cityList", cityList);
        } else {
            modelAndView.setViewName("redirect:/jsp/cities/create");
        }
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createCityPage() {
        modelAndView.setViewName("create");
        modelAndView.addObject("createCity", new City());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCity(@ModelAttribute("createCity") City city) {
        String cityName = city.getCityName();
        City foundCityById = cityService.getByNameCity(cityName);

        //is there already such a city
        if (foundCityById == null) {
            cityService.addCity(city);
            modelAndView.setViewName("redirect:/jsp/cities/");
        }else{
            String errorMessage = "Город с таким названием, уже существует.";
            modelAndView.addObject("error", errorMessage);
            modelAndView.setViewName("create");
            modelAndView.addObject("createCity", city);
        }

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCityPage(@PathVariable("id") int id) {
        City foundCityById = cityService.getById(id);
        modelAndView.setViewName("edit");
        modelAndView.addObject("editCity", foundCityById);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editCity(@ModelAttribute("editCity") City city) {
        cityService.editCity(city);
        modelAndView.setViewName("redirect:/jsp/cities/");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCity(@PathVariable("id") int id) {
        modelAndView.setViewName("redirect:/jsp/cities/");
        cityService.deleteCity(id);
        return modelAndView;
    }
}
