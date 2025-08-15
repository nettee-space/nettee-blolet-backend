package nettee.blolet.blog.export.client;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import nettee.blolet.blog.export.client.BlogClientDto.BlogOwnershipVerifyResponse;
import nettee.client.request.NetteeRequest;
import nettee.restclient.NetteeClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public final class BlogClient {
    private final NetteeClient customClient;
    private final Map<BlogRequestType, Cache<Object, Object>> cacheMap;

    public BlogClient(NetteeClient customClient) {
        this.customClient = customClient;
        this.cacheMap = new ConcurrentHashMap<>();
    }

    /**
     *
     * @param userId
     * @param blogId
     * @return
     */
    public BlogOwnershipVerifyResponse verifyOwnership(String userId, String blogId) {
        var cache = cacheMap.computeIfAbsent(
                BlogRequestType.VERIFY_OWNERSHIP,
                this::createCacheStorage
        );

        return (BlogOwnershipVerifyResponse) cache.get(
                "userId=%s,blogId=%s".formatted(userId, blogId),
                (key) -> {
                    var request = NetteeRequest.<BlogOwnershipVerifyResponse>builder()
                            .domain("board")
                            .path("/%s/ownership?userId=%s".formatted(blogId, userId))
                            .build();

                    return customClient.get(request);
                }
        );
    }

    /**
     *
     * @param profileId
     * @param blogId
     * @return
     */
    public BlogOwnershipVerifyResponse verifyOwnershipByProfileId(String profileId, String blogId) {
        var cache = cacheMap.computeIfAbsent(
                BlogRequestType.VERIFY_OWNERSHIP,
                this::createCacheStorage
        );

        return (BlogOwnershipVerifyResponse) cache.get(
                "profileId=%s,blogId=%s".formatted(profileId, blogId),
                (key) -> {
                    var request = NetteeRequest.<BlogOwnershipVerifyResponse>builder()
                            .domain("board")
                            .path("/%s/ownership?profileId=%s".formatted(blogId, profileId))
                            .build();

                    return customClient.get(request);
                }
        );
    }

    /**
     * 캐시를 무시하고 조회합니다.
     * 단, 조회한 결과를 새롭게 캐싱합니다.
     *
     * @param userId
     * @param blogId
     * @return
     */
    public BlogOwnershipVerifyResponse verifyOwnershipFresh(String userId, String blogId) {
        var cache = cacheMap.computeIfAbsent(
                BlogRequestType.VERIFY_OWNERSHIP,
                this::createCacheStorage
        );
        var request = NetteeRequest.<BlogOwnershipVerifyResponse>builder()
                .domain("board")
                .path("/%s/ownership?userId=%s".formatted(blogId, userId))
                .build();

        var response = customClient.get(request);
        cache.put("userId=%s,blogId=%s".formatted(userId, blogId), response);

        return response;
    }

    /**
     * 캐시를 무시하고 조회합니다.
     * 단, 조회한 결과를 새롭게 캐싱합니다.
     *
     * @param profileId
     * @param blogId
     * @return
     */
    public BlogOwnershipVerifyResponse verifyOwnershipByProfileIdFresh(String profileId, String blogId) {
        var cache = cacheMap.computeIfAbsent(
                BlogRequestType.VERIFY_OWNERSHIP,
                this::createCacheStorage
        );
        var request = NetteeRequest.<BlogOwnershipVerifyResponse>builder()
                .domain("board")
                .path("/%s/ownership?profileId=%s".formatted(blogId, profileId))
                .build();

        var response = customClient.get(request);
        cache.put("profileId=%s,blogId=%s".formatted(profileId, blogId), response);

        return response;
    }

    private Cache<Object, Object> createCacheStorage(BlogRequestType type) {
        return cacheMap.computeIfAbsent(
                type,
                ignore -> Caffeine.newBuilder()
                        .expireAfterAccess(6000, TimeUnit.SECONDS)
                        .build()
        );
    }

    private enum BlogRequestType {
        VERIFY_OWNERSHIP,
    }
}

// 캐싱 시간을 어떤 조건에서 어떻게 수정하도록 허용할 것인지 고민 필요함. (모놀리스 및 MSA에서 동일한 빈 등록 방식 유지하려면.)