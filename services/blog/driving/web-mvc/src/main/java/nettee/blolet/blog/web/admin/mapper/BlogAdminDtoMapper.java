package nettee.blolet.blog.web.admin.mapper;

import nettee.blolet.blog.domain.Blog;
import nettee.blolet.blog.web.admin.dto.BlogAdminCommandDto.BlogCreateCommand;
import nettee.blolet.blog.web.admin.dto.BlogAdminCommandDto.BlogCreateResponse;
import nettee.blolet.blog.web.admin.dto.BlogAdminCommandDto.BlogInternalCreateCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlogAdminDtoMapper {
    Blog toDomain(String userId, BlogCreateCommand dto);
    Blog toDomain(BlogInternalCreateCommand requestBody);

    BlogCreateResponse toResponse(Blog blog);
}
