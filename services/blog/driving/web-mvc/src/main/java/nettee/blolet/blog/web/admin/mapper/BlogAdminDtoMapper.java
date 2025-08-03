package nettee.blolet.blog.web.admin.mapper;

import nettee.blolet.blog.domain.Blog;
import nettee.blolet.blog.web.admin.dto.BlogAdminCommandDto.BlogCreateCommand;
import nettee.blolet.blog.web.admin.dto.BlogAdminCommandDto.BlogCreateResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlogAdminDtoMapper {
    Blog toDomain(BlogCreateCommand dto);

    BlogCreateResponse toResponse(Blog blog);
}
