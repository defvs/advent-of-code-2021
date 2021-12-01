fun <T> List<T>.zipWithMultipleNext(): List<List<T>> {
	if (size < 3) return emptyList()
	val result = mutableListOf<List<T>>()
	for (i in indices.toList().dropLast(2))
		result.add(listOf(get(i), get(i + 1), get(i + 2)))
	return result
}

fun main() {
	fun part1(input: List<String>) =
		input.map { it.toInt() }
			.zipWithNext()
			.count { it.second > it.first }
	
	fun part2(input: List<String>) =
		input.map { it.toInt() }
			.zipWithMultipleNext().map { it.sum() }
			.zipWithNext()
			.count { it.second > it.first }
	
	val input = readInput("Day01")
	part1(input).println()
	part2(input).println()
}
