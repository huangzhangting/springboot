package nom.learning.springboot.dao.model;

import java.util.Date;
import lombok.Data;

@Data
public class UserExtendDO {
    /** 自增id **/
    private Integer id;

    /** 创建时间 **/
    private Date gmtCreate;

    /** 修改时间 **/
    private Date gmtUpdate;

    /** 创建人 **/
    private String creator;

    /** 修改人 **/
    private String updator;

    /** 用户id **/
    private Integer userId;

    /** 手机号 **/
    private String mobile;

    /** 注册时间 **/
    private Date registerTime;

    /** 用户类型 0：普通客户 1：微贷员工 **/
    private Byte userType;

    /** 推荐人id **/
    private Integer referrerId;

    /** 推荐人姓名 **/
    private String referrerName;

    /** 推荐人类型 1：BD 2：用户 **/
    private Byte referrerType;

    /** 邀请码 **/
    private String invitationCode;

    /** 所属者id **/
    private Integer ownerId;

    /** 所属者姓名 **/
    private String ownerName;
}