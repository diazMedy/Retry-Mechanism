package retry.retry.sample.service;

import retry.retry.sample.model.UserTableDto;
import retry.retry.sample.model.response.MessageModel;

public interface UserTableService {

    MessageModel<UserTableDto> getDataUserById(String idUser);
}
