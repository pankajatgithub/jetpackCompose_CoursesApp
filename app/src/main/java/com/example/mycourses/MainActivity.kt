package com.example.mycourses

import android.app.admin.SystemUpdateInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycourses.ui.theme.MyCoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCoursesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
//                    HomeScreen()

                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    onDetailsClick: (title: String) -> Unit,
    onAboutClick: () -> Unit
) {
    Scaffold { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            item {
                HomeAppBar(onAboutClick)
            }
            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
            items(allCourses) { item ->
                CourseCard(
                    item,
                    onClick = { onDetailsClick(item.title) }
                )
            }
        }


    }

}

@Composable
private fun HomeAppBar(onAboutClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(text = "My Course", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = onAboutClick) {
            Text(text = "About", fontSize = 24.sp)
        }
    }
}

@Composable
fun CourseCard(item: Courses, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Column() {
            Image(
                painterResource(item.thumbnail),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop

            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {

                Text(text = item.title)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item.body, maxLines = 1, style = MaterialTheme.typography.bodySmall)
            }

        }

    }
}

@Composable
fun AboutScreen(onNavigateUp: () -> Unit) {

    Scaffold() { padding ->
        Column(Modifier.padding(padding)) {
            AppBar(title = "About", onNavigateUp)
            Spacer(modifier = Modifier.height(20.dp))
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = "This app is a demonstration about the navigation in" +
                            "android jetpack compose"
                )

                Spacer(modifier = Modifier.height(20.dp))

                val udemy_link = LocalUriHandler.current
                Button(onClick = { udemy_link.openUri("https://www.udemy.com/course/mastering-jetpack-compose/learn/lecture/38145166#overview") }) {
                    Text(text = "View Our Udemy ")
                }
            }

        }
    }

}

@Composable
fun AppBar(title: String, onNavigateUp: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        IconButton(onClick = onNavigateUp) {
            Icon(
                Icons.Rounded.ArrowBack,
                contentDescription = "Go Back"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = title, fontSize = 24.sp)
        }
    }
}

@Composable
fun DetailsScreen(title: String, name: String?, onNavigateUp: () -> Unit) {
    val choosen_course = allCourses.first { it.title == title }

    Scaffold() { padding ->
        Column(Modifier.padding(padding)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        Icons.Rounded.ArrowBack,
                        contentDescription = "Go Back"
                    )
                }

                Image(
                    painterResource(id = choosen_course.thumbnail), contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(text = choosen_course.title, Modifier.fillMaxSize(), fontSize = 20.sp)

                }

            }
        }
    }
}