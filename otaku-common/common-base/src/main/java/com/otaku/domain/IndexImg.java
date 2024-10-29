package com.otaku.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 主页轮播图
 */
@ApiModel(description = "主页轮播图")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "index_img")
public class IndexImg implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "img_id", type = IdType.INPUT)
    @ApiModelProperty(value = "主键")
    private Long imgId;
    /**
     * 店铺ID
     */
    @TableField(value = "shop_id")
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    /**
     * 图片
     */
    @TableField(value = "img_url")
    @ApiModelProperty(value = "图片")
    private String imgUrl;
    /**
     * 说明文字,描述
     */
    @TableField(value = "des")
    @ApiModelProperty(value = "说明文字,描述")
    private String des;
    /**
     * 标题
     */
    @TableField(value = "title")
    @ApiModelProperty(value = "标题")
    private String title;
    /**
     * 链接
     */
    @TableField(value = "link")
    @ApiModelProperty(value = "链接")
    private String link;
    /**
     * 状态
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "状态")
    private Integer status;
    /**
     * 顺序
     */
    @TableField(value = "seq")
    @ApiModelProperty(value = "顺序")
    private Integer seq;
    /**
     * 上传时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "上传时间")
    private Date createTime;
    /**
     * 关联
     */
    @TableField(value = "prod_id")
    @ApiModelProperty(value = "关联")
    private Long prodId;
    /**
     * 关联商品类型，0已关联商品,-1未关联商品
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value = "关联商品类型，0已关联商品,-1未关联商品")
    private Integer type;
}