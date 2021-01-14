package botTravel.controller;

import botTravel.entity.City;
import botTravel.service.CityService;
import botTravel.service.Impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BotPageController {

    @Autowired
    private CityService cityService = new CityServiceImpl();

    private ModelAndView modelAndView = new ModelAndView();

    @GetMapping("/")
    public ModelAndView allCitiesPage() {
        List<City> cityList = cityService.getAll();
        modelAndView.setViewName("cities");
        if (!cityList.isEmpty()){
            modelAndView.addObject("cityList", cityList);
        }else {
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
    @PostMapping ("/create")
    public ModelAndView createCity(@ModelAttribute("createCity") City city) {
        cityService.addCity(city);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
