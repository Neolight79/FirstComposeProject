package com.example.firstcomposeproject

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hello(name = "ываываыва ыва ыва ")

        }
    }

    @Composable
    fun Hello(name: String?)
    {
        name
            ?.let{Text(text = "Hello, $name !",
                style = TextStyle(
                    color = Color.Green,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center
                ))}
            ?:Text("Имя не задано")
    }

    @Preview(
        showSystemUi = true, device = "spec:width=1080px,height=2400px,dpi=420",
        uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_UNDEFINED
    )
    @Preview(device = "spec:parent=pixel_5,orientation=landscape", showSystemUi = true)
    @Preview(device = "spec:width=200dp,height=200dp,isRound=true", showBackground = true,
        backgroundColor = 0xFFFFEB3B, showSystemUi = true)
    @Composable
    fun HelloPreview(){
        Hello("Android")
    }

    @Preview(showSystemUi = true)
    @Composable
    fun HelloNullPreview(){
        Hello(null)
    }

    @Preview(name = "portrait", showSystemUi = true)
    @Composable
    fun ImagePreview() {
        Image(
            modifier = Modifier.run { size(240.dp, 120.dp) },
            alignment = Alignment.Center,
            painter = painterResource(id = R.drawable.circle),
            contentDescription = "R.string.standard_icon.toString()"
        )
    }

    @Preview(name = "Circle1", showSystemUi = true)
    @Composable
    fun Circle1Preview() {
        Image(
            modifier = Modifier.size(width = 240.dp, height = 120.dp).background(Color.Black),
            alignment = Alignment.TopEnd,
            contentScale = ContentScale.None,
            painter = painterResource(id = R.drawable.circle),
            contentDescription = null
        )
    }

    @Preview(name = "Circle2", showSystemUi = true)
    @Composable
    fun Circle2Preview() {
        Image(
            modifier = Modifier
                .size(width = 240.dp, height = 120.dp)
                .background(Color.Blue)
                .alpha(0.5f),
            alignment = Alignment.Center,
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.circle),
            contentDescription = null
        )
    }

    @Preview(name = "button", showSystemUi = true)
    @Composable
    fun Button1() {
        Button(
            onClick = { val a = 0 },
            content = { Text("Нажми на меня") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, Color.Gray),
            elevation = ButtonDefaults.buttonElevation(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black)
        )
    }

    @Composable
    fun RoundInitials(initials: String) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = null,
                )
                Text(
                    text = initials
                )
            }
        }
    }

    @Preview(name = "portrait", showSystemUi = true)
    @Composable
    fun RoundInitialsPreview() {
        RoundInitials("АБ")
    }

}