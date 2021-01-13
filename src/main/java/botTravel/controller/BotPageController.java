package botTravel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BotPageController {

    private ModelAndView modelAndView = new ModelAndView();

    @GetMapping("/")
    public ModelAndView allCitiesPage() {
        return modelAndView;
    }
}
