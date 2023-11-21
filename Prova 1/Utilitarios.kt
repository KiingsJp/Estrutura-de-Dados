package algoritmos_ordenacao

import java.util.*

fun printArray(array: IntArray) {
    for (numero in array) {
        print("[$numero] ")
    }
    println()
}

fun printMenu() {
    println("\nMenu de Opções:")
    println("1 - Bubble Sort (Método Bolha)")
    println("2 - Insertion Sort (Método de Inserção)")
    println("3 - Selection Sort (Método de Seleção)")
    println("4 - Merge Sort")
    println("5 - Quick Sort")
    println("6 - Comparativo de Tempo de execução")
    println("7 - Sair\n")
    print("Escolha uma opção: ")
}

fun printEscolhaNumero() {
    println("\n1 - Números gerados aleatoriamente pela máquina")
    println("2 - Números digitados pelo usuário")
    print("Escolha uma opção: ")
}

fun ordenar(array: IntArray, metodo: String): Double {
    val tempo: Double?
    when (metodo) {
        "Bubble" -> {
            tempo = kotlin.system.measureNanoTime {
                bubbleSort(array)
            }.toDouble() / 1_000_000_000
            return tempo
        }

        "Selection" -> {
            tempo = kotlin.system.measureNanoTime {
                selectionSort(array)
            }.toDouble() / 1_000_000_000
            return tempo
        }

        "Insertion" -> {
            tempo = kotlin.system.measureNanoTime {
                insertionSort(array)
            }.toDouble() / 1_000_000_000
            return tempo
        }

        "Merge" -> {
            tempo = kotlin.system.measureNanoTime {
                mergeSort(array)
            }.toDouble() / 1_000_000_000
            return tempo
        }

        "Quick" -> {
            tempo = kotlin.system.measureNanoTime {
                quickSort(array)
            }.toDouble() / 1_000_000_000
            return tempo
        }
    }
    return 0.0
}

fun chamarMetodo(scan: Scanner, metodo: String) {
    print("\nQual a quantidade a ser ordenada? ")
    val quantidade = scan.nextInt()
    val array = IntArray(quantidade)

    printEscolhaNumero()
    val opcao = scan.nextInt()
    if (opcao !in 1..2) {
        println("Digito inválido!\n")
        return
    }

    if (opcao == 1) {
        val random = Random()
        for (i in 0..<quantidade) {
            array[i] = random.nextInt()
        }
    } else {
        for (i in 0..<quantidade) {
            print("Dígite o número da posição ${i + 1}: ")
            array[i] = scan.nextInt()
        }
    }
    print("\nAntes da ordenação: ")
    printArray(array)

    val tempo = ordenar(array, metodo)
    print("Depois da ordenação: ")

    printArray(array)
    println("Tempo gasto: ${String.format("%.5f", tempo)} segundos")
}

fun temposOrdenados(quantidade: Int) {
    val random = Random()

    val array = IntArray(quantidade)
    for (i in array.indices) {
        array[i] = random.nextInt()
    }

    val temposOrdenacao = listOf(
        "Insertion" to ordenar(array.copyOf(), "Insertion"),
        "Quick" to ordenar(array.copyOf(), "Quick"),
        "Merge" to ordenar(array.copyOf(), "Merge"),
        "Selection" to ordenar(array.copyOf(), "Selection"),
        "Bubble" to ordenar(array.copyOf(), "Bubble")
    )

    val tempos = temposOrdenacao.sortedBy { it.second }
    for ((tipo, tempo) in tempos) {
        println("$tipo - Tempo: ${String.format("%.5f", tempo)} segundos")
    }
}
