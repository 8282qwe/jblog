package jblog.service;

import jblog.dto.PostDtoForSelect;
import jblog.dto.PostRequestDto;
import jblog.repository.PostRepository;
import jblog.vo.PostVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostVo> findAllBySpec(PostDtoForSelect spec) {
        List<PostVo> postVos = postRepository.findAllBySpec(spec);
        if (spec.getPostId() != -1 && postVos.size() > 1) {
            for (int i = 0; i < postVos.size(); i++) {
                PostVo postVo = postVos.get(i);
                if (postVo.getId() == spec.getPostId()) {
                    postVos.addFirst(postVos.remove(i));
                    break;
                }
            }
        }
        return postVos;
    }

    public boolean insertBoard(PostRequestDto dto) {
        return postRepository.insertPost(new PostVo(dto.getTitle(), dto.getContent(), dto.getCategory()));
    }

    public boolean existByCategoryId(int categoryId) {
        return postRepository.existDeletePostByCategoryId(categoryId);
    }
}
