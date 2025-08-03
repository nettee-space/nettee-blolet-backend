package nettee.blolet.blog.web.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nettee.blolet.blog.application.usecase.BlogCreateUseCase;
import nettee.blolet.blog.web.admin.dto.BlogAdminCommandDto.BlogCreateCommand;
import nettee.blolet.blog.web.admin.dto.BlogAdminCommandDto.BlogCreateResponse;
import nettee.blolet.blog.web.admin.mapper.BlogAdminDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/blogs")
public class BlogAdminCommandApi {

    private final BlogCreateUseCase createUseCase;
    private final BlogAdminDtoMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogCreateResponse create(@RequestBody @Valid BlogCreateCommand requestBody) {
        var blog = mapper.toDomain(requestBody);
        return mapper.toResponse(
                createUseCase.save(blog)
        );
    }
}
