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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    val listaTarefas = mutableListOf(
        tarefa1,
        tarefa2,
        tarefa3,
    )

    return listaTarefas
}

@Composable
fun DataClassApp(tarefas: List<Tarefa>) {
    /*
    TODO(1) Definimos o tarefasState para que seja possível observar as mudanças. Note que o estado
     é observado em tarefasState e não em tarefas. Elas são indpendentes.
     */
    val tarefasState = remember { tarefas.toMutableStateList() }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Lista de Tarefas",
            style = MaterialTheme.typography.titleLarge
        )
        LazyColumn {
            /*
            TODO(4) Trocamos para itemsIndexed para ter acesso a qual elemento da lista está sendo
             renderizado na tela. Agora temos tanto o índice (index) quanto o objeto (tarefa).
             */
            itemsIndexed(tarefasState) { index, tarefa ->
                TarefaCard(
                    tarefa = tarefa,
                    /*
                    TODO(5) Definimos a função como uma lambda (função anônima).
                     */
                    onFinalizadaCheck = { ehMarcado ->
                        /*
                        TODO(6) O copy permite copiar um objeto e trocamos apenas o valor do
                         atributo ehFinalizada para a marcação realizada na tela pelo usuário.
                         */
                        tarefasState[index] = tarefa.copy(ehFinalizada = ehMarcado)
                    }
                )
            }
        }
    }
}

@Composable
fun TarefaCard(
    tarefa: Tarefa,
    /*
     TODO(2) TarefaCard recebe um parâmetro, que é uma função. Ou seja, além de passar valores
      também é possível passar comportamento/ações por meio de funções. A função a ser definida
      recebe um Boolean, que vai indicar se o checkbox está marcado ou não. O Unit indica que essa
      função não tem retorno.
     */

    onFinalizadaCheck: (Boolean) -> Unit
) {
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
                /*
                TODO(3) Chamamos a função, que foi enviada por parâmetro, passando o valor do
                 checkbox. Esse valor é um Boolean que indica se o checkbox está marcado ou não.
                 */
                onCheckedChange = { ehMarcado ->
                    onFinalizadaCheck(ehMarcado)
                }
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