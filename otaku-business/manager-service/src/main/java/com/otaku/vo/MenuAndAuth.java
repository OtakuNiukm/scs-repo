package com.otaku.vo;

/**
 * @Author: wz296
 * @Description: 菜单和权限
 * @Date: Created in 2024/10/20 下午3:19
 * @FileName: MenuAndAuth
 * @Version: 1.0
 */

import com.otaku.domain.SysMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@ApiModel("菜单和操作权限对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuAndAuth {

    @ApiModelProperty("菜单权限集合")
    private Set<SysMenu> menuList;
    @ApiModelProperty("操作权限集合")
    private Set<String> authorities;
}
