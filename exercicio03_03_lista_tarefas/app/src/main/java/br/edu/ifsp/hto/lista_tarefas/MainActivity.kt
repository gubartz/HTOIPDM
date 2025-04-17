package br.edu.ifsp.hto.lista_tarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.hto.lista_tarefas.ui.theme.ListaTarefasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListaTarefasTheme {
                ListaTarefa()
            }
        }
    }
}

@Composable
fun ListaTarefa() {
    val listaTarefas = remember { mutableStateListOf<String>() }
    var txtTarefa by remember { mutableStateOf("") }
    var indiceEditando by remember { mutableStateOf(-1) }
    var textoBotao by remember { mutableStateOf("Adicionar") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .windowInsetsPadding(WindowInsets.safeDrawing),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Lista de Tarefas",
            style = TextStyle(
                fontSize = 32.sp
            )
        )
        Spacer(modifier = Modifier.size(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f),
                value = txtTarefa,
                onValueChange = {
                    txtTarefa = it
                },
                label = { Text("Entre com uma tarefa") }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    if (indiceEditando != -1) {
                        listaTarefas[indiceEditando] = txtTarefa
                        textoBotao = "Adicionar"
                        indiceEditando = -1
                    } else {
                        listaTarefas.add(txtTarefa)
                    }
                    txtTarefa = ""
                }
            ) {
                Text(textoBotao)
            }
        }
        LazyColumn {
            itemsIndexed(listaTarefas) { currentIndex, tarefa ->
                TarefaCard(
                    tarefa,
                    onEdit = {
                        textoBotao = "Editar"
                        txtTarefa = tarefa
                        indiceEditando = currentIndex
                    },
                    onDelete = {
                        listaTarefas.removeAt(currentIndex)
                    }
                )
            }
        }
    }
}

@Composable
fun TarefaCard(tarefa: String, onEdit: () -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = tarefa,
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
            )
            Row(
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd)
            ) {
                Button(
                    onClick = onEdit
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = onDelete,
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remover",
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaTarefaPreview() {
    ListaTarefasTheme {
        ListaTarefa()
    }
}