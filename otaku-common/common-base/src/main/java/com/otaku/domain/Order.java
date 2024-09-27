package com.otaku.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单表
 */
@ApiModel(description="订单表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`order`")
public class Order implements Serializable {
    /**
     * 订单ID
     */
    @TableId(value = "order_id", type = IdType.INPUT)
    @ApiModelProperty(value="订单ID")
    private Long orderId;

    /**
     * 订购用户ID
     */
    @TableField(value = "open_id")
    @ApiModelProperty(value="订购用户ID")
    private String openId;

    /**
     * 订购流水号
     */
    @TableField(value = "order_number")
    @ApiModelProperty(value="订购流水号")
    private String orderNumber;

    /**
     * 总值
     */
    @TableField(value = "total_money")
    @ApiModelProperty(value="总值")
    private BigDecimal totalMoney;

    /**
     * 实际总值
     */
    @TableField(value = "actual_total")
    @ApiModelProperty(value="实际总值")
    private BigDecimal actualTotal;

    /**
     * 订单备注
     */
    @TableField(value = "remarks")
    @ApiModelProperty(value="订单备注")
    private String remarks;

    /**
     * 订单状态 1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="订单状态 1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败")
    private Integer status;

    /**
     * 配送类型
     */
    @TableField(value = "dvy_type")
    @ApiModelProperty(value="配送类型")
    private String dvyType;

    /**
     * 配送方式ID
     */
    @TableField(value = "dvy_id")
    @ApiModelProperty(value="配送方式ID")
    private Long dvyId;

    /**
     * 物流单号
     */
    @TableField(value = "dvy_flow_id")
    @ApiModelProperty(value="物流单号")
    private String dvyFlowId;

    /**
     * 订单运费
     */
    @TableField(value = "freight_amount")
    @ApiModelProperty(value="订单运费")
    private BigDecimal freightAmount;

    /**
     * 用户订单地址Id
     */
    @TableField(value = "addr_order_id")
    @ApiModelProperty(value="用户订单地址Id")
    private Long addrOrderId;

    /**
     * 订单商品总数
     */
    @TableField(value = "product_nums")
    @ApiModelProperty(value="订单商品总数")
    private Integer productNums;

    /**
     * 订购时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="订购时间")
    private Date createTime;

    /**
     * 订单更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="订单更新时间")
    private Date updateTime;

    /**
     * 付款时间
     */
    @TableField(value = "pay_time")
    @ApiModelProperty(value="付款时间")
    private Date payTime;

    /**
     * 发货时间
     */
    @TableField(value = "dvy_time")
    @ApiModelProperty(value="发货时间")
    private Date dvyTime;

    /**
     * 完成时间
     */
    @TableField(value = "finally_time")
    @ApiModelProperty(value="完成时间")
    private Date finallyTime;

    /**
     * 取消时间
     */
    @TableField(value = "cancel_time")
    @ApiModelProperty(value="取消时间")
    private Date cancelTime;

    /**
     * 是否已经支付，1：已经支付过，0：，没有支付过
     */
    @TableField(value = "is_payed")
    @ApiModelProperty(value="是否已经支付，1：已经支付过，0：，没有支付过")
    private Integer isPayed;

    /**
     * 用户订单删除状态，0：没有删除， 1：回收站， 2：永久删除
     */
    @TableField(value = "delete_status")
    @ApiModelProperty(value="用户订单删除状态，0：没有删除， 1：回收站， 2：永久删除")
    private Integer deleteStatus;

    /**
     * 0:默认,1:在处理,2:处理完成
     */
    @TableField(value = "refund_sts")
    @ApiModelProperty(value="0:默认,1:在处理,2:处理完成")
    private Integer refundSts;

    /**
     * 优惠总额
     */
    @TableField(value = "reduce_amount")
    @ApiModelProperty(value="优惠总额")
    private BigDecimal reduceAmount;

    /**
     * 订单关闭原因 1-超时未支付 2-退款关闭 4-买家取消 15-已通过货到付款交易
     */
    @TableField(value = "close_type")
    @ApiModelProperty(value="订单关闭原因 1-超时未支付 2-退款关闭 4-买家取消 15-已通过货到付款交易")
    private Integer closeType;

    private static final long serialVersionUID = 1L;
}