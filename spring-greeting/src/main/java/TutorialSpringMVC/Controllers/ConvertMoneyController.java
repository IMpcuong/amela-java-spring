package TutorialSpringMVC.Controllers;

import TutorialSpringMVC.Service.ConvertMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConvertMoneyController {

//    public final ConvertMoneyService convertMoneyService;
//
//    public ConvertMoneyController(ConvertMoneyService convertMoneyService) {
//        this.convertMoneyService = convertMoneyService;
//    }

    @GetMapping(value ="/convert-money-form")
    public String convertMoney() {
        return "convert-money";
    }
    
    @PostMapping(value = "/convert-money-result")
    public ModelAndView convertMoneyResult(@RequestParam String rate, @RequestParam String money) {
        long _rate = Long.parseLong(rate);
        long _money = Long.parseLong(money);
        long result = _rate * _money;

        ModelAndView modelAndView = new ModelAndView("convert-money-result", "convert-result", result);

        return modelAndView;
    }
}
