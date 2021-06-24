package TDZilla.MovieReviews;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GeneralExceptionHandlers {

    @ExceptionHandler({})
    public ModelAndView handleExceptions(HttpServletRequest req, HttpServletResponse res,Exception ex){
        ex.printStackTrace();
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception",ex);
        mv.setViewName("error");

        return mv;
    }


}
