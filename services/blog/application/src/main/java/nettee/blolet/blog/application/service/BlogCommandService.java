package nettee.blolet.blog.application.service;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import nettee.blolet.blog.application.port.BlogCommandPort;
import nettee.blolet.blog.application.usecase.BlogCreateUseCase;
import nettee.blolet.blog.application.usecase.BlogDeleteUseCase;
import nettee.blolet.blog.application.usecase.BlogUpdateUseCase;
import nettee.blolet.blog.domain.Blog;

import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_MAXIMUM_EXCEEDED;

@Server
@RequiredArgsConstructor
public class BlogCommandService implements BlogCreateUseCase, BlogUpdateUseCase, BlogDeleteUseCase {

    private final BlogCommandPort blogCommandPort;

    @Override
    public Blog save(Blog blog) {
        int count = blogCommandPort.countByUserId(blog.getUserId());

        if (count >= 1 /* TODO 정책 데이터 관리 전략 도입 시 수정 */) {
            throw BLOG_MAXIMUM_EXCEEDED.exception();
        }
        return blogCommandPort.save(blog);
    }

    @Override
    public void deleteById(String blogId) {
        throw new Error("Not implemented yet");
    }

    @Override
    public Blog update(String blogId, String name, String url) {
        throw new Error("Not implemented yet");
    }

    @Override
    public Blog updateUrl(String blogId, String url) {
        throw new Error("Not implemented yet");
    }
}
