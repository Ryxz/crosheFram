package com.croshe.crosheandroidbase.util;

import android.content.Context;

/**
 * Created by niuyongwei on 17/4/26.
 */

public class ProviderUtil {
    public static String getFileProviderName(Context context){
        return context.getPackageName()+".provider";
    }
}
