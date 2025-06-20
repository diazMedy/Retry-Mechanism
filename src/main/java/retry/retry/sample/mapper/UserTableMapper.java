package retry.retry.sample.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import retry.retry.sample.model.UserTableDto;

@Mapper
public interface UserTableMapper {

    @Select("SELECT * FROM user_schema.user_table WHERE id_user=#{idUser}")
    UserTableDto getDataUserById(@Param("idUser") String idUser);

}
