#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mapper;

import ${package}.model.app.vo.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper extends MyMapper<Menu> {

    /**
     * 查找用户菜单
     *
     * @param userId
     * @param type
     * @return
     */
    List<Menu> selectMenus(@Param("userId") Long userId, @Param("type") String type);

    /**
     * 查找角色菜单
     *
     * @param code
     * @param type
     * @return
     */
    List<Menu> selectMenus4Role(@Param("code") String code, @Param("type") String type);
}