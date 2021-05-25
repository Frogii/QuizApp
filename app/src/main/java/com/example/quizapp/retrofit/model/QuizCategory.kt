package com.example.quizapp.retrofit.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import okhttp3.internal.trimSubstring

@Parcelize
data class QuizCategory(
    val id: Int,
    @SerializedName("name")
    val name: String
) : Parcelable {

    val shortName: String
        get() = if (this.name.contains("Entertainment:")) {
            this.name.trimSubstring(14)
        } else
            this.name

}