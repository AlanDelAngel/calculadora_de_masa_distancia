package com.example.calculadora_de_masa_distancia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calculadora_de_masa_distancia.ui.theme.Calculadora_de_masa_distanciaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Calculadora_de_masa_distanciaTheme {
                AppNavigation()
                }
            }
        }
    }

// -------------------- NAVIGATION --------------------
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") { MenuScreen(navController) }
        composable("page1") { conversion_lbs_kg(navController) }
        composable("page2") { conversion_kg_lbs(navController) }
        composable("page3") { conversion_mi_km(navController) }
        composable("page4") { conversion_km_mi(navController) }
    }
}

// -------------------- MAIN MENU --------------------

@Composable
fun MenuScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Menú Principal", style = MaterialTheme.typography.headlineMedium)

        Button(onClick = { navController.navigate("page1") }, modifier = Modifier.fillMaxWidth()) {
            Text("Conversión Libras → Kilos")
        }
        Button(onClick = { navController.navigate("page2") }, modifier = Modifier.fillMaxWidth()) {
            Text("Conversión Kilos → Libras")
        }
        Button(onClick = { navController.navigate("page3") }, modifier = Modifier.fillMaxWidth()) {
            Text("Conversión Millas → Kilometros")
        }
        Button(onClick = { navController.navigate("page4") }, modifier = Modifier.fillMaxWidth()) {
            Text("Conversión Kilometros → Millas")
        }
    }
}


fun convertir_lbs_a_kg(libras: Float): Double {
    return 0.453592 * libras
}

fun convertir_kg_a_lbs(kilos: Float): Double {
    return 2.20462 * kilos
}

fun convertir_mi_a_km(millas: Float): Double {
    return 1.60934 * millas
}

fun convertir_km_a_mi(kilometros: Float): Double {
    return 0.621371 * kilometros
}

@Composable
fun conversion_lbs_kg(navController: NavController, modifier: Modifier = Modifier) {
    var inputText by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<Double?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Ingrese libras") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val libras = inputText.toFloatOrNull()
                if (libras != null) {
                    resultado = convertir_lbs_a_kg(libras)
                } else {
                    resultado = null
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Convertir a Kg")
        }

        Text(
            text = resultado?.let { "Resultado: %.2f kg".format(it) } ?: "Ingrese un número válido",
            style = MaterialTheme.typography.bodyLarge
        )

        Button(onClick = { navController.navigate("menu") }, modifier = Modifier.fillMaxWidth()) {
            Text("<--")
        }
    }
}

@Composable
fun conversion_kg_lbs(navController: NavController, modifier: Modifier = Modifier) {
    var inputText by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<Double?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Ingrese kilogramos") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val kilos = inputText.toFloatOrNull()
                if (kilos != null) {
                    resultado = convertir_kg_a_lbs(kilos)
                } else {
                    resultado = null
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Convertir a lbs")
        }

        Text(
            text = resultado?.let { "Resultado: %.2f lbs".format(it) } ?: "Ingrese un número válido",
            style = MaterialTheme.typography.bodyLarge
        )

        Button(onClick = { navController.navigate("menu") }, modifier = Modifier.fillMaxWidth()) {
            Text("<--")
        }
    }
}

@Composable
fun conversion_mi_km(navController: NavController, modifier: Modifier = Modifier) {
    var inputText by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<Double?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Ingrese millas") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val millas = inputText.toFloatOrNull()
                if (millas != null) {
                    resultado = convertir_mi_a_km(millas)
                } else {
                    resultado = null
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Convertir a km")
        }

        Text(
            text = resultado?.let { "Resultado: %.2f km".format(it) } ?: "Ingrese un número válido",
            style = MaterialTheme.typography.bodyLarge
        )

        Button(onClick = { navController.navigate("menu") }, modifier = Modifier.fillMaxWidth()) {
            Text("<--")
        }
    }
}

@Composable
fun conversion_km_mi(navController: NavController, modifier: Modifier = Modifier) {
    var inputText by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<Double?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Ingrese kilometros") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val kilometros = inputText.toFloatOrNull()
                if (kilometros != null) {
                    resultado = convertir_km_a_mi(kilometros)
                } else {
                    resultado = null
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Convertir a millas")
        }

        Text(
            text = resultado?.let { "Resultado: %.2f mi".format(it) } ?: "Ingrese un número válido",
            style = MaterialTheme.typography.bodyLarge
        )

        Button(onClick = { navController.navigate("menu") }, modifier = Modifier.fillMaxWidth()) {
            Text("<--")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Calculadora_de_masa_distanciaTheme {
        MenuScreen(navController = rememberNavController())
    }
}