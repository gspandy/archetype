#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.biz.service.impl;

import com.github.pagehelper.PageHelper;
import ${package}.biz.service.UserService;
import ${package}.common.util.DateUtils;
import ${package}.common.util.Digests;
import ${package}.common.util.Encodes;
import ${package}.mapper.RoleMapper;
import ${package}.mapper.UserMapper;
import ${package}.model.app.ShiroUser;
import ${package}.model.app.vo.User;
import ${package}.model.constants.AppConstants;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/12/2
 */
@Service
@Log4j2
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User findUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);

        return super.selectOne(user);
    }

    @Override
    public ShiroUser getShiroUser() {
        return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    }

    @Override
    public User findUserById(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    public void updateUser(User user) {
        super.updateByPrimaryKeySelective(user);
    }

    @Override
    public void updateUserLoginTime(User user) {
        user = findUserByUsername(user.getUsername());
        if (user != null) {
            user.setLoginTime(DateUtils.getNow());
            updateByPrimaryKeySelective(user);
        } else {
            log.error("更新用户登录时间时，发现用户不存在");
        }
    }

    @Override
    public void saveUser(User user) {
        entryptPassword(user);
        super.insertSelective(user);
    }

    @Override
    public void saveUserWithDefaultRole(User user) {
        saveUser(user);
        saveUserRoles(user.getId(), AppConstants.DEFAULT_ROLE_CODE);
    }

    @Override
    public boolean existsUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return userMapper.selectCount(user) == 1;
    }

    @Override
    public List<User> searchUsers(int pageNum, int pageSize, String username, String nickName) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotEmpty(username)) {
            criteria.andLike("username", String.format("%%%s%%", username));
        }

        if (StringUtils.isNotEmpty(nickName)) {
            criteria.andLike("nickName", String.format("%%%s%%", nickName));
        }

        example.setOrderByClause("id desc");

        PageHelper.startPage(pageNum, pageSize);
        return super.selectByExample(example);
    }

    @Override
    public void updateUserRoles(Long userId, String roleCodes) {
        roleMapper.deleteAllRolesByUserId(userId);

        if (StringUtils.isNotEmpty(roleCodes)) {
            saveUserRoles(userId, roleCodes);
        }
    }

    @Override
    public void updateUserPassword(User user) {
        entryptPassword(user);
        updateUser(user);
    }

    @Override
    public List<User> findAllUsers() {
        User user = new User();
        user.setIsDeleted(AppConstants.IS_DELETED_NO);

        return super.select(user);
    }

    /**
     * 批量保存用户角色
     *
     * @param userId
     * @param roleCodes
     */
    private void saveUserRoles(Long userId, String roleCodes) {
        userMapper.insertUserRoles(userId, Arrays.asList(roleCodes.split(",")));
    }

    /**
     * 设定安全的密码，生成随机的salt并经过N次 sha-1 hash
     *
     * @param user
     */
    public void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(AppConstants.SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, AppConstants.HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }

}
