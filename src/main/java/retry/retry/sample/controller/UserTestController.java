package retry.retry.sample.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import retry.retry.sample.model.UserTableDto;
import retry.retry.sample.model.response.GetDataUserFromApiCalling;
import retry.retry.sample.model.response.MessageModel;
import retry.retry.sample.service.UserTableService;
import retry.retry.sample.util.ApiCommunicationUtil;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class UserTestController {

    private final UserTableService userTableService;
    private final ApiCommunicationUtil apiCommunicationUtil;

    @GetMapping(path = "/find-by-id-user")
    public ResponseEntity<MessageModel<UserTableDto>> getById(
            @RequestParam String idUser
    ) {
        MessageModel<UserTableDto> data = userTableService.getDataUserById(idUser);
        return ResponseEntity.ok().body(data);
    }

    @GetMapping(path = "/calling/find-by-id-user")
    public ResponseEntity<GetDataUserFromApiCalling> getCallingById(
            @RequestParam String idUser
    ) {
        return ResponseEntity.ok().body(apiCommunicationUtil.getUserDataById(idUser));
    }
}
