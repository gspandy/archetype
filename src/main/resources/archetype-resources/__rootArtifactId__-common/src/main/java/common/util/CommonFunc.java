#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.util;

/**
 * @author kangyonggan
 * @since 2016/12/1
 */
public class CommonFunc {

    /**
     * 实现Oracle的Lpad(string,n,replace)函数
     *
     * @param originalString
     * @param n
     * @param replace
     * @return
     */
    public static String lpad(String originalString, int n, String replace) {
        if (originalString.length() < n) {
            while (originalString.length() < n) {
                originalString = replace + originalString;
            }
        } else {
            originalString = originalString.substring(0, n);
        }

        return originalString;
    }

}
