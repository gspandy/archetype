#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.util;

import ${package}.biz.util.AntxUtil;
import ${package}.model.constants.AppConstants;
import net.coobird.thumbnailator.Thumbnails;

/**
 * @author kangyonggan
 * @since 2016/12/6
 */
public class Images {

    //大Logo
    public static String large(String source) throws Exception {
        return thumbnails(source, "l", 200, 200);
    }

    //中Logo
    public static String middle(String source) throws Exception {
        return thumbnails(source, "m", 128, 128);
    }

    //小Logo
    public static String small(String source) throws Exception {
        return thumbnails(source, "s", 64, 64);
    }

    private static String thumbnails(String source, String suffix, int width, int height) throws Exception {
        String desc = FileUpload.extractFilePath(source, suffix);
        Thumbnails.of(AntxUtil.getProperties(AppConstants.FILE_PATH_ROOT) + source)
                .size(width, height)
                .keepAspectRatio(false)
                .toFile(AntxUtil.getProperties(AppConstants.FILE_PATH_ROOT) + desc);

        return desc;
    }
}
