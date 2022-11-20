package com.example.mytestapplicationframework.presentation.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mytestapplicationframework.presentation.compose.ui.theme.MyCleanFrameworkTheme

class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Column {
            Text(text = "Hello $name!")
            Text(text = "Hi $name!")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = "Hello1 $name!")
            Text(text = "Hi1 $name!")
        }
    }
}

@Composable
fun List(texts: Array<String>) {
    LazyColumn {
        items(texts) { message ->
            Text(text = "Hi $message")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyCleanFrameworkTheme {
        List(arrayOf("a","b","c","d","e"))
    }
}