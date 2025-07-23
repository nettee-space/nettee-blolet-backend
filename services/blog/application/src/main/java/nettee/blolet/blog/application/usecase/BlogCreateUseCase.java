package nettee.blolet.blog.application.usecase;

import nettee.blolet.blog.domain.Blog;

public interface BlogCreateUseCase {
    Blog save(Blog blog);
}
