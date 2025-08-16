package nettee.blolet.blog.export.client.webmvc;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import nettee.blolet.blog.export.client.api.BlogClient;
import nettee.blolet.blog.export.client.api.BlogClientDto.BlogOwnershipVerifyResponse;
import nettee.client.request.NetteeRequest;
import nettee.restclient.NetteeClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public final class RestBlogClient implements BlogClient {
    private final NetteeClient customClient;
    private final Map<BlogRequestType, Cache<Object, Object>> cacheMap;

    public RestBlogClient(NetteeClient customClient) {
        this.customClient = customClient;
        this.cacheMap = new ConcurrentHashMap<>();
    }

    /**
     * 사용자가 블로그의 소유자인지 확인합니다.
     * 조회 결과를 10분 동안 캐싱합니다.
     *
     * @param userId
     * @param blogId
     * @return { isOwner : Boolean }
     */
    @Override
    public BlogOwnershipVerifyResponse verifyOwnership(String userId, String blogId) {
        var cache = cacheMap.computeIfAbsent(
                BlogRequestType.VERIFY_OWNERSHIP,
                this::createCacheStorage
        );

        return (BlogOwnershipVerifyResponse) cache.get(
                "userId=%s,blogId=%s".formatted(userId, blogId),
                (key) -> {
                    var request = generateBlogOwnershipRequest(userId, blogId);
                    return customClient.get(request);
                }
        );
    }

    /**
     * 사용자가 블로그의 소유자인지 확인합니다.
     * 조회 결과를 10분 동안 캐싱됩니다.
     *
     * @param profileId
     * @param blogId
     * @return { isOwner : Boolean }
     */
    @Override
    public BlogOwnershipVerifyResponse verifyOwnershipByProfileId(String profileId, String blogId) {
        var cache = cacheMap.computeIfAbsent(
                BlogRequestType.VERIFY_OWNERSHIP,
                this::createCacheStorage
        );

        return (BlogOwnershipVerifyResponse) cache.get(
                "profileId=%s,blogId=%s".formatted(profileId, blogId),
                (key) -> {
                    var request = generateBlogOwnershipRequestByProfileId(profileId, blogId);
                    return customClient.get(request);
                }
        );
    }

    /**
     * 기존 캐시를 무시하고 조회합니다.
     * 이 메서드는 캐시를 재사용하지 않지만, 캐시를 갱신합니다.
     *
     * @param userId
     * @param blogId
     * @return { isOwner : Boolean }
     */
    @Override
    public BlogOwnershipVerifyResponse verifyOwnershipFresh(String userId, String blogId) {
        var cache = cacheMap.computeIfAbsent(
                BlogRequestType.VERIFY_OWNERSHIP,
                this::createCacheStorage
        );
        var request = generateBlogOwnershipRequest(userId, blogId);
        var response = customClient.get(request);
        cache.put("userId=%s,blogId=%s".formatted(userId, blogId), response);

        return response;
    }

    /**
     * 기존 캐시를 무시하고 조회합니다.
     * 이 메서드는 캐시를 재사용하지 않지만, 캐시를 갱신합니다.
     *
     * @param profileId
     * @param blogId
     * @return { isOwner : Boolean }
     */
    @Override
    public BlogOwnershipVerifyResponse verifyOwnershipByProfileIdFresh(String profileId, String blogId) {
        var cache = cacheMap.computeIfAbsent(
                BlogRequestType.VERIFY_OWNERSHIP,
                this::createCacheStorage
        );
        var request = generateBlogOwnershipRequestByProfileId(profileId, blogId);
        var response = customClient.get(request);
        cache.put("profileId=%s,blogId=%s".formatted(profileId, blogId), response);

        return response;
    }

    private Cache<Object, Object> createCacheStorage(BlogRequestType type) {
        return Caffeine.newBuilder()
                .expireAfterAccess(6000, TimeUnit.SECONDS)
                .build();
    }

    private NetteeRequest<BlogOwnershipVerifyResponse> generateBlogOwnershipRequest(String userId, String blogId) {
        return NetteeRequest.<BlogOwnershipVerifyResponse>builder()
                .domain("blog")
                .path("/blogs/{id}/ownership?userId=%s".formatted(userId))
                .uriVariables(new Object[] { blogId })
                .responseType(BlogOwnershipVerifyResponse.class)
                .build();
    }

    private NetteeRequest<BlogOwnershipVerifyResponse> generateBlogOwnershipRequestByProfileId(
            String profileId, String blogId) {
        return NetteeRequest.<BlogOwnershipVerifyResponse>builder()
                .domain("blog")
                .path("/blogs/{id}/ownership?profileId=%s".formatted(profileId))
                .uriVariables(new Object[] { blogId })
                .responseType(BlogOwnershipVerifyResponse.class)
                .build();
    }

    private enum BlogRequestType {
        VERIFY_OWNERSHIP,
    }
}

// 캐싱 시간을 어떤 조건에서 어떻게 수정하도록 허용할 것인지 고민 필요함. (모놀리스 및 MSA에서 동일한 빈 등록 방식 유지하려면.)