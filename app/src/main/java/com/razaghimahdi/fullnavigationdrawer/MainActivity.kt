package com.razaghimahdi.fullnavigationdrawer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.razaghimahdi.fullnavigationdrawer.components.ClothItem
import com.razaghimahdi.fullnavigationdrawer.components.TabLayout
import com.razaghimahdi.fullnavigationdrawer.ui.theme.*
import com.razaghimahdi.library.CardDrawer
import com.razaghimahdi.library.core.CardDrawerState
import com.razaghimahdi.library.core.CardDrawerValue
import com.razaghimahdi.library.core.rememberCardDrawerState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FullNavigationDrawerTheme {

                val drawerState = rememberCardDrawerState(initialValue = CardDrawerValue.Closed)

                CardDrawer(
                    contentCornerSize = 16.dp,
                    drawerContent = { DrawerContent(drawerState) },
                    drawerBackgroundColor = DrawerBackground,
                    drawerState = drawerState
                ) {
                    MainScreen(drawerState)
                }
            }
        }
    }


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainScreen(drawerState: CardDrawerState) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            Card(
                onClick = { coroutineScope.launch { drawerState.open() } },
                modifier = Modifier.size(50.dp),
                shape = RoundedCornerShape(12.dp),
                backgroundColor = Color.White
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_menu),
                    null,
                    tint = DrawerBackground,
                    modifier = Modifier
                        .padding(12.dp)
                )
            }


            Card(
                modifier = Modifier.size(50.dp),
                shape = RoundedCornerShape(12.dp),
                backgroundColor = Color.White
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_user),
                    null,
                    tint = DrawerBackground,
                    modifier = Modifier
                        .padding(12.dp)
                )
            }

        }

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "Best Suits",
            style = MaterialTheme.typography.h2,
        )

        Text(
            text = "Perfect Choice!",
            style = MaterialTheme.typography.h5,
        )
        Spacer(modifier = Modifier.padding(8.dp))


        Row() {


            Card(
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                backgroundColor = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Icon(
                        painterResource(id = R.drawable.ic_search),
                        null,
                        modifier = Modifier.padding(8.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Search", style = MaterialTheme.typography.body2,
                        color = OnBackground2
                    )
                }
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                backgroundColor = Color.White
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_settings),
                    null,
                    tint = DrawerBackground,
                    modifier = Modifier
                        .padding(12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))


        TabLayout(
            listOf(
                "All",
                "Shirts",
                "Shoes",
                "Hoodies",
                "coats",
                "suits",
            )
        )


        Spacer(modifier = Modifier.padding(8.dp))


        LazyColumn() {
            items(cloths) {
                ClothItem(it)
            }
        }


    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DrawerContent(drawerState: CardDrawerState) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                Card(
                    shape = CircleShape,
                    backgroundColor = cardViewBackground
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_user),
                        null,
                        tint = Color.White,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column() {
                    Text(
                        text = "Mahdi R.",
                        style = MaterialTheme.typography.body2,
                        color = Color.White
                    )
                    Text(
                        text = "Android Developer",
                        style = MaterialTheme.typography.body1,
                        color = Color.White
                    )
                }
            }

            Card(
                onClick = { coroutineScope.launch { drawerState.close() } },
                shape = CircleShape
            ) {
                Icon(
                    painterResource(id = R.drawable.close_small),
                    null,
                    tint = DrawerBackground,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(64.dp))

        Text(
            text = "Browse",
            style = MaterialTheme.typography.subtitle1,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painterResource(id = R.drawable.ic_home),
                null,
                tint = Color.White,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Home", style = MaterialTheme.typography.body2,
                color = Color.White
            )
        }

        Divider(
            color = cardViewBackground,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Icon(
                painterResource(id = R.drawable.ic_search),
                null,
                tint = Color.White,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Search", style = MaterialTheme.typography.body2,
                color = Color.White
            )
        }

        Divider(
            color = cardViewBackground,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )



        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Icon(
                painterResource(id = R.drawable.ic_star),
                null,
                tint = Color.White,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Favorites", style = MaterialTheme.typography.body2,
                color = Color.White
            )
        }

        Divider(
            color = cardViewBackground,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Icon(
                painterResource(id = R.drawable.ic_comments),
                null,
                tint = Color.White,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Help", style = MaterialTheme.typography.body2,
                color = Color.White
            )
        }








        Spacer(modifier = Modifier.height(64.dp))


        Text(
            text = "History",
            style = MaterialTheme.typography.subtitle1,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painterResource(id = R.drawable.ic_time),
                null,
                tint = Color.White,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "History", style = MaterialTheme.typography.body2,
                color = Color.White
            )
        }

        Divider(
            color = cardViewBackground,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )



        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painterResource(id = R.drawable.ic_bell),
                null,
                tint = Color.White,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Notifications", style = MaterialTheme.typography.body2,
                color = Color.White
            )
        }

        Divider(
            color = cardViewBackground,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )


    }

}
