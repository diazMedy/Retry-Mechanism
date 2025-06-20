package retry.retry.sample.model.response;

import lombok.Getter;
import lombok.Setter;
import retry.retry.sample.model.UserTableDto;

@Getter
@Setter
public class GetDataUserFromApiCalling {

    String message;
    Boolean status;
    UserTableDto data;
}
