package edu.miu.composeboxlayout

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.miu.composeboxlayout.ui.theme.ComposeBoxLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBoxLayoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PersonLikeDislike(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PersonLikeDislike(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        val context = LocalContext.current
        
        var isFavouriteClicked by remember { mutableStateOf(false) }
        var isDislikeClicked by remember { mutableStateOf(false) }

        var personImageVector = if (isFavouriteClicked) {
            Icons.Default.Face
        } else if (isDislikeClicked){
            Icons.Default.Lock
        } else {
            Icons.Default.Person
        }
        Image(
            imageVector = personImageVector,
            contentDescription = "Person",
            modifier = modifier
                .size(200.dp)
        )
        Image(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Favorite",
            modifier = modifier
                .size(50.dp)
                .align(Alignment.TopEnd)
                .clickable {
                    // Handle favorite click
                    isFavouriteClicked = !isFavouriteClicked
                    isDislikeClicked = false
                    Toast.makeText(context, "Favorite clicked", Toast.LENGTH_SHORT).show()
                }
        )

        Image(
            imageVector = Icons.Default.Clear,
            contentDescription = "Launcher",
            modifier = modifier
                .size(50.dp)
                .align(Alignment.TopStart)
                .clickable {
                    //Handle delete click
                    isDislikeClicked = !isDislikeClicked
                    isFavouriteClicked = false
                    Toast.makeText(context, "Delete clicked", Toast.LENGTH_SHORT).show()
                }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeBoxLayoutTheme {
        PersonLikeDislike()
    }
}