#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model.constants;

/**
 * 系统常量
 *
 * @author kangyonggan
 * @since 2016/11/30
 */
public interface AppConstants {

    /**
     * 文件根目录在antx中的name
     */
    String FILE_PATH_ROOT = "file.root.path";

    /**
     * 上传目录
     */
    String FILE_UPLOAD_PATH = "upload/";

    /**
     * 是否删除, {0:未删除,1:已删除}
     */
    byte IS_DELETED_YES = 1;
    byte IS_DELETED_NO = 0;

    /**
     * 菜单类型
     */
    String MENU_TYPE_DASHBOARD = "dashboard";
    String MENU_TYPE_ADMIN = "admin";
    String MENU_TYPE_ALL = null;

    /**
     * Shiro常量
     */
    String HASH_ALGORITHM = "SHA-1";
    int HASH_INTERATIONS = 2;
    int SALT_SIZE = 8;

    /**
     * 注册用户时用户有的默认角色(普通就是)
     */
    String DEFAULT_ROLE_CODE = "ROLE_USER";

    /**
     * 默认分页大小
     */
    int PAGE_SIZE = 10;
}
