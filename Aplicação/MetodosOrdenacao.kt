package algoritmos_ordenacao

fun quickSort(array: IntArray, left: Int = 0, right: Int = array.size - 1) {
    if (left < right) {
        val pivot = array[right]
        var i = left - 1
        var j = left
        while (j < right) {
            if (array[j] <= pivot) {
                i++
                val temp = array[i]
                array[i] = array[j]
                array[j] = temp
            }
            j++
        }
        val temp = array[i + 1]
        array[i + 1] = array[right]
        array[right] = temp

        val pivotIndex = i + 1
        quickSort(array, left, pivotIndex - 1)
        quickSort(array, pivotIndex + 1, right)

    }
}

fun mergeSort(array: IntArray) {
    if (array.size < 2) {
        return
    }

    val middle = array.size / 2
    val left = array.sliceArray(0..<middle)
    val right = array.sliceArray(middle..<array.size)

    mergeSort(left)
    mergeSort(right)

    merge(array, left, right)
}

fun merge(array: IntArray, left: IntArray, right: IntArray) {
    var i = 0
    var j = 0
    var k = 0

    while (i < left.size && j < right.size) {
        if (left[i] < right[j]) {
            array[k++] = left[i++]
        } else {
            array[k++] = right[j++]
        }
    }

    while (i < left.size) {
        array[k++] = left[i++]
    }

    while (j < right.size) {
        array[k++] = right[j++]
    }
}

fun selectionSort(array: IntArray) {
    for (i in 0..<array.size - 1) {
        var posic = i
        for (j in i + 1..<array.size) {
            if (array[j] < array[posic]) {
                posic = j
            }
        }
        val aux = array[posic]
        array[posic] = array[i]
        array[i] = aux
    }
}

fun insertionSort(array: IntArray) {
    for (i in 1..<array.size) {
        var j = i - 1
        while (j >= 0 && array[j] > array[j + 1]) {
            val aux = array[j + 1]
            array[j + 1] = array[j]
            array[j] = aux
            j--
        }
    }
}

fun bubbleSort(array: IntArray) {
    for (i in 0..<array.size - 1) {
        for (j in 0..<array.size - 1) {
            if (array[j] > array[j + 1]) {
                val aux = array[j + 1]
                array[j + 1] = array[j]
                array[j] = aux
            }
        }
    }
}
