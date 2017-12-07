package nom.learning.springboot.dao.slave.mapper;

import nom.learning.springboot.dao.model.UserExtendDO;

public interface SlaveUserExtendDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserExtendDO record);

    UserExtendDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserExtendDO record);

}