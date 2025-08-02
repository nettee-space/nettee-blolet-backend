package nettee.blolet.blog.application.service;

import lombok.RequiredArgsConstructor;
import nettee.blolet.blog.application.port.BlogSubscriptionCommandRepositoryPort;
import nettee.blolet.blog.application.usecase.newsletter.BlogNewsletterSubscriptionUseCase;
import nettee.blolet.blog.application.usecase.newsletter.BlogNewsletterUnsubscriptionUseCase;
import nettee.blolet.blog.application.usecase.subscription.BlogSubscriptionUseCase;
import nettee.blolet.blog.application.usecase.subscription.BlogUnsubscriptionUseCase;
import nettee.blolet.blog.application.usecase.subscription.data.SubscriptionStats;
import nettee.blolet.blog.domain.BlogSubscription;
import org.springframework.stereotype.Service;

import static nettee.blolet.blog.exception.BlogErrorCode.ALREADY_SUBSCRIBED_BLOG;
import static nettee.blolet.blog.exception.BlogErrorCode.ALREADY_SUBSCRIBED_BLOG_NEWSLETTER;
import static nettee.blolet.blog.exception.BlogErrorCode.UNSUBSCRIBED_BLOG;
import static nettee.blolet.blog.exception.BlogErrorCode.UNSUBSCRIBED_BLOG_NEWSLETTER;

@Service
@RequiredArgsConstructor
public class BlogSubscriptionCommandService
        implements BlogSubscriptionUseCase,
        BlogUnsubscriptionUseCase,
        BlogNewsletterSubscriptionUseCase,
        BlogNewsletterUnsubscriptionUseCase {

    private final BlogSubscriptionCommandRepositoryPort commandRepository;

    @Override
    public SubscriptionStats subscribeBlog(String userId, String blogId) {
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

        // 저장 후 통계 조회
        return subscriptionStats(userId, blogId);
    }

    @Override
    public SubscriptionStats unsubscribeBlog(String userId, String blogId) {
        BlogSubscription subscription = commandRepository.findByUserIdAndBlogId(userId, blogId)
                .orElseThrow(UNSUBSCRIBED_BLOG::exception);
        commandRepository.deleteById(subscription.getId());

        return subscriptionStats(userId, blogId);
    }

    @Override
    public SubscriptionStats subscribeBlogNewsletter(String userId, String blogId) {
        // Prerequisite: 블로그를 구독 중이어야 뉴스레터를 구독할 수 있음.
        BlogSubscription blogSubscription = commandRepository.findByUserIdAndBlogId(userId, blogId)
                .orElseThrow(UNSUBSCRIBED_BLOG::exception);

        // Exception: 이미 뉴스레터를 구독 중
        if (blogSubscription.getEmailAllowed()) {
            throw ALREADY_SUBSCRIBED_BLOG_NEWSLETTER.exception();
        }

        blogSubscription.subscribeNewsletter();
        commandRepository.update(blogSubscription);

        return subscriptionStats(userId, blogId);
    }

    @Override
    public SubscriptionStats unsubscribeBlogNewsletter(String userId, String blogId) {
        // Prerequisite: 이미 블로그가 구독취소되어 있다면 뉴스레터도 취소된 것으로 취급됨.
        BlogSubscription blogSubscription = commandRepository.findByUserIdAndBlogId(userId, blogId)
                .orElseThrow(UNSUBSCRIBED_BLOG::exception);

        // Exception: 이미 뉴스레터 구독 취소
        if (!blogSubscription.getEmailAllowed()) {
            throw UNSUBSCRIBED_BLOG_NEWSLETTER.exception();
        }

        blogSubscription.unsubscribeNewsletter();
        commandRepository.update(blogSubscription);

        return subscriptionStats(userId, blogId);
    }

    private SubscriptionStats subscriptionStats(String userId, String blogId) {
        int userCount = commandRepository.countByUserId(userId);
        int blogCount = commandRepository.countByBlogId(blogId);

        return SubscriptionStats.builder()
                .userSubscriptionCount(userCount)
                .blogTotalSubscriberCount(blogCount)
                .build();
    }
}
