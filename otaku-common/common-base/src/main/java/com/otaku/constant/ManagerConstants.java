package com.otaku.constant;

/**
 * @Author: wz296
 * @Description: 系统业务模块常量类
 * @Date: Created in 2024/10/22 下午4:14
 * @FileName: ManagerConstants
 * @Version: 1.0
 */
public interface ManagerConstants {

    /**
     * 系统所有角色数据存放到Redis中的key
     */
    String SYS_ALL_ROLE_KEY = "'roles'";

    /**
     * 系统所有菜单数据存放到Redis中的key
     */
    String SYS_ALL_MENU_KEY = "'menus'";
}
