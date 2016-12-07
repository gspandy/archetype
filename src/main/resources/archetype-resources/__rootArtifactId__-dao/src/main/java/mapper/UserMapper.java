#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mapper;

import ${package}.model.app.vo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends MyMapper<User> {

    /**
     * 保存用户角色
     *
     * @param userId
     * @param roleCodes
     */
    void insertUserRoles(@Param("userId") Long userId, @Param("roleCodes") List<String> roleCodes);
}