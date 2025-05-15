package com.example.firstcomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactDetails(contact = Contact(
                name = "Евгений",
                surname = "Андреевич",
                familyName = "Лукашин",
                isFavorite = true,
                phone = "+7 495 495 95 95",
                address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
                email = "ELukashin@practicum.ru"
            ))
        }
    }

    @Composable
    fun ContactDetails(contact: Contact)
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Первым делом показываем изображение контакта
            Box(
                modifier = Modifier.height(64.dp),
                contentAlignment = Alignment.Center
            ){
                when (contact.imageRes == null) {
                    true -> {
                        Icon(
                            modifier = Modifier.size(64.dp),
                            painter = painterResource(id = R.drawable.circle),
                            tint = Color.LightGray,
                            contentDescription = null
                        )
                        Text(
                            text = contact.name.take(1) + contact.familyName.take(1),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                    false -> Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = contact.imageRes),
                        contentDescription = null
                    )
                }
            }
            // Теперь показываем имя и отчество, если есть
            Text("${contact.name} ${contact.surname.orEmpty()}",
                modifier = Modifier.padding(top = 12.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold)
            // Теперь у нас фамилия и отметка избранного
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(contact.familyName,
                    fontSize = 20.sp)
                if (contact.isFavorite)
                    Image(
                        modifier = Modifier.height(20.dp).padding(start = 6.dp),
                        painter = painterResource(android.R.drawable.star_big_on),
                        contentDescription = null)
            }
            // Теперь телефон
            InfoRow(stringResource(R.string.phone), contact.phone)
            // Теперь адрес
            InfoRow(stringResource(R.string.address), contact.address)
            // Теперь E-mail
            if (!contact.email.isNullOrEmpty())
                InfoRow(stringResource(R.string.email), contact.email)
        }
    }

    @Composable
    fun InfoRow(name: String, value: String) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(name,
                modifier = Modifier.weight(0.5f).padding(horizontal = 4.dp),
                textAlign = TextAlign.End,
                fontSize = 16.sp)
            Text(value,
                modifier = Modifier.weight(0.5f).padding(horizontal = 4.dp),
                fontSize = 16.sp)
        }
    }

    @Preview(showSystemUi = true,
        device = "spec:width=1080px,height=2400px,dpi=420"
    )
    @Composable
    fun ContactDetailsPreviewLukashin(){
        ContactDetails(contact = Contact(
            name = "Евгений",
            surname = "Андреевич",
            familyName = "Лукашин",
            isFavorite = true,
            phone = "+7 495 495 95 95",
            address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
            email = "ELukashin@practicum.ru"
        ))
    }

    @Preview(showSystemUi = true,
        device = "spec:width=1080px,height=2400px,dpi=420"
    )
    @Composable
    fun ContactDetailsPreviewKuzyakin(){
        ContactDetails(contact = Contact(
            name = "Василий",
            familyName = "Кузякин",
            phone = "---",
            address = "Ивановская область, дер. Крутово, д. 4",
            imageRes = R.drawable.kuzyakin
        ))
    }

}