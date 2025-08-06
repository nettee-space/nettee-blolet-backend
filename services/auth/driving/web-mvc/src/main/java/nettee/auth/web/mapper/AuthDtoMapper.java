package nettee.auth.web.mapper;

import nettee.auth.domain.User;
import nettee.auth.web.dto.AuthCommandDto.LoginResponse;
import nettee.auth.web.dto.AuthCommandDto.SignUpRequest;
import nettee.blolet.auth.readmodel.AuthCommandModels.LoginTokenModel;
import nettee.blolet.auth.readmodel.AuthCommandModels.SignUpRequestModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthDtoMapper {
    User toDomain(SignUpRequest dto);
    SignUpRequestModel toModel(SignUpRequest signUpRequest);
    LoginResponse toDto(LoginTokenModel signUpRequestModel);
}
