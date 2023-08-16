package com.example.jetpackcompose.assignment.resourses


import com.google.gson.annotations.SerializedName

data class LoginUIDataClass(
    @SerializedName("screen_name") var screenName: String?,
    @SerializedName("content") var content: List<Content?>?
) {
    data class Content(
        @SerializedName("type") var type: String?,
        @SerializedName("attributes") var attributes: Attributes?,
        @SerializedName("components") var components: List<Component?>?
    ) {
        data class Attributes(
            @SerializedName("text") var text: String?,
            @SerializedName("color") var color: String?,
            @SerializedName("padding") var padding: Padding?,
            @SerializedName("margin") var margin: Margin?,
            @SerializedName("width") var width: Int?,
            @SerializedName("height") var height: Int?,
            @SerializedName("background_color") var backgroundColor: String?
        ) {
            data class Padding(
                @SerializedName("top") var top: Int?,
                @SerializedName("bottom") var bottom: Int?,
                @SerializedName("left") var left: Int?,
                @SerializedName("right") var right: Int?
            )

            data class Margin(
                @SerializedName("top") var top: Int?,
                @SerializedName("bottom") var bottom: Int?,
                @SerializedName("left") var left: Int?,
                @SerializedName("right") var right: Int?
            )
        }

        data class Component(
            @SerializedName("label") var label: String?,
            @SerializedName("hint_label") var hintLabel: String?,
            @SerializedName("color") var color: String?,
            @SerializedName("hint_color") var hintColor: String?,
            @SerializedName("input_type") var inputType: Int?
        )
    }
}