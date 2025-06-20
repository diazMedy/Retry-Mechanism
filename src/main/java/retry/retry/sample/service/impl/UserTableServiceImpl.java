package retry.retry.sample.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import retry.retry.sample.mapper.UserTableMapper;
import retry.retry.sample.model.UserTableDto;
import retry.retry.sample.model.response.MessageModel;
import retry.retry.sample.service.UserTableService;

@Service
@RequiredArgsConstructor
public class UserTableServiceImpl implements UserTableService {

    private final UserTableMapper userTableMapper;


    public MessageModel<UserTableDto> getDataUserById(String idUser){
        UserTableDto result = userTableMapper.getDataUserById(idUser);
        return new MessageModel<>("Success", true, result);
    }
}
