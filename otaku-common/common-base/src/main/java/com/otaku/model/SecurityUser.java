package com.otaku.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @Author: wz296
 * @Description: Security安全框架识别安全用户对象
 * @Date: Created in 2024/10/13 上午10:41
 * @FileName: SecurityUser
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser implements UserDetails {
    // 商城后台管理系统用户相关属性
    private Long userId;
    private String username;
    private String password;
    private Integer status;
    private Long shopId;
    private String loginType;
    private Set<String> perms = new HashSet<>();
    // 商城购物系统用户会员相关属性

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginType + this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.status == 1;
    }

    @Override
    public boolean isEnabled() {
        return this.status == 1;
    }

    public Set<String> getPerms() {
        HashSet<String> finalPermsSet = new HashSet<>();
        // 循环遍历用户权限集合
        perms.forEach(perm -> {
            // 判断是否多权限
            if (perm.contains(",")) {
                // 多权限，根据逗号分割处理
                String[] realPerms = perm.split(",");
                // 添加
                finalPermsSet.addAll(Arrays.asList(realPerms));
            } else {
                finalPermsSet.add(perm);
            }
        });
        return finalPermsSet;
    }
}
