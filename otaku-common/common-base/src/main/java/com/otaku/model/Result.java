package com.otaku.model;

import com.otaku.constant.BusinessEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 项目统一响应结果对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("项目统一响应结果对象")
public class Result<T> implements Serializable {

    @ApiModelProperty("状态码，默认200")
    private Integer code = 200;

    @ApiModelProperty("响应消息，默认success")
    private String msg = "success";

    @ApiModelProperty("数据")
    private T data;

    /**
     * 响应成功
     * @param data
     * @return
     * @param <T>
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        return result;
    }

    /**
     *  操作失败，自定义响应码
     * @param code
     * @param msg
     * @return
     * @param <T>
     */
    public static <T> Result<T> fail(Integer code, String msg) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    /**
     * 操作失败，从枚举类获取响应码
     * @param businessEnum
     * @return
     * @param <T>
     */
    public static <T> Result<T> fail(BusinessEnum businessEnum) {
        Result<T> result = new Result<>();
        result.setCode(businessEnum.getCode());
        result.setMsg(businessEnum.getDesc());
        result.setData(null);
        return result;
    }

}
