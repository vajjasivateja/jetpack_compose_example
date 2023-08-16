package com.example.jetpackcompose.assignment.resourses


import com.google.gson.annotations.SerializedName

data class MainScreenDataClass(
    @SerializedName("component")
    var component: String,
    @SerializedName("ui_components")
    var uiComponents: UiComponents
) {
    data class UiComponents(
        @SerializedName("box")
        var box: Box,
        @SerializedName("login_text_fields")
        var loginTextFields: List<LoginTextField>,
        @SerializedName("main_title")
        var mainTitle: MainTitle,
        @SerializedName("size_box")
        var sizeBox: SizeBox
    ) {
        data class Box(
            @SerializedName("color")
            var color: String,
            @SerializedName("height")
            var height: Int,
            @SerializedName("width")
            var width: Int
        )

        data class LoginTextField(
            @SerializedName("color")
            var color: String,
            @SerializedName("hint_label")
            var hintLabel: String,
            @SerializedName("input_type")
            var inputType: Int,
            @SerializedName("lable")
            var lable: String,
            @SerializedName("leading_icon")
            var leadingIcon: String,
            @SerializedName("margin")
            var margin: Margin
        ) {
            data class Margin(
                @SerializedName("bottom")
                var bottom: Int,
                @SerializedName("left")
                var left: Int,
                @SerializedName("right")
                var right: Int,
                @SerializedName("top")
                var top: Int
            )
        }

        data class MainTitle(
            @SerializedName("color")
            var color: String,
            @SerializedName("height")
            var height: Int?,
            @SerializedName("text")
            var text: String,
            @SerializedName("width")
            var width: Int
        )

        data class SizeBox(
            @SerializedName("height")
            var height: Int,
            @SerializedName("width")
            var width: Int
        )
    }
}