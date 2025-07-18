package nettee.blolet.blog.domain;

import java.time.Instant;

public class Blog {
    private String id;
    private String userId;
    private String name;
    private String url;
    private Instant createdAt;
    private Instant updatedAt;

    public Blog(String userId, String name, String url) {
        this.userId = userId;
        this.name = name;
        this.url = url;
    }
}
