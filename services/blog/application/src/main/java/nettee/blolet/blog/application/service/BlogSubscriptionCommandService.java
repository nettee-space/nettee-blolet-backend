package nettee.blolet.blog.application.service;

import lombok.RequiredArgsConstructor;
import nettee.blolet.blog.application.port.BlogSubscriptionCommandRepositoryPort;
import nettee.blolet.blog.application.usecase.subscription.BlogSubscriptionUseCase;
import nettee.blolet.blog.application.usecase.subscription.BlogUnsubscriptionUseCase;
import nettee.blolet.blog.domain.BlogSubscription;
import org.springframework.stereotype.Service;

import static nettee.blolet.blog.exception.BlogErrorCode.ALREADY_SUBSCRIBED_BLOG;

@Service
@RequiredArgsConstructor
public class BlogSubscriptionCommandService implements BlogSubscriptionUseCase, BlogUnsubscriptionUseCase {

    private final BlogSubscriptionCommandRepositoryPort commandRepository;

    @Override
    public void subscribeBlog(String userId, String blogId) {
        boolean exists = commandRepository.existsByUserIdAndBlogId(userId, blogId);
        if(exists) {
            throw ALREADY_SUBSCRIBED_BLOG.exception();
        }

        BlogSubscription subscription = BlogSubscription.builder()
                .userId(userId)
                .blogId(blogId)
                .emailAllowed(false)
                .notificationAllowed(false)
                .build();

        commandRepository.save(subscription);
    }

    @Override
    public void unsubscribeBlog(String username, String blogId) {
        throw new Error("Not implemented yet");
    }
}
