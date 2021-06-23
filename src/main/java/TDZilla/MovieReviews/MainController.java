package TDZilla.MovieReviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    @Qualifier("cmongoTemplate")
    MongoTemplate template;

    @RequestMapping("/")
    public ModelAndView getIndex(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index.html");
        return mv;
    }


    @RequestMapping("/signin")
    public ModelAndView getSignIn(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("loginPage");
        return mv;
    }

    @RequestMapping("/signup")
    public ModelAndView getSignUp(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("create-acc");
        return mv;
    }

    @RequestMapping("/admin-dash")
    public String getAdminDashBoard(){
        return "admin_dash";
    }

    @RequestMapping("/create_post")
    public String getCreatePost(Model model){
        return "create_post";
    }

    @RequestMapping("/mtest")
    @ResponseBody
    public String testMongo(){
        Osama osama1 = new Osama("Osama","Binladen");
        template.save(osama1);
        return "";
    }

    @RequestMapping("/e")
    public String showErrorPage()throws Exception{
        throw new Exception("Error occured");
    }



}
