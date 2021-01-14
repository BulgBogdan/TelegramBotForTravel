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
public class BotPageController {

    @Autowired
    private CityService cityService = new CityServiceImpl();

    private ModelAndView modelAndView = new ModelAndView();

    @GetMapping("/")
    public ModelAndView allCitiesPage(@RequestParam(defaultValue = "1") int page) {
        int pageSize = 3;
        Page<City> pages = cityService.findPaginated(page, pageSize);
        List<City> cityList = pages.getContent();
        modelAndView.setViewName("cities");
        if (!cityList.isEmpty()) {
            modelAndView.addObject("page", page);
            modelAndView.addObject("pagesCount", pages.getTotalPages());
            modelAndView.addObject("countCities", pages.getTotalElements());

            modelAndView.addObject("cityList", cityList);
        } else {
            modelAndView.setViewName("redirect:/create");
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
        cityService.addCity(city);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCityPage(@PathVariable("id") int id) {
        City cityFindById = cityService.getById(id);
        modelAndView.setViewName("edit");
        modelAndView.addObject("editCity", cityFindById);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editCity(@ModelAttribute("editCity") City city) {
        cityService.editCity(city);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCity(@PathVariable("id") int id) {
        modelAndView.setViewName("redirect:/");
        cityService.deleteCity(id);
        return modelAndView;
    }
}
