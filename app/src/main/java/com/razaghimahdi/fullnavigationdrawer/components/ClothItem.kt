package com.razaghimahdi.fullnavigationdrawer.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.razaghimahdi.fullnavigationdrawer.Cloth
import com.razaghimahdi.fullnavigationdrawer.ui.theme.DrawerBackground
import com.razaghimahdi.fullnavigationdrawer.ui.theme.cardViewBackground2


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ClothItem(cloth: Cloth) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        backgroundColor = Color.White,
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {


            Card(
                elevation = 0.dp,
                shape = RoundedCornerShape(16.dp),
                backgroundColor = cardViewBackground2,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(.5f)
                    .padding(8.dp)
            ) {
                Image(
                    painterResource(id = cloth.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 32.dp)
                )
            }

            Column(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(text = cloth.name, style = MaterialTheme.typography.subtitle1)
                    Text(text = "by ${cloth.seller}", style = MaterialTheme.typography.subtitle2)
                }

                Text(text = cloth.desc, style = MaterialTheme.typography.caption)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = cloth.price, style = MaterialTheme.typography.subtitle1)

                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(backgroundColor = DrawerBackground),
                        shape = RoundedCornerShape(24.dp),
                    ) {
                        Text(
                            text = "Buy",
                            color = Color.White,
                            style = MaterialTheme.typography.button
                        )
                    }

                }

            }


        }
    }
}
