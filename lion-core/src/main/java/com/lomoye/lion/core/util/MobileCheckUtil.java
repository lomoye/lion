package com.lomoye.lion.core.util;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by 6 on 2016/1/28.
 */
public class MobileCheckUtil {
    private static Pattern pattern = Pattern.compile("1[^012][0-9]{9}");

    public static List<String> isEffectivePhone(Collection<String> mobiles) {
        List<String> errorMobiles = new ArrayList<>();

        for (String mobile : mobiles) {
            if (!isEffectivePhone(mobile)) {
                errorMobiles.add(mobile);
            }
        }
        return errorMobiles;
    }

    public static boolean isEffectivePhone(String mobile) {
        if (Strings.isNullOrEmpty(mobile)) {
            return false;
        }
        mobile = mobile.trim();
        return mobile.length() == 11 && pattern.matcher(mobile).matches();
    }
}
