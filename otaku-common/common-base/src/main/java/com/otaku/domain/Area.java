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

@ApiModel(description = "area")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "area")
public class Area implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "area_id", type = IdType.INPUT)
    @ApiModelProperty(value = "")
    private Long areaId;
    @TableField(value = "area_name")
    @ApiModelProperty(value = "")
    private String areaName;
    @TableField(value = "parent_id")
    @ApiModelProperty(value = "")
    private Long parentId;
    @TableField(value = "`level`")
    @ApiModelProperty(value = "")
    private Integer level;
}