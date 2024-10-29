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
 * 购物车
 */
@ApiModel(description = "购物车")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "basket")
public class Basket implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "basket_id", type = IdType.INPUT)
    @ApiModelProperty(value = "主键")
    private Long basketId;
    /**
     * 店铺ID
     */
    @TableField(value = "shop_id")
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    /**
     * 产品ID
     */
    @TableField(value = "prod_id")
    @ApiModelProperty(value = "产品ID")
    private Long prodId;
    /**
     * SkuID
     */
    @TableField(value = "sku_id")
    @ApiModelProperty(value = "SkuID")
    private Long skuId;
    /**
     * 用户ID
     */
    @TableField(value = "open_id")
    @ApiModelProperty(value = "用户ID")
    private String openId;
    /**
     * 购物车产品个数
     */
    @TableField(value = "prod_count")
    @ApiModelProperty(value = "购物车产品个数")
    private Integer prodCount;
    /**
     * 购物时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "购物时间")
    private Date createTime;
}