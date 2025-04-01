package br.edu.ifsp.hto.dataclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifsp.hto.dataclass.data.TarefaDAOImpl
import br.edu.ifsp.hto.dataclass.model.Tarefa
import br.edu.ifsp.hto.dataclass.model.tarefa1
import br.edu.ifsp.hto.dataclass.model.tarefa2
import br.edu.ifsp.hto.dataclass.model.tarefa3
import br.edu.ifsp.hto.dataclass.ui.theme.DataClassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var tarefas = InicializarDados()

        setContent {
            Box(
                modifier = Modifier
                    .safeDrawingPadding()
            ) {
                DataClassTheme {
                    DataClassApp(
                        tarefas = tarefas
                    )
                }
            }
        }
    }
}

fun InicializarDados(): List<Tarefa> {
    val tarefaDAOImpl = TarefaDAOImpl()
    val listaTarefas = mutableListOf(
        tarefa1,
        tarefa2,
        tarefa3,
    )

    tarefaDAOImpl.insertMulti(listaTarefas)

    return tarefaDAOImpl.getAllTarefas()
}

@Composable
fun DataClassApp(tarefas: List<Tarefa>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Lista de Tarefas",
            style = MaterialTheme.typography.titleLarge
        )
        LazyColumn {
            items(tarefas) { tarefa ->
                TarefaCard(tarefa)
            }
        }
    }
}

@Composable
fun TarefaCard(tarefa: Tarefa) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Checkbox(
                checked = tarefa.ehFinalizada,
                onCheckedChange = { }
            )
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = tarefa.titulo,
                    style = MaterialTheme.typography.titleLarge
                )
                tarefa.descricao?.let {
                    Text(
                        text = tarefa.descricao,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DataClassAppPreview() {
    var tarefas = InicializarDados()
    DataClassTheme {
        DataClassApp(
            tarefas = tarefas
        )
    }
}