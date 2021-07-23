package TutorialSpringMVC.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import TutorialSpringMVC.Service.TranslatorService;

@Controller
public class TranslatorController {

    private final TranslatorService translatorService;

    public TranslatorController(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    @GetMapping("/translate")
    public String translateForm() {
        return "translate";
    }

    @PostMapping("/translate-result")
    public ModelAndView translateForm(@RequestParam String txtSearch) {

        String result = translatorService.translate(txtSearch);
        ModelAndView modelAndView = new ModelAndView("translate", "translate-result", result);
        return modelAndView;
    }
}