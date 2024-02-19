package com.example.unitconcerter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconcerter.ui.theme.UnitConcerterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConcerterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val inputText = remember {
            mutableStateOf("")
        }

        val outputValue = remember {
            mutableStateOf("")
        }

        val inputUnit = remember {
            mutableStateOf("Meters")
        }

        val outputUnit = remember {
            mutableStateOf("Meters")
        }

        val iExpended = remember {
            mutableStateOf(false)
        }

        val oExpended = remember {
            mutableStateOf(false)
        }

        val conversionFactor = remember {
            mutableDoubleStateOf(1.00)
        }

        val oConversionFactor = remember {
            mutableDoubleStateOf(1.00)
        }

        fun convertUnits() {
            val inputValueDouble = inputText.value.toDoubleOrNull() ?: 0.0
            val result =
                (inputValueDouble * conversionFactor.doubleValue * 100.0 / oConversionFactor.doubleValue).roundToInt() / 100.0
            outputValue.value = result.toString()
        }

        Text(
            text = "Unit Converter",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputText.value,
            onValueChange = {
                inputText.value = it
                convertUnits()
            },
            label = {
                Text(text = "Enter Value")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = { iExpended.value = !iExpended.value }) {
                    Text(inputUnit.value)
                    Icon(Icons.Default.ArrowDropDown, null)
                }
                DropdownMenu(
                    expanded = iExpended.value,
                    onDismissRequest = { iExpended.value = !iExpended.value }
                ) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            iExpended.value = !iExpended.value
                            inputUnit.value = "Centimeters"
                            conversionFactor.doubleValue = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            iExpended.value = !iExpended.value
                            inputUnit.value = "Meters"
                            conversionFactor.doubleValue = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            iExpended.value = !iExpended.value
                            inputUnit.value = "Feet"
                            conversionFactor.doubleValue = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            iExpended.value = !iExpended.value
                            inputUnit.value = "Millimeters"
                            conversionFactor.doubleValue = 0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpended.value = !oExpended.value }) {
                    Text(outputUnit.value)
                    Icon(Icons.Default.ArrowDropDown, null)
                }
            }
            DropdownMenu(
                expanded = oExpended.value,
                onDismissRequest = { oExpended.value = oExpended.value }
            ) {
                DropdownMenuItem(
                    text = { Text("Centimeters") },
                    onClick = {
                        oExpended.value = !oExpended.value
                        outputUnit.value = "Centimeters"
                        oConversionFactor.doubleValue = 0.01
                        convertUnits()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Meters") },
                    onClick = {
                        oExpended.value = !oExpended.value
                        outputUnit.value = "Meters"
                        oConversionFactor.doubleValue = 1.00
                        convertUnits()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Feet") },
                    onClick = {
                        oExpended.value = !oExpended.value
                        outputUnit.value = "Feet"
                        oConversionFactor.doubleValue = 0.3048
                        convertUnits()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Millimeters") },
                    onClick = {
                        oExpended.value = !oExpended.value
                        outputUnit.value = "Millimeters"
                        oConversionFactor.doubleValue = 0.001
                        convertUnits()
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}