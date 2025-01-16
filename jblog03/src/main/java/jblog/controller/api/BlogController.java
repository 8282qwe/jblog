package jblog.controller.api;

import jblog.service.CategoryService;
import jblog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("BlogApiController")
@RequestMapping("/api/blog")
//@Authorization(role = "ADMIN")
public class BlogController {
    private final CategoryService categoryService;
    private final PostService postService;

    public BlogController(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
    }

    @GetMapping({"/category/delete/{id}"})
    public ResponseEntity<String> adminCategoryDelete(@PathVariable("id") int categoryId) {
        if (!postService.existByCategoryId(categoryId)) {
            return ResponseEntity.status(510).body("게시글이 있어 삭제할 수 없습니다.");
        }

        if (!categoryService.deleteCategory(categoryId)){
            return ResponseEntity.status(501).body("기본 카테고리는 삭제할 수 없습니다.");
        }
        return ResponseEntity.ok("success");
    }

    @PostMapping({"/category/add"})
    public ResponseEntity<String> adminCategoryAdd(@RequestParam("id") String id,
                                           @RequestParam("name") String categoryName,
                                           @RequestParam("description") String description) {
        categoryService.insertCategory(id, categoryName, description);
        return ResponseEntity.ok("success");
    }

}
