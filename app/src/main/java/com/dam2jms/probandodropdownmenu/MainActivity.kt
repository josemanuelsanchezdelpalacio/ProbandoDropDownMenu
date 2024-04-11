package com.dam2jms.probandodropdownmenu

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dam2jms.probandodropdownmenu.ui.theme.ProbandoDropDownMenuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProbandoDropDownMenuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Demo_dropDownMenu()
                    Demo_ExposeDropMenu()
                }
            }
        }
    }
}

@Composable
fun Demo_dropDownMenu() {
    val context = LocalContext.current
    var expanded by remember {
        mutableStateOf(false)
    }
    Row(modifier = Modifier.size(20.dp)){
        IconButton(onClick = { expanded = !expanded }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = { "Opcion 1" }, onClick = { Toast.makeText(context, "Opcion 1 elegida", Toast.LENGTH_SHORT).show() })
            DropdownMenuItem(text = { "Opcion 2" }, onClick = { Toast.makeText(context, "Opcion 2 elegida", Toast.LENGTH_SHORT).show() })
            DropdownMenuItem(text = { "Opcion 3" }, onClick = { Toast.makeText(context, "Opcion 3 elegida", Toast.LENGTH_SHORT).show() })
            DropdownMenuItem(text = { "Opcion 4" }, onClick = { Toast.makeText(context, "Opcion 4 elegida", Toast.LENGTH_SHORT).show() })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Demo_ExposeDropMenu() {
    val context = LocalContext.current
    val menuOptions = arrayOf("Opcion 1", "Opcion 2", "Opcion 3", "Opcion 4")
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedText by remember {
        mutableStateOf(menuOptions[0])
    }
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(32.dp)){
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {expanded = !expanded}) {
            TextField(value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)},
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = !expanded }) {
                menuOptions.forEach { opcion ->
                    DropdownMenuItem(text = { Text(text = opcion) },
                        onClick = {
                            selectedText = opcion
                            expanded = false
                            Toast.makeText(context, "Has elegido la " + opcion, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}