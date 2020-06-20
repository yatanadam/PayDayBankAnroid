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

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat

/**
 * Data Binding adapters specific to the app.
 */
object BindingAdapters {
    @BindingAdapter("visibleGone")
    @JvmStatic
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @BindingAdapter("latitude", "longitude")
    @JvmStatic
    fun setLatLang(view: TextView, latitude: Double?, longitude: Double?) {

        if (latitude != null || longitude != null) {
            view.text = "$latitude , $longitude"
        }
    }

    @BindingAdapter("stringDate", "formatDate", "targetDate")
    @JvmStatic
    fun setDate(view: TextView, stringDate: String?, formatDate: String?, targetDate: String?) {

        if (stringDate != null || formatDate != null || targetDate != null) {
            SimpleDateFormat(formatDate).apply {
                view.text = SimpleDateFormat(targetDate).format(this.parse(stringDate))
            }
        }
    }

    @BindingAdapter("setPrice")
    @JvmStatic
    fun setPrice(view: TextView, price: String?) {

        if (price != null) {
            view.text = "${String.format("%.2f", price.toDouble())} TL"
        }
    }

    @BindingAdapter("setPercent")
    @JvmStatic
    fun setPercent(view: TextView, percent: String?) {

        if (percent != null) {
            view.text = "% ${percent.toDouble()}"
        }
    }
}
