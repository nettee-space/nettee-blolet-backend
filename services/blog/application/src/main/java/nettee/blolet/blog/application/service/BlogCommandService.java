package nettee.blolet.blog.application.service;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import nettee.blolet.blog.application.port.BlogCommandPort;
import nettee.blolet.blog.application.usecase.BlogCreateUseCase;
import nettee.blolet.blog.application.usecase.BlogDeleteUseCase;
import nettee.blolet.blog.application.usecase.BlogUpdateUseCase;
import nettee.blolet.blog.domain.Blog;

@Server
@RequiredArgsConstructor
public class BlogCommandService implements BlogCreateUseCase, BlogUpdateUseCase, BlogDeleteUseCase {

    private final BlogCommandPort blogCommandPort;

    @Override
    public Blog save(Blog blog) {
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
