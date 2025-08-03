package nettee.blolet.blog.web.mapper;

import nettee.blolet.blog.domain.Blog;
import nettee.blolet.blog.web.dto.BlogCommandDto.BlogUpdateCommand;
import nettee.blolet.blog.web.dto.BlogCommandDto.BlogUpdateResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlogDtoMapper {
    Blog toDomain(String blogId, BlogUpdateCommand dto);
    BlogUpdateResponse toResponse(Blog blog);
}
