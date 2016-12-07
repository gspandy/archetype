#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.biz.service;

import java.util.List;

/**
 * redis服务接口
 *
 * @author kangyonggan
 * @since 2016/11/30
 */
public interface RedisService {


    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, Object value);

    /**
     * set
     * @param key
     * @param value
     * @param value
     * @return
     */
    boolean set(String key, String value, long timeout);

    /**
     * get
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * delete
     *
     * @param key
     * @return
     */
    Object delete(String key);

    /**
     * incr
     *
     * @param key
     * @return
     */
    long incr(String key);

    /**
     * listLeftPush
     *
     * @param key
     * @return
     */
    long listLeftPush(String key, String url);

    /**
     * listRightPush
     *
     * @param key
     * @return
     */
    long listRightPush(String key, String url);

    /**
     * listRange
     *
     * @param key
     * @return
     */
    List<Object> listRange(String key, long start, long end);

    /**
     * hashSetNx
     *
     * @param hash
     * @param key
     * @param value
     * @return
     */
    boolean hashSetNx(String hash, String key, String value);

    /**
     * hashSize
     *
     * @param hash
     * @return
     */
    long hashSize(String hash);

    /**
     * hashExist
     *
     * @param hash
     * @param key
     * @return
     */
    boolean hashExist(String hash, String key);

}
