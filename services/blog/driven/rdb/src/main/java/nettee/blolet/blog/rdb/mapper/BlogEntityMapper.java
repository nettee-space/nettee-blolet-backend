package nettee.blolet.blog.rdb.mapper;

import nettee.blolet.blog.domain.Blog;
import nettee.blolet.blog.rdb.entity.BlogEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlogEntityMapper {
    BlogEntity toEntity(Blog domain);
    Blog toDomain(BlogEntity entity);
}
