package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //usamos restcontroller el cual es para retornar json y xml, de usar el controller tradicional necesitariamos otra etiqueta
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String helloWord(){
        return "Hello word from colombia!";
    }
}
