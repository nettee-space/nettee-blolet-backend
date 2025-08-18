package nettee.blolet.blog.export.client.webmvc;

import nettee.blolet.blog.export.client.api.BlogClient;
import nettee.restclient.NetteeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlogInternalClientConfig {
    @Bean
    public BlogClient blogClient(NetteeClient customClient) {
        return new RestBlogClient(customClient);
    }
}
