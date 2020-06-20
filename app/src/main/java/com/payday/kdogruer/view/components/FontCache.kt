package com.payday.kdogruer.view.components

import android.content.Context
import android.graphics.Typeface

import java.util.Hashtable

object FontCache {

    private val fontCache = Hashtable<String, Typeface>()

    operator fun get(name: String, context: Context): Typeface? {
        var tf: Typeface? = fontCache[name]
        if (tf == null) {
            tf = Typeface.createFromAsset(context.assets, name)
            fontCache[name] = tf
        }
        return tf
    }
}