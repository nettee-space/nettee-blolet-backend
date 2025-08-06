package nettee.article.usecase;

public interface ArticleLikeDeleteUseCase {

    void deleteArticleLike(String userId, String profileId, String articleId);
}
