/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.payday.kdogruer.databinding

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.payday.kdogruer.view.components.CustomUnitFontButton
import com.payday.kdogruer.view.components.CustomUnitFontEditText
import com.payday.kdogruer.R

/**
 * a parameter for parts step validation.
 */
object EditTextRequieredValidator {
    @BindingAdapter("validateFields")
    @JvmStatic
    fun setResource(containerLayout: ViewGroup, button: CustomUnitFontButton) {

        var isValid = true
        for (i in 0 until containerLayout.childCount) {

            if (containerLayout.getChildAt(i) is TextInputLayout) {
                for (j in 0 until (containerLayout.getChildAt(i) as TextInputLayout).childCount) {
                    if ((containerLayout.getChildAt(i) as TextInputLayout).getChildAt(j) is FrameLayout) {
                        for (k in 0 until ((containerLayout.getChildAt(i) as TextInputLayout).getChildAt(j) as FrameLayout).childCount) {
                            if (((containerLayout.getChildAt(i) as TextInputLayout).getChildAt(j) as FrameLayout).getChildAt(k) is CustomUnitFontEditText &&
                                    (((containerLayout.getChildAt(i) as TextInputLayout).getChildAt(j) as FrameLayout).getChildAt(k) as CustomUnitFontEditText).error != null) {
                                isValid = false
                            }
                        }
                    }
                }
            }
        }

        if (!isValid)
            button.setBackgroundColor(button.resources.getColor(R.color.colorDarkRed))
        else
            button.setBackgroundColor(button.resources.getColor(R.color.colorAccent))
    }
}
