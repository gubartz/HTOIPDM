package br.edu.ifsp.hto.listatarefas.navegacao

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import br.edu.ifsp.hto.dataclass.data.TarefaDAOImpl
import br.edu.ifsp.hto.listatarefas.domain.tarefa1
import br.edu.ifsp.hto.listatarefas.domain.tarefa2
import br.edu.ifsp.hto.listatarefas.domain.tarefa3
import br.edu.ifsp.hto.listatarefas.ui.telas.ListagemViewModel
import br.edu.ifsp.hto.listatarefas.ui.telas.TelaAdicionarEditar
import br.edu.ifsp.hto.listatarefas.ui.telas.TelaListagem
import kotlinx.serialization.Serializable

@Serializable
object ListagemRota

@Serializable
data class AdicionarEditarRota(val id: Long? = null)

@Composable
fun TarefaNavHost() {
    val navController = rememberNavController()
    val tarefaDAOImpl = TarefaDAOImpl()

    val factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ListagemViewModel(tarefaDAOImpl) as T
        }
    }

    val viewModel: ListagemViewModel = viewModel(factory = factory)

    NavHost(navController = navController, startDestination = ListagemRota) {
        composable<ListagemRota> {
            TelaListagem(
                navegarParaTelaAdicionarEditar = { id ->
                    navController.navigate(AdicionarEditarRota(id = id))
                },
                viewModel = viewModel
            )
        }

        composable<AdicionarEditarRota> { navBackStackEntry ->
            val adicionarEditarRota = navBackStackEntry.toRoute<AdicionarEditarRota>()

            TelaAdicionarEditar(
                tarefaDAOImpl = tarefaDAOImpl,
                onBack = { navController.popBackStack() },
                id = adicionarEditarRota.id
            )
        }
    }
}