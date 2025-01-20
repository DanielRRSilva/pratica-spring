import javax.swing.JOptionPane

fun mainzinho() {
    val texto = "Olá"

    println(texto + "mundo!")

    var descricao = "descrição"
    descricao = "Nova descrição"
    println(descricao)

    var inteiro: Int? = null
    var double: Double
    var string: String
    var boolean: Boolean

//    var somar = 10+inteiro!!

    println(inteiro)

    var nome : String? = null

    println(nome ?: "Não tem nome")
    nome = "Elvis"
    println("Nome: $nome")

    val n1 = 10
    val n2 = 15
    println("Resultado: ${n1 + n2}")

//    print("Digite seu nome:")
//    val nomeUsuario = readlnOrNull() ?: "Sem nome"

//    println("Seu nome é: ${nomeUsuario}")

    val nome2 = JOptionPane.showInputDialog("Digite seu nome")
    JOptionPane.showMessageDialog(null,"Nome: $nome2")
}

fun mainzinho2() {
    val listaUsuario = listOf("José, Maria")
    val listaUsuarioMutavel = mutableListOf<String>()
    listaUsuarioMutavel.addAll(listaUsuario)
    listaUsuarioMutavel.add("Marcio")
    listaUsuarioMutavel.add("Edu")
    println("primeiro indice: ${listaUsuario.get(0)}")
    println("primeiro indice: ${listaUsuario[0]}")
    println("primeiro indice: ${listaUsuario.first()}")
    println("ultimo indice: ${listaUsuario.last()}")

    println("--------------------------------------------------------")
    listaUsuarioMutavel.forEachIndexed {indice, daVez ->
        println("${indice}° $daVez")
    }
    listaUsuario.map { it.uppercase() }
    println(listaUsuario)

    for (daVez in listaUsuarioMutavel) {
        println(daVez)
    }

    for (indice in 1..<listaUsuarioMutavel.size) {
        println(listaUsuarioMutavel[indice])
    }
}

fun mainzinho3() {
    val valor = 10
    when (valor) {
        in 0 .. 10 -> println("Ta entre 0 e 10")
        11 -> println("é 11")
        else -> println("número errado")
    }
    val valor2 = 5
    val resultado = when(valor2){in 0..10-> "Isso" else-> "Aquilo"}
}

fun main() {
    val pessoa = Pessoa(
        nome = "daniel",
        email = "daniel@gmail.com"
    )
    pessoa.idade = 19
    println(pessoa)
}