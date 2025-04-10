package br.edu.ifsp.hto.dataclass.domain

data class Tarefa(
    val id: Long,
    val titulo: String,
    val descricao: String?,
    val ehFinalizada: Boolean
)

// fake dados
val tarefa1 = Tarefa(
    1,
    "Tarefa 1",
    descricao = "Descrição da Tarefa 1",
    ehFinalizada = true
)

val tarefa2 = Tarefa(
    2,
    "Tarefa 2",
    descricao = null,
    ehFinalizada = false
)

val tarefa3 = Tarefa(
    3,
    "Tarefa 3",
    descricao = "Descrição da Tarefa 3",
    ehFinalizada = true
)