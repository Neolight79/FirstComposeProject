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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
                .padding(dimensionResource(R.dimen.main_padding)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Первым делом показываем изображение контакта
            Box(
                modifier = Modifier.height(dimensionResource(R.dimen.image_size)),
                contentAlignment = Alignment.Center
            ){
                when {
                    contact.imageRes == null -> {
                        Icon(
                            modifier = Modifier.size(dimensionResource(R.dimen.image_size)),
                            painter = painterResource(id = R.drawable.circle),
                            tint = Color.LightGray,
                            contentDescription = null
                        )
                        Text(
                            text = contact.name.take(1) + contact.familyName.take(1),
                            fontWeight = FontWeight.Bold,
                            fontSize = dimensionResource(R.dimen.big_font).value.sp
                        )
                    }
                    else -> Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = contact.imageRes),
                        contentDescription = null
                    )
                }
            }
            // Теперь показываем имя и отчество, если есть
            Text("${contact.name} ${contact.surname.orEmpty()}",
                modifier = Modifier.padding(top = dimensionResource(R.dimen.middle_padding)),
                fontSize = dimensionResource(R.dimen.main_font).value.sp,
                fontWeight = FontWeight.Bold)
            // Теперь у нас фамилия и отметка избранного
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = dimensionResource(R.dimen.high_padding)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(contact.familyName,
                    fontSize = dimensionResource(R.dimen.big_font).value.sp)
                if (contact.isFavorite)
                    Image(
                        modifier = Modifier
                            .height(dimensionResource(R.dimen.is_favorite_size))
                            .padding(start = dimensionResource(R.dimen.half_middle_padding)),
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
            modifier = Modifier.fillMaxWidth().padding(vertical = dimensionResource(R.dimen.half_main_padding)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(name,
                modifier = Modifier.weight(0.5f).padding(horizontal = dimensionResource(R.dimen.quarter_main_padding)),
                textAlign = TextAlign.End,
                fontSize = dimensionResource(R.dimen.main_font).value.sp)
            Text(value,
                modifier = Modifier.weight(0.5f).padding(horizontal = dimensionResource(R.dimen.quarter_main_padding)),
                fontSize = dimensionResource(R.dimen.main_font).value.sp)
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