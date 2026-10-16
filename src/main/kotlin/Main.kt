fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage: sort <csv> | search <csv> <target> | slice <csv> <from> <to>")
        return
    }

    try {
        when (args[0]) {
            "sort" -> {
                require(args.size >= 2) { "Usage: sort <comma-separated-ints> [--desc]" }
                val array = parseCsvInts(args[1])
                val descending = args.contains("--desc")
                val result = sortedCopy(array, descending)
                println(result.joinToString(","))
            }

            "search" -> {
                require(args.size == 3) { "Usage: search <sorted-comma-separated-ints> <target>" }
                val array = parseCsvInts(args[1])
                val target = args[2].toInt()

                when (val result = search(array, target)) {
                    is SearchResult.Found -> println("Found at index: ${result.index}")
                    is SearchResult.Missing -> println("Missing. Insertion point: ${result.insertionPoint}")
                }
            }

            "slice" -> {
                require(args.size == 4) { "Usage: slice <comma-separated-ints> <fromInclusive> <toExclusive>" }
                val array = parseCsvInts(args[1])
                val from = args[2].toInt()
                val to = args[3].toInt()

                val result = sliceCopy(array, from, to)
                println(result.joinToString(","))
            }

            else -> println("Unknown command: ${args[0]}")
        }
    } catch (e: IllegalArgumentException) {
        // Понятное сообщение пользователю при ошибках валидации
        System.err.println("Error: ${e.message}")
    } catch (e: Exception) {
        System.err.println("Unexpected error: ${e.message}")
    }
}

// Вспомогательная функция для превращения "3,1,2" в IntArray
private fun parseCsvInts(input: String): IntArray {
    if (input.isBlank()) return intArrayOf()
    return input.split(",")
        .map { it.trim().toInt() }
        .toIntArray()
}