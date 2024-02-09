package ivan.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Controlador {

    public Controlador(){}

    @RequestMapping({"/"})
    public String saludo(){
        return "login";  //.jsp
    }
}
