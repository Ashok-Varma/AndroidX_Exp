package com.ashokvarma.androidx.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * Class description
 *
 * @author ashok
 * @version 1.0
 * @since 17/06/18
 */
public class ResourcesUtils {
    private static final String LAYOUT_RESOURCE = "layout";

    private static final String DRAWABLE_RESOURCE = "drawable";

    private static final String MIPMAP_RESOURCE = "mipmap";

    private static final String STRING_RESOURCE = "string";

    private static final String ID_RESOURCE = "id";

    private static final String COLOR_RESOURCE = "color";

    private static final String ANIM_RESOURCE = "anim";

    private static final String STYLEABLE_RESOURCE = "styleable";

    private static final String STYLE_RESOURCE = "style";

    private static final String RAW_RESOURCE = "raw";

    private static final String XML_RESOURCE = "xml";

    private static final String MENU_RESROUCE = "menu";

    private static final String DIMEN_RESOURCE = "dimen";

    private static final String ARRAY_RECOURCE = "array";

    /**
     * 上下文引用
     */
    private static Context mContext = null;

    /**
     * 初始化方法,使用之前必须调用<br>
     * 一般资源的获取是全局的，可使用ApplicationContext来初始化
     *
     * @param context 上下文引用
     */
    public static void initContext(Context context) {
        if (mContext == null)
            mContext = context;
    }

    /**
     * 获得String类型ID
     *
     * @param name 字符窜资源名
     * @return 返回字符窜资源的ID
     */
    public static int getStringResIdentifier(String name) {
        return getResIdentifier(STRING_RESOURCE, name);
    }

    /**
     * 获取Drawable类型ID
     *
     * @param name drawable资源名
     * @return 返回drawable资源的ID
     */
    public static int getDrawResIdentifier(String name) {
        return getResIdentifier(DRAWABLE_RESOURCE, name);
    }

    /**
     * 获取mipmap类型的ID
     *
     * @param name mipmap资源名
     * @return 返回mipmap资源ID
     */
    public static int getMipmapIdentifier(String name) {
        return getResIdentifier(MIPMAP_RESOURCE, name);
    }

    /**
     * 获取ID类型
     *
     * @param name ID资源名
     * @return 返回ID资源ID
     */
    public static int getIDResIdentifier(String name) {
        return getResIdentifier(ID_RESOURCE, name);
    }

    /**
     * 获取Layout类型
     *
     * @param name layout资源名
     * @return 返回layout资源id
     */
    public static int getLayoutResIdentifier(String name) {
        return getResIdentifier(LAYOUT_RESOURCE, name);
    }

    /**
     * 获取Color类型
     *
     * @param name color资源名
     * @return 返回color资源id
     */
    public static int getColorResIdentifier(String name) {
        return getResIdentifier(COLOR_RESOURCE, name);
    }

    /**
     * 获取Anim类型
     *
     * @param name anim资源名
     * @return 返回anim资源id
     */
    public static int getAnimResIdentifier(String name) {
        return getResIdentifier(ANIM_RESOURCE, name);
    }

    /**
     * 获取Styleable类型
     *
     * @param name styleable资源名
     * @return 返回styleable资源id
     */
    public static int getStyleableResIdentifier(String name) {
        return getResIdentifier(STYLEABLE_RESOURCE, name);
    }

    /**
     * 获取style类型
     *
     * @param name style资源名
     * @return 返回style资源id
     */
    public static int getStyleResIdentifier(String name) {
        return getResIdentifier(STYLE_RESOURCE, name);
    }

    /**
     * 获取raw类型
     *
     * @param name raw资源名
     * @return 返回raw资源id
     */
    public static int getRawResIdentifier(String name) {
        return getResIdentifier(RAW_RESOURCE, name);
    }

    /**
     * 获取XML类型
     *
     * @param name XML资源名
     * @return 返回XML资源id
     */
    public static int getXmlResIdentifier(String name) {
        return getResIdentifier(XML_RESOURCE, name);
    }

    /**
     * 获取dimen类型
     *
     * @param name dimen资源名
     * @return 返回dimen资源id
     */
    public static int getDimenIdentifier(String name) {
        return getResIdentifier(DIMEN_RESOURCE, name);
    }

    /**
     * 获取array类型
     *
     * @param name dimen资源名
     * @return 返回dimen资源id
     */
    public static int getArrayIdentifier(String name) {
        return getResIdentifier(ARRAY_RECOURCE, name);
    }

    /**
     * 获取menu类型
     *
     * @param name menu资源名
     * @return 返回menu资源id
     */
    public static int getMenuResIdentifier(String name) {
        return getResIdentifier(MENU_RESROUCE, name);
    }

    private static int getResIdentifier(String type, String name) {
//		return mContext.getResources().getIdentifier
//				(mContext.getPackageName() + ":" + type + "/" + name, null, null);
        return mContext.getResources().getIdentifier(name, type, mContext.getPackageName());
    }

    /**
     * 根据版本号获取颜色资源
     *
     * @param colorResID color资源ID
     * @return 返回color资源的值
     */
    @SuppressWarnings("deprecation")
    public static int getColor(int colorResID) {
        if (Build.VERSION.SDK_INT < 23) {
            return mContext.getResources().getColor(colorResID);
        } else {
            return mContext.getColor(colorResID);
        }
    }

    /**
     * 根据版本号获取Drawable资源
     *
     * @param drawableResID drawable资源ID
     * @return 返回drawable资源
     */
    @SuppressWarnings("deprecation")
    public static Drawable getDrawable(int drawableResID) {
        if (Build.VERSION.SDK_INT < 21) {
            return mContext.getResources().getDrawable(drawableResID);
        } else {
            return mContext.getDrawable(drawableResID);
        }
    }


}
