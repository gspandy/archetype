#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.biz.service;

import ${package}.model.app.ShiroUser;
import ${package}.model.app.vo.User;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/12/2
 */
public interface UserService {

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 获取当前登录的用户
     *
     * @return
     */
    ShiroUser getShiroUser();

    /**
     * 查找用户
     *
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 更新用户
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 更新用户登录时间
     *
     * @param user
     */
    void updateUserLoginTime(User user);

    /**
     * 保存用户
     *
     * @param user
     */
    void saveUser(User user);

    /**
     * 保存用户和默认角色
     *
     * @param user
     */
    void saveUserWithDefaultRole(User user);

    /**
     * 校验用户名是否存在
     *
     * @param username
     * @return
     */
    boolean existsUsername(String username);

    /**
     * 搜索用户
     *
     * @param pageNum
     * @param pageSize
     * @param username
     * @param nickName
     * @return
     */
    List<User> searchUsers(int pageNum, int pageSize, String username, String nickName);

    /**
     * 更新用户角色
     *
     * @param userId
     * @param roleCodes
     */
    void updateUserRoles(Long userId, String roleCodes);

    /**
     * 更新用户密码
     *
     * @param user
     */
    void updateUserPassword(User user);

    /**
     * 查找所有用户
     *
     * @return
     */
    List<User> findAllUsers();
}
