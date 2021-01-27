package botTravel.controller;

import botTravel.entity.City;
import botTravel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/jsp/cities")
public class BotPageController {

    private CityService cityService;

    private ModelAndView modelAndView = new ModelAndView();

    @Autowired
    public BotPageController(CityService cityService) {
        this.cityService = cityService;
    }

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

        City foundById = cityService.getByName(cityName);

        //is there already such a city
        if (Objects.isNull(foundById)) {
            cityService.add(city);
            modelAndView.setViewName("redirect:/jsp/cities/");
        } else {
            String errorMessage = "Город с таким названием, уже существует.";
            modelAndView.addObject("error", errorMessage);
            modelAndView.setViewName("create");
            modelAndView.addObject("createCity", city);
        }

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCityPage(@PathVariable("id") int id) {
        City foundById = cityService.getById(id);
        modelAndView.setViewName("edit");
        modelAndView.addObject("editCity", foundById);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editCity(@ModelAttribute("editCity") City city, @PathVariable String id) {
        cityService.edit(city);
        modelAndView.setViewName("redirect:/jsp/cities/");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCity(@PathVariable("id") int id) {
        modelAndView.setViewName("redirect:/jsp/cities/");
        cityService.delete(id);
        return modelAndView;
    }
}
