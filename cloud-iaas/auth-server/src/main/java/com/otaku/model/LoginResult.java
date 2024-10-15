package com.otaku.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: wz296
 * @Description: 登录统一结果对象
 * @Date: Created in 2024/10/13 上午11:43
 * @FileName: LoginResult
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("登录统一结果对象")
public class LoginResult {

    @ApiModelProperty("令牌TOKEN")
    private String accessToken;

    @ApiModelProperty("有效时常")
    private Long expireIn;
}
