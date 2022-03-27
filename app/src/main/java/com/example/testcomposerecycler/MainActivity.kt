package com.example.testcomposerecycler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testcomposerecycler.data.UserData.users
import com.example.testcomposerecycler.ui.theme.TestComposeRecyclerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestComposeRecyclerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyNavHost()
                }
            }
        }
    }
}

@Composable
fun <T> PersonsList(users: List<T>, rows: @Composable (Int) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        itemsIndexed(users) { index, _ ->
            Card(
                shape = MaterialTheme.shapes.medium,
                elevation = 2.dp,
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                ) {
                    rows(index)
                }
            }
        }
    }
}

@Composable
private fun PersonRow(userId: Int, navController: NavHostController) {
    val user = users[userId]
    Row(Modifier.clickable {
        navController.navigate("${Screen.Profile.name}/$userId")
    }) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            modifier = Modifier.size(48.dp),
            contentDescription = null
        )
        Column(
            Modifier
                .fillMaxWidth()
                .weight(1F)
        ) {
            Row {
                Text(text = user.name, style = typography.body1)
            }
            Row {
                Text(text = user.sex.value, style = typography.caption)
            }
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(24.dp)
        )
    }
}

@Composable
fun Profile(userId: Int) {
    val user = users[userId]
    Column(
        Modifier
            .fillMaxWidth()
    ) {
        Row {
            Text(text = user.name, style = typography.body1)
        }
        Row {
            Text(text = user.sex.value, style = typography.caption)
        }
        Row {
            Text(text = user.age.toString(), style = typography.caption)
        }
    }
}

@Composable
fun MyNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.PersonList.name) {
        composable(
            route ="${Screen.Profile.name}/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            Profile(backStackEntry.arguments?.getInt("userId")!!)
        }
        composable(Screen.PersonList.name) {
            PersonsList(users = users) { userId ->
                PersonRow(userId, navController)
            }
        }
    }
}

enum class Screen {
    Profile,
    PersonList
}



