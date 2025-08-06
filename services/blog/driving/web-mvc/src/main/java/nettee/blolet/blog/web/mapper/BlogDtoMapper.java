package nettee.blolet.blog.web.mapper;

import nettee.blolet.blog.domain.Blog;
import nettee.blolet.blog.web.dto.BlogCommandDto.BlogUpdateCommand;
import nettee.blolet.blog.web.dto.BlogCommandDto.BlogUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BlogDtoMapper {
    @Mapping(target = "id", source = "blogId")
    Blog toDomain(String blogId, BlogUpdateCommand dto);
    BlogUpdateResponse toResponse(Blog blog);
}
