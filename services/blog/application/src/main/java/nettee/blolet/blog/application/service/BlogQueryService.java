package nettee.blolet.blog.application.service;

import lombok.RequiredArgsConstructor;
import nettee.blolet.blog.application.port.BlogQueryRepositoryPort;
import nettee.blolet.blog.application.usecase.BlogReadUseCase;
import nettee.blolet.blog.application.usecase.BlogOwnershipVerifyUseCase;
import nettee.blolet.blog.domain.Blog;
import nettee.blolet.blog.readmodel.BlogReadModels.UserProfileBlogs;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BlogQueryService implements BlogReadUseCase, BlogOwnershipVerifyUseCase {

    private final BlogQueryRepositoryPort repository;

    @Override
    public Blog findById(String id) {
        return repository.findById(id).orElseThrow(BLOG_NOT_FOUND::exception);
    }

    @Override
    public Map<String, UserProfileBlogs> findByUserProfileIds(Set<String> profileIds) {
        return repository.findAllByProfileIdIn(profileIds);
    }

    @Override
    public boolean verifyOwnershipByUserId(String userId, String blogId) {
        var blog = repository.findById(blogId)
                .orElseThrow(BLOG_NOT_FOUND::exception);
        return Objects.equals(userId, blog.getUserId());
    }

    @Override
    public boolean verifyOwnershipByProfileId(String profileId, String blogId) {
        var blog = repository.findById(blogId)
                .orElseThrow(BLOG_NOT_FOUND::exception);
        return Objects.equals(profileId,blog.getProfileId());
    }
}
