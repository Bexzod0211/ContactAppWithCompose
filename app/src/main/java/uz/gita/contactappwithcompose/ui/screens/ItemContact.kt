package uz.gita.contactappwithcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.contactappwithcompose.R
import uz.gita.contactappwithcompose.data.model.ContactData
import uz.gita.contactappwithcompose.ui.theme.ContactAppWithComposeTheme


@Composable
fun ContactItem(contact:ContactData) {
    ContactAppWithComposeTheme {
        Surface {
            Row(modifier = Modifier
                .fillMaxWidth()
                .size(0.dp, 80.dp)) {
                Image(painter = painterResource(id = R.drawable.photo_man), contentDescription =null, modifier = Modifier
                    .padding(start = 16.dp)
                    .size(60.dp)
                    .align(Alignment.CenterVertically))
                Column(modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
                    .fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                    Text(text = "${contact.firstName} ${contact.lastName}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = contact.phone, color = Color.Gray, modifier = Modifier.padding(top = 8.dp))
                }
                Image(painter = painterResource(id = R.drawable.ic_more), contentDescription = null, alignment = Alignment.Center, modifier = Modifier.padding(end = 16.dp).align(Alignment.CenterVertically))
            }
        }
    }
}

@Preview
@Composable
fun ContactItemPreview() {
    ContactItem(ContactData(1,"Bexzod","Mamatxalilov","+998916591363"))
}