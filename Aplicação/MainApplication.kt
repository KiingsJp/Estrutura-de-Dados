package algoritmos_ordenacao

import java.util.Scanner

fun main() {
    println("\nAvaliação Oficial Estrutura de Dados\n")
    val scan = Scanner(System.`in`)
    do {
        printMenu()
        val opcao = scan.nextInt()

        if (opcao !in 1..7) {
            println("Digito inválido!\n")
            continue
        }

        when (opcao) {
            1 -> { chamarMetodo(scan, "Bubble") }

            2 -> { chamarMetodo(scan, "Insertion") }

            3 -> { chamarMetodo(scan, "Selection") }

            4 -> { chamarMetodo(scan, "Merge") }

            5 -> { chamarMetodo(scan, "Quick") }

            6 -> {
                println("\n---------- Vetores ordenados com cada método ----------")
                println("\nVETOR DE 10.000 NÚMEROS: ")
                temposOrdenados(10000)
                println("\nVETOR DE 30.000 NÚMEROS: ")
                temposOrdenados(30000)
                println("-------------------------------------------------------")

            }
        }
    } while (opcao != 7)
    println("\n-------- Obrigado! --------")
    scan.close()
}

