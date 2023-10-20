package com.example.bullseyegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bullseyegame.ui.theme.BullsEyeGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BullsEyeGameTheme {
                GameScreen()
            }
        }
    }
}

object GameParameters {
    var min = 0;
    var max = 100;
    var target = mutableStateOf(((min + max)) / 2);
    var guess = mutableStateOf((min + max) / 2);

    var scoreIsVisible = mutableStateOf(false);
    val defaultColor = Color(0xFF6650a4);
}

@Composable
@Preview(showBackground = true)
fun GameScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bull's Eye Game",
            textAlign = TextAlign.Center,
            fontSize = 40.sp
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "move the slider as close as you can to: ${GameParameters.target} ",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(50.dp))
        SliderWrapped()
        Spacer(modifier = Modifier.height(50.dp))
        SubmitButton()
    }

}

@Composable
fun SliderWrapped() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${GameParameters.min}",
            textAlign = TextAlign.Right
        )
        Slider(
            value = GameParameters.guess.value.toFloat(),
            onValueChange = { newValue ->
                GameParameters.guess.value = newValue.toInt();
                GameParameters.scoreIsVisible.value = false
            },
            valueRange = GameParameters.min.toFloat()..GameParameters.max.toFloat(),
            enabled = true,
            modifier = Modifier
                .width(300.dp)
                .padding(10.dp),
            colors = SliderDefaults.colors(
                thumbColor = GameParameters.defaultColor,
                activeTrackColor = GameParameters.defaultColor
            )
        )
        Text(
            text = "${GameParameters.max}",
            textAlign = TextAlign.Left
        )
    }
}

@Composable
fun SubmitButton() {
    Button(
        onClick = {
//            submit();
            generateRandomTarget()
        }
    ) {
        Text(
            text = "Hit me!",
        )

    }
}

fun generateRandomTarget() {
    GameParameters.target.value = Random.nextInt(GameParameters.min, GameParameters.max + 1)
}