package com.example.jetpackcompose.assignment

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.assignment.resourses.LoginUIDataClass
import com.example.jetpackcompose.assignment.resourses.MainScreenDataClass
import com.example.jetpackcompose.assignment.ui.theme.JetpackComposeAssignmentTheme
import com.google.gson.Gson
import java.io.InputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAssignmentTheme {

// this one is Simple json file, UNCOMMENT below lines i.e 32, 33, 34 and COMMENT line number 36, 37, 38 to see different styles of json ui binding
//                val jsonString = readJsonFileFromResources(this, R.raw.main_screen)
//                val json = Gson().fromJson(jsonString, MainScreenDataClass::class.java)
//                CustomUI(json.uiComponents)

                val jsonStringNew = readJsonFileFromResources(this, R.raw.login_ui)
                val jsonNew = Gson().fromJson(jsonStringNew, LoginUIDataClass::class.java)
                CustomUINew(jsonNew)
            }
        }
    }

    private fun readJsonFileFromResources(context: Context, resourceId: Int): String {
        val inputStream: InputStream = context.resources.openRawResource(resourceId)
        return inputStream.bufferedReader().use { it.readText() }
    }
}

@Composable
fun CustomUI(uiComponents: MainScreenDataClass.UiComponents) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(uiComponents.mainTitle.height?.dp ?: 40.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = uiComponents.mainTitle.text,
                color = Color(android.graphics.Color.parseColor(uiComponents.mainTitle.color)),
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            for (textField in uiComponents.loginTextFields) {
                LoginTextField(textField)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(uiComponents.box.width.dp, uiComponents.box.height.dp)
                .background(Color(android.graphics.Color.parseColor(uiComponents.box.color)))
        )
    }
}

@Composable
fun LoginTextField(loginTextField: MainScreenDataClass.UiComponents.LoginTextField) {
    var inputValue by remember { mutableStateOf("") }
    OutlinedTextField(
        value = inputValue,
        onValueChange = { inputValue = it },
        label = { Text(text = loginTextField.lable) },
        placeholder = { Text(text = loginTextField.hintLabel) },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = when (loginTextField.inputType) {
                1 -> KeyboardType.Email
                2 -> KeyboardType.Password
                else -> KeyboardType.Text
            }
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(android.graphics.Color.parseColor(loginTextField.color)),
            unfocusedBorderColor = Color(android.graphics.Color.parseColor(loginTextField.color))
        ),
        modifier = Modifier
            .padding(
                top = loginTextField.margin.top.dp,
                bottom = loginTextField.margin.bottom.dp,
                start = loginTextField.margin.left.dp,
                end = loginTextField.margin.right.dp
            )
    )
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeAssignmentTheme {
        Greeting("Android")
    }
}


@Composable
fun CustomUINew(data: LoginUIDataClass) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        data.content?.let {
            data.content!!.forEach { singleContent ->
                run {
                    when (singleContent!!.type) {
                        "text" -> {
                            Text(
                                text = singleContent.attributes!!.text!!,
                                color = Color(android.graphics.Color.parseColor(singleContent.attributes!!.color!!)),
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(
                                        top = singleContent.attributes?.padding?.top?.dp ?: 0.dp,
                                        bottom = singleContent.attributes?.padding?.bottom?.dp ?: 0.dp,
                                        start = singleContent.attributes?.padding?.left?.dp ?: 0.dp,
                                        end = singleContent.attributes?.padding?.right?.dp ?: 0.dp,
                                    )

                            )
                            Spacer(modifier = Modifier.height(16.dp))

                        }
                        "text_field" -> {
                            singleContent.components?.let {
                                singleContent.components!!.forEach {
                                    TextFieldComponent(it, singleContent)
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))

                        }
                        "button" -> {
                            Button(
                                onClick = { /* Handle button click */ },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(android.graphics.Color.parseColor(singleContent.attributes!!.backgroundColor!!))),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = singleContent.attributes?.padding?.top?.dp ?: 0.dp,
                                        bottom = singleContent.attributes?.padding?.bottom?.dp ?: 0.dp,
                                        start = singleContent.attributes?.padding?.left?.dp ?: 0.dp,
                                        end = singleContent.attributes?.padding?.right?.dp ?: 0.dp,
                                    )
                                    .padding(horizontal = singleContent.attributes?.margin?.right?.dp ?: 0.dp)

                            ) {
                                Text(
                                    text = singleContent.attributes!!.text!!,
                                    color = Color(android.graphics.Color.parseColor(singleContent.attributes!!.color!!))
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        "box" -> {
                            BoxComponent(singleContent)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TextFieldComponent(component: LoginUIDataClass.Content.Component?, singleContent: LoginUIDataClass.Content) {
    var inputValue by remember { mutableStateOf("") }
    OutlinedTextField(
        value = inputValue,
        onValueChange = { inputValue = it },
        label = { Text(text = component!!.label!!) },
        placeholder = { Text(text = component!!.hintLabel!!) },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = when (component!!.inputType!!) {
                1 -> KeyboardType.Email
                2 -> KeyboardType.Password
                else -> KeyboardType.Text
            }
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(android.graphics.Color.parseColor(component.color!!)),
            unfocusedBorderColor = Color(android.graphics.Color.parseColor(component.color!!))
        ),
        modifier = Modifier
            .padding(
                top = singleContent.attributes?.padding?.top?.dp ?: 0.dp,
                bottom = singleContent.attributes?.padding?.bottom?.dp ?: 0.dp,
                start = singleContent.attributes?.padding?.left?.dp ?: 0.dp,
                end = singleContent.attributes?.padding?.right?.dp ?: 0.dp,
            )
            .padding(horizontal = singleContent.attributes?.margin?.right?.dp ?: 0.dp)
    )
}

@Composable
fun BoxComponent(singleContent: LoginUIDataClass.Content) {
    Box(
        modifier = Modifier
            .size(width = singleContent.attributes!!.width!!.dp, height = singleContent.attributes!!.height!!.dp)
            .background(Color(android.graphics.Color.parseColor(singleContent.attributes!!.color!!)))
    )
}
