package br.edu.ifsp.hto.listatarefas.navegacao

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import br.edu.ifsp.hto.dataclass.data.TarefaDAOImpl
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

    NavHost(navController = navController, startDestination = ListagemRota) {
        composable<ListagemRota> {
            TelaListagem(
                tarefaDAOImpl = tarefaDAOImpl,
                navegarParaTelaAdicionarEditar = { id ->
                    navController.navigate(AdicionarEditarRota(id = id))
                }
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