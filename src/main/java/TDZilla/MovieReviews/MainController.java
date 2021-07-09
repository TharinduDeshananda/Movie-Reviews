package TDZilla.MovieReviews;


import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.awt.print.Pageable;
import java.util.*;

@Controller
public class MainController {
    final int POSTS_PER_PAGE=5;
    @Autowired
    @Qualifier("cmongoTemplate")
    MongoTemplate template;

    @RequestMapping("/")
    public RedirectView firstPAge(){

        return new RedirectView("/firstPage/"+1);
    }

    @RequestMapping("/tagPosts/{tag}/{pageNum}")
    public String getPostsByTag(@PathVariable("tag")String tag,@PathVariable("pageNum")int pageNum,Model model){

        PageRequest pageRequest = PageRequest.of(pageNum-1,POSTS_PER_PAGE);
        Query query = new Query(Criteria.where("movieTags").is(tag));
        long itemCount = template.count(query,MoviePost.class);
        long pageCount = (itemCount/POSTS_PER_PAGE)+1;
        query.with(pageRequest);
        List<MoviePost> posts = template.find(query,MoviePost.class);
        List<MovieTemplate> postTemplates = new ArrayList<>();
        if(posts.size()>0){
            for(MoviePost post: posts){
                MovieTemplate ptemplate = new MovieTemplate();
                ptemplate.setMovieName(post.getMovieName());
                ptemplate.setMovieDate(post.getMovieYear());
                ptemplate.setPostId(post.getId());
                ptemplate.setLikes(post.getLike());
                ptemplate.setDislikes(post.getDislike());
                String pic_id;
                if(post.getPicIdsList().size()>0){
                    pic_id = post.getPicIdsList().get(0);
                    MoviePicture pic = template.findById(new ObjectId(pic_id),MoviePicture.class);
                    ptemplate.setPicContent(Base64.getEncoder().encodeToString(pic.getPicContent().getData()));
                }

                postTemplates.add(ptemplate);
            }
        }

        model.addAttribute("posts",postTemplates);
        model.addAttribute("itemCount",itemCount);
        model.addAttribute("currentPage",pageNum);
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("currentTag",tag);
        return "tagPosts";
    }

    @RequestMapping("/firstPage/{pageId}")
    public String getIndex(@PathVariable(value = "pageId",required = false)Integer pageId,Model model){
        if(pageId==null)pageId=1;
        PageRequest pageRequest = PageRequest.of(pageId-1,POSTS_PER_PAGE);
        Query query = new Query();
        query.with(pageRequest);
        List<MoviePost> posts = template.find(query,MoviePost.class);
        Query query2 = new Query();
        long itemCount=template.count(query2,MoviePost.class);

        long pageCount = (itemCount/POSTS_PER_PAGE)+1;
        System.out.println("Page count: "+pageCount);


        List<MovieTemplate> postTemplates = new ArrayList<>();
        if(posts.size()>0){
            for(MoviePost post: posts){
                MovieTemplate ptemplate = new MovieTemplate();
                ptemplate.setMovieName(post.getMovieName());
                ptemplate.setMovieDate(post.getMovieYear());
                ptemplate.setPostId(post.getId());
                ptemplate.setLikes(post.getLike());
                ptemplate.setDislikes(post.getDislike());
                String pic_id;
                if(post.getPicIdsList().size()>0){
                    pic_id = post.getPicIdsList().get(0);
                    MoviePicture pic = template.findById(new ObjectId(pic_id),MoviePicture.class);
                    ptemplate.setPicContent(Base64.getEncoder().encodeToString(pic.getPicContent().getData()));
                }

                postTemplates.add(ptemplate);
            }
        }
        model.addAttribute("posts",postTemplates);
        model.addAttribute("itemCount",itemCount);
        model.addAttribute("currentPage",pageId);
        model.addAttribute("pageCount",pageCount);
        return "index";
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

    @RequestMapping("/update_post/{postId}")
    public String updatePost(@PathVariable("postId")String pid,Model model)throws Exception{
        System.out.println(pid+" ========= ffffff");
        MoviePost post = template.findById(new ObjectId(pid),MoviePost.class);
        if(post==null){throw new Exception("Movie Post not found");}
        ArrayList<String> picIdsList = post.getPicIdsList();
        for(String picID:picIdsList){
            MoviePicture picObj = template.findById(new ObjectId(picID),MoviePicture.class);
            post.addToBinaryPics(Base64.getEncoder().encodeToString(picObj.getPicContent().getData()));
        }

        model.addAttribute("post",post);
        return "update_post";
    }

    @RequestMapping("/remove_post/{pid}")
    public String removePost(@PathVariable String pid)throws Exception{
        System.out.println(pid+" ========= removing post");
        MoviePost post = template.findById(new ObjectId(pid),MoviePost.class);
        if(post==null){throw new Exception("Movie Post not found");}
        ArrayList<String> picIdsList = post.getPicIdsList();
        for(String picID:picIdsList){
            template.remove(new Query(Criteria.where("id").is(picID)), MoviePicture.class);

        }
        template.remove(post);

        return "forward:/";
    }




    @RequestMapping("/getPost/{pid}")
    public String getPost(@PathVariable("pid")String pid, Model model){
        System.out.println(pid+" ========= ffffff");
        MoviePost post = template.findById(new ObjectId(pid),MoviePost.class);
        if(post==null){return "index";}
        ArrayList<String> picIdsList = post.getPicIdsList();
        for(String picID:picIdsList){
            MoviePicture picObj = template.findById(new ObjectId(picID),MoviePicture.class);
            post.addToBinaryPics(Base64.getEncoder().encodeToString(picObj.getPicContent().getData()));
        }

        model.addAttribute("post",post);
        return "post";
    }

    @RequestMapping("/postComment")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String postComment(@RequestBody PostComment comment){
        UserComment userComment = new UserComment(comment.getUserName(),comment.getComment(),comment.getDate());
        System.out.println("------"+comment.getPid()+"-----");
        System.out.println("------"+comment.getUserName()+"-----");

        MoviePost post=template.findById(new ObjectId(comment.getPid()),MoviePost.class);
        post.getUserComments().add(userComment);
        template.save(post);
        return "Done posting comment";
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

                    if(file.isEmpty()){continue;}
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
