package TDZilla.MovieReviews;


import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GeneralExceptionHandlers {




    @ExceptionHandler({})
    public ModelAndView handleExceptions(HttpServletRequest req, HttpServletResponse res,Exception ex){
        ex.printStackTrace();
        System.out.println(ex);
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception",ex);
        mv.setViewName("error");

        return mv;
    }


}
