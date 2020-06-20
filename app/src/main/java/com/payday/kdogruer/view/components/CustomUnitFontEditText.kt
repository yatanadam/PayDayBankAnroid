package com.payday.kdogruer.view.components

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import com.payday.kdogruer.R


/**
 * Created by Kaan on 10.04.2017.
 */

class CustomUnitFontEditText : AppCompatEditText {
    private var textFont: String? = null
    var required: Boolean = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setCustomFont(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setCustomFont(context, attrs)
    }


    private fun setCustomFont(ctx: Context, attrs: AttributeSet) {
        val a = ctx.obtainStyledAttributes(attrs, R.styleable.CustomUnitFontEditText)
        textFont = a.getString(R.styleable.CustomUnitFontEditText_customEditTextFont)
        required = a.getBoolean(R.styleable.CustomUnitFontEditText_required, false)
        setText(this.text, BufferType.SPANNABLE)
        setCustomFont(ctx, textFont)
        a.recycle()
    }

    fun setCustomFont(ctx: Context, font: String?): Boolean {
        var typeface: Typeface?
        try {
            typeface = FontCache[font!!, ctx]
        } catch (e: Exception) {
            Log.e("CustomUnitFontEditText", e.toString())
            return false
        }

        setTypeface(typeface)
        return true
    }
}