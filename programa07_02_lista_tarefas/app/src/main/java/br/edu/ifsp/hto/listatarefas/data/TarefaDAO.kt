package br.edu.ifsp.hto.dataclass.data

import br.edu.ifsp.hto.listatarefas.domain.Tarefa

interface TarefaDAO {
    fun insert(tarefa: Tarefa)
    fun insertMulti(tarefas: MutableList<Tarefa>)
    fun update(tarefa: Tarefa)
    fun delete(tarefa: Tarefa)
    fun getAllTarefas(): List<Tarefa>
    fun getTarefaById(tarefaId: Long): Tarefa?
}