package jblog.controller;

import jblog.annotation.Authorization;
import jblog.dto.PostDtoForSelect;
import jblog.dto.PostRequestDto;
import jblog.exception.BlogNotFoundException;
import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.service.FileUploadService;
import jblog.service.PostService;
import jblog.vo.BlogVo;
import jblog.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/{id:(?:(?!assets|user).)*}")
public class BlogController {
    private final PostService postService;
    private final CategoryService categoryService;
    private final BlogService blogService;
    private final FileUploadService fileUploadService;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    @Scope("singleton")
    public Map<String, BlogVo> blogTitle() {
        Map<String, BlogVo> blogTitle = new ConcurrentHashMap<>();
        for (BlogVo blogVo : blogService.findAll()) {
            blogTitle.put(blogVo.getUserId(), blogVo);
        }
        return blogTitle;
    }

    public BlogController(PostService postService, CategoryService categoryService, BlogService blogService, FileUploadService fileUploadService) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.blogService = blogService;
        this.fileUploadService = fileUploadService;
    }

    @Authorization(role = "ANY")
    @GetMapping({"", "/", "/{categoryId}", "/{categoryId}/{postId}"})
    public String blogView(@PathVariable("id") String id
            , @PathVariable(name = "categoryId", required = false) Optional<Integer> categoryId
            , @PathVariable(name = "postId", required = false) Optional<Integer> postId
            , Model model)  {

        blogService.checkBlog(id);

        PostDtoForSelect post = new PostDtoForSelect();
        post.setUserId(id);
        post.setCategoryId(categoryId.orElse(-1));
        post.setPostId(postId.orElse(-1));

        List<PostVo> postVoList = postService.findAllBySpec(post);
        model.addAttribute("post", !postVoList.isEmpty() ? postVoList.removeFirst() : null);
        model.addAttribute("boards", postVoList);
        model.addAttribute("categories", categoryService.findAllByBlogId(id));

//        userVo.ifPresent(vo -> model.addAttribute("admin", vo.getId().equals(id)));
        return "blog/main";
    }

    @Authorization(role = "ADMIN")
    @GetMapping({"/admin"})
    public String adminView(@PathVariable("id") String id) {
        return "blog/admin-default";
    }

    @Authorization(role = "ADMIN")
    @PostMapping({"/admin"})
    public String adminFix(@PathVariable("id") String id, @RequestParam("title") String title, @RequestParam("logo-file") MultipartFile file) {
        blogService.editBlog(id, title, Optional.ofNullable(fileUploadService.restore(file)).orElse(""));
        return "redirect:/" + id;
    }

    @Authorization(role = "ADMIN")
    @GetMapping({"/admin/category"})
    public String adminCategoryView(@PathVariable("id") String id, Model model) {
        model.addAttribute("categories", categoryService.findAllCategoryByBlogId(id));
        return "blog/admin-category";
    }

    @Authorization(role = "ADMIN")
    @GetMapping({"/admin/write"})
    public String adminWriteView(@PathVariable("id") String id,
                                 @ModelAttribute PostRequestDto postRequestDto,
                                 Model model) {

        model.addAttribute("categories", categoryService.findAllByBlogId(id));
        return "blog/admin-write";
    }

    @Authorization(role = "ADMIN")
    @PostMapping({"/admin/write"})
    public String adminWriteAction(@PathVariable("id") String id,
                                   @ModelAttribute PostRequestDto postRequestDto,
                                   Model model) {

        model.addAttribute("categories", categoryService.findAllByBlogId(id));
        if (postService.insertBoard(postRequestDto)) return "redirect:/" + id;

        return "blog/admin-write";
    }

}
