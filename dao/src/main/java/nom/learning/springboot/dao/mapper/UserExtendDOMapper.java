package nom.learning.springboot.dao.mapper;

import java.util.List;
import nom.learning.springboot.dao.model.UserExtendDO;

public interface UserExtendDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserExtendDO record);

    UserExtendDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserExtendDO record);

    /** batch insert **/
    int batchInsert(List<UserExtendDO> list);
}