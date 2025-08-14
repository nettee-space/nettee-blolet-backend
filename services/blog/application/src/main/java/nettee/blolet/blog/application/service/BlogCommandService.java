package nettee.blolet.blog.application.service;

import lombok.RequiredArgsConstructor;
import nettee.blolet.blog.application.port.BlogCommandRepositoryPort;
import nettee.blolet.blog.application.usecase.BlogCreateUseCase;
import nettee.blolet.blog.application.usecase.BlogDeleteUseCase;
import nettee.blolet.blog.application.usecase.BlogUpdateUseCase;
import nettee.blolet.blog.domain.Blog;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_COMMAND_FORBIDDEN;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_ID_REQUIRED;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_MAXIMUM_EXCEEDED;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_NAME_CANNOT_BE_BLANK;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_NOT_FOUND;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_OWNER_ID_REQUIRED;

@Service
@RequiredArgsConstructor
public class BlogCommandService implements BlogCreateUseCase, BlogUpdateUseCase, BlogDeleteUseCase {

    private final BlogCommandRepositoryPort commandRepository;

    @Override
    public Blog save(Blog blog) {
        int count = commandRepository.countByUserId(blog.getUserId());

        if (count >= 1 /* TODO 정책 데이터 관리 전략 도입 시 수정 */) {
            throw BLOG_MAXIMUM_EXCEEDED.exception();
        }
        return commandRepository.save(blog);
    }

    /**
     *
     * @param blogId 블로그 아이디
     * @throws nettee.common.CustomException includes {@code BLOG_NOT_FOUND} error code if target blog doesn't exist.
     */
    @Override
    public void deleteById(String blogId) {
        commandRepository.deleteById(blogId);
    }

    @Override
    public Blog update(Blog blog) {
        Objects.requireNonNull(blog, "Blog cannot be null");
        String blogId = Objects.requireNonNull(blog.getId(), () -> {
            throw BLOG_ID_REQUIRED.exception();
        });
        String userId = Objects.requireNonNull(blog.getUserId(), () -> {
            throw BLOG_OWNER_ID_REQUIRED.exception();
        });
        String name = Objects.requireNonNull(blog.getName(), () -> {
            throw BLOG_NAME_CANNOT_BE_BLANK.exception();
        });
        String url = blog.getUrlIdentifier();

        if (name.isBlank()) {
            throw BLOG_NAME_CANNOT_BE_BLANK.exception();
        }

        // Exception when BLOG_NOT_FOUND
        var entity = commandRepository.findById(blogId)
                .orElseThrow(BLOG_NOT_FOUND::exception);

        // validate ownership
        if (!Objects.equals(userId, entity.getUserId())) {
            throw BLOG_COMMAND_FORBIDDEN.exception();
        }

        // update
        entity.prepareUpdate()
                .name(name)
                .url(url)
                .update();

        return commandRepository.update(entity);
    }

    @Override
    public Blog updateUrl(String userId, String blogId, String url) {
        Objects.requireNonNull(blogId, "blogId cannot be null");
        Objects.requireNonNull(url, "url cannot be null");
        var entity = commandRepository.findById(blogId)
                .orElseThrow(BLOG_NOT_FOUND::exception);

        // validate ownership
        if (!Objects.equals(userId, entity.getUserId())) {
            throw BLOG_COMMAND_FORBIDDEN.exception();
        }

        entity.updateUrl(url);

        return commandRepository.save(entity);
    }
}
