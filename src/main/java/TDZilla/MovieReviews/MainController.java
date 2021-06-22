package TDZilla.MovieReviews;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {




    @RequestMapping("/")
    public ModelAndView getIndex(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index.html");
        return mv;
    }

    @RequestMapping("/e")
    public String showErrorPage()throws Exception{
        throw new Exception("Error occured");
    }



}
