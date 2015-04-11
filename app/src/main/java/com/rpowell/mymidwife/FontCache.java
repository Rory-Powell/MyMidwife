/***************************************************************************
 * COPYRIGHT (C) 2015, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/
package com.rpowell.mymidwife;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

public class FontCache {
    private static HashMap<String, Typeface> fontMap = new HashMap<String, Typeface>();

    public static Typeface getFont(Context ctx, String name) {
        if(fontMap.containsKey(name)) {
            return fontMap.get(name);
        } else {
            Typeface typeface = Typeface.createFromAsset(ctx.getAssets(), "fonts/" + name);
            fontMap.put(name, typeface);
            return typeface;
        }
    }
}
