package nettee.blolet.blog.rdb.repository.projection;

public final class BlogQueryProjection {
    private BlogQueryProjection() {}

    public record BlogIdProjection(Long id) { }
}
