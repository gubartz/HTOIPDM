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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifsp.hto.dataclass.domain.Tarefa
import br.edu.ifsp.hto.dataclass.domain.tarefa1
import br.edu.ifsp.hto.dataclass.domain.tarefa2
import br.edu.ifsp.hto.dataclass.domain.tarefa3
import br.edu.ifsp.hto.dataclass.ui.theme.DataClassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var tarefas = InicializarDados()

        setContent {
            /*
            TODO(3) Todo o conteúdo da tela ficará em um BOX com safeDrawingPadding.
                Isso garante que os elementos sejam renderizados respeitando as áreas seguras da
                tela, como as barras superiores e outras sobreposições do sistema.
            */
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

/*
TODO(4) Criação de uma função para inicializar uma lista
 com os dados manualmente criados em model/Tarefa.kt
*/
fun InicializarDados(): List<Tarefa> {
    val listaTarefas = mutableListOf(
        tarefa1,
        tarefa2,
        tarefa3,
    )

    return listaTarefas
}

/*
TODO(5) Layout principal da tela.
 Consiste em uma coluna com um texto e uma LazyColumn para exibir a lista de tarefas.
*/
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

/*
TODO(6) Este composable representa um elemento da LazyColumn.
 Cada elemento é um Card. O Card é um contêiner com cantos arredondados, sombra e elevação.
*/
@Composable
fun TarefaCard(tarefa: Tarefa) {
    /*
    TODO(7) Para dar suporte à marcação do item, criamos um estado observável com base no
     conteúdo de um item da lista. Aqui é importante destacar que não estamos alterando nada do
     conteúdo da lista! Apenas permitimos que o estado de ehFinalizada seja alterado, mas não da
     propriedade tarefa.ehFinalizada.
    */
    var ehFinalizada by remember { mutableStateOf(tarefa.ehFinalizada) }

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
                checked = ehFinalizada,
                onCheckedChange = { ehFinalizada = it }
            )
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = tarefa.titulo,
                    style = MaterialTheme.typography.titleLarge
                )
                /*
                TODO(8) Como tarefa.descricao pode ser nula, utilizamos o ?.let.
                 O ?.let serve para executar um bloco de código apenas se o valor não for nulo.
                 */
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