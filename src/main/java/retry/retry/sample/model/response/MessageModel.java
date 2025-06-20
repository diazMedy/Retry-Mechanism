package retry.retry.sample.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageModel<T> {

    String message;
    Boolean status;
    T data;
}
