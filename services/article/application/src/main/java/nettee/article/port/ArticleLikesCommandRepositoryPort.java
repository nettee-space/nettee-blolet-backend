package nettee.article.port;

public interface ArticleLikesCommandRepositoryPort {

    void save(String userId, String profileId, String articleId);

    void delete(String userId, String profileId, String articleId);
}
