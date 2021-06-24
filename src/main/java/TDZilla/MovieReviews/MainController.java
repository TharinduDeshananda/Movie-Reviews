package TDZilla.MovieReviews;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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

    @RequestMapping("/savePost")
    public String savePost(@RequestParam("movieName")String movieName,@RequestParam("postName")String postName,
                           @RequestParam("movieYear")long movieYear,@RequestParam(value = "moviePics",required = false)MultipartFile[] moviePics,
                           @RequestParam(value = "movieTags",required = false)String[] movieTags,@RequestParam("postContent")String postContent){

            MoviePost post = new MoviePost();
            post.setMovieName(movieName);
            post.setPostName(postName);
            post.setMovieYear(movieYear);
            post.setPostContent(postContent);
            post.setLike(0);
            post.setDislike(0);

            if(movieTags!=null){
                for(String tag:movieTags){
                    post.addToMovieTags(tag);
                }
            }
        System.out.println(moviePics.length);
            for(MultipartFile file : moviePics){
                try{

                    MoviePicture pic = new MoviePicture();
                    pic.setMovieName("");
                    pic.setPicDate(new Date());
                    pic.setPicContent(new Binary(BsonBinarySubType.BINARY,file.getBytes()));
                    pic=template.save(pic);
                    post.addToPicIdList(pic.getId());
                }catch(Exception e){
                    System.out.println(e);
                }
            }


            post.setPostDate(new Date());
            post.setEditorName("Tharindu");
            post.setLastEdited(new Date());
            template.save(post);
            return "post_success";

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
