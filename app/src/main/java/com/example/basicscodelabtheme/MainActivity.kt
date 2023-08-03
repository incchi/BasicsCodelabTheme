package com.example.basicscodelabtheme

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.example.basicscodelabtheme.ui.theme.BasicsCodelabThemeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabThemeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}
//TODO add logic to MYAPP to show different screen ,and in HOIST state
@Composable
fun MyApp(modifier: Modifier = Modifier){
    var shouldShowOnBoarding by remember { mutableStateOf(true) }

    Surface(modifier) {
        if(shouldShowOnBoarding){
            OnboardingScreen(onContinuedClicked = {shouldShowOnBoarding =false})
        }else{
            Greetings()
        }
    }

}
//TODO to make MyApp fun hoist we have to move all of its content to a new function
@Composable
private fun Greetings (
    modifier: Modifier = Modifier,names: List<String> = List(1000){"$it"}
    ){
        LazyColumn(modifier =modifier.padding(vertical = 4.dp)){
            items(items = names){
                name -> Greeting(name = name)
            }
        }





/*Column(modifier = modifier.padding(vertical = 4.dp)) {
            for (name in names){
                Greeting(name = name)
            }

        }

         */

}

@Composable
private fun Greeting(name: String) {
    var expanded by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if(expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )


    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
                Text(text = "Hello, ")
                Text(text = name, style = MaterialTheme.typography
                    .headlineMedium.copy(fontWeight = FontWeight.ExtraBold ))
            }
            ElevatedButton(onClick = { expanded = !expanded }) {
                Text(if (expanded) "Show less" else "Show More")
            }

        }

    }
}
//TODO for STATE HOISTING

@Composable
fun OnboardingScreen(
    onContinuedClicked : () ->  Unit,
    modifier: Modifier=Modifier){

    //TODO THis state should be hoisted
    //var shouldShowOnBoarding by remember { mutableStateOf(true) }
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Basics Codelab")
        Button(
            modifier=Modifier.padding(vertical = 24.dp),
            onClick = {shouldShowOnboarding = false }
        ) {
            Text("Continue")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BasicsCodelabThemeTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true, widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES, name = "Dark")
@Composable
fun DefaultPreview() {
    BasicsCodelabThemeTheme {
        Greetings()
    }
}

@Preview(showBackground = true, heightDp = 320)
@Composable
fun OnBoardingPreview(){
        BasicsCodelabThemeTheme {
            OnboardingScreen(onContinuedClicked = {}) //Do nothing on click
        }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
private fun GreetingsPreview(){
    BasicsCodelabThemeTheme {
        Greetings()
    }
}

@Preview
@Composable
fun MyAppPreview(){
    BasicsCodelabThemeTheme {
        MyApp(Modifier.fillMaxSize())
    }
}









