package com.otaku.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统用户
 */
@ApiModel(description="系统用户")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class SysUser implements Serializable {
    @TableId(value = "user_id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Long userId;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @ApiModelProperty(value="用户名")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    @ApiModelProperty(value="手机号")
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="状态  0：禁用   1：正常")
    private Integer status;

    /**
     * 创建者ID
     */
    @TableField(value = "create_user_id")
    @ApiModelProperty(value="创建者ID")
    private Long createUserId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 用户所在的商城Id
     */
    @TableField(value = "shop_id")
    @ApiModelProperty(value="用户所在的商城Id")
    private Long shopId;

    //////////////新增管理员/////////////////////
    @TableField(exist = false)
    @ApiModelProperty("角色id集合")
    private List<Long> roleIdList;

    private static final long serialVersionUID = 1L;
}