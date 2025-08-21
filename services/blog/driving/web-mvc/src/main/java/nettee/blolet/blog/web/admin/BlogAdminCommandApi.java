package nettee.blolet.blog.web.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nettee.blolet.blog.application.usecase.BlogCreateUseCase;
import nettee.blolet.blog.web.admin.dto.BlogAdminCommandDto.BlogCreateCommand;
import nettee.blolet.blog.web.admin.dto.BlogAdminCommandDto.BlogCreateResponse;
import nettee.blolet.blog.web.admin.dto.BlogAdminCommandDto.BlogInternalCreateCommand;
import nettee.blolet.blog.web.admin.mapper.BlogAdminDtoMapper;
import nettee.blolet.jwt.filter.annotation.AuthUser;
import nettee.blolet.jwt.filter.annotation.AuthorizedUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BlogAdminCommandApi {

    private final BlogCreateUseCase createUseCase;
    private final BlogAdminDtoMapper mapper;

    @PostMapping("/admin/blogs")
    @ResponseStatus(HttpStatus.CREATED)
    public BlogCreateResponse createByAdmin(
            @RequestBody @Valid BlogCreateCommand requestBody,
            @AuthUser AuthorizedUser user
    ) {
        var blog = mapper.toDomain(user.userId(), requestBody);
        return mapper.toResponse(
                createUseCase.save(blog)
        );
    }

    @PostMapping("/internal/blogs")
    @ResponseStatus(HttpStatus.CREATED)
    public BlogCreateResponse create(
            @RequestBody @Valid BlogInternalCreateCommand requestBody
    ) {
        var blog = mapper.toDomain(requestBody);
        return mapper.toResponse(
                createUseCase.save(blog)
        );
    }
}
