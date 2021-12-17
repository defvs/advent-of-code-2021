fun main() {
	fun part1(input: List<String>): Int {
		val charMap = mapOf(
			'(' to ')',
			'[' to ']',
			'{' to '}',
			'<' to '>',
		)
		val scoreMap = mapOf(
			')' to 3,
			']' to 57,
			'}' to 1197,
			'>' to 25137,
		)
		return input.sumOf { line ->
			val stack = ArrayDeque<Char>(line.length)
			line.firstOrNull {
				when (it) {
					'[', '(', '{', '<' -> stack.addLast(it)
					']', ')', '}', '>' -> {
						val elem = stack.removeLastOrNull()
						if (charMap[elem] != it)
							return@firstOrNull true
					}
					else -> throw Exception("Invalid character.")
				}
				return@firstOrNull false
			}?.let { scoreMap[it] } ?: 0
		}
	}
	
	fun part2(input: List<String>): Long {
		val charMap = mapOf(
			'(' to ')',
			'[' to ']',
			'{' to '}',
			'<' to '>',
		)
		val scoreMap = mapOf<Char, Long>(
			')' to 1,
			']' to 2,
			'}' to 3,
			'>' to 4,
		)
		return input.mapNotNull { line ->
			val stack = ArrayDeque<Char>(line.length)
			line.firstOrNull {
				when (it) {
					'[', '(', '{', '<' -> stack.addLast(it)
					']', ')', '}', '>' -> {
						val elem = stack.removeLastOrNull()
						if (charMap[elem] != it)
							return@mapNotNull null
					}
					else -> throw Exception("Invalid character.")
				}
				return@firstOrNull false
			}
			stack.toList()
		}.map {
			it.reversed().fold(0L) { acc, char ->
				acc.times(5) + (scoreMap[charMap[char]] ?: 0)
			}
		}.sorted().let { it[it.size / 2] }
	}
	
	val input = readInput("Day10")
	part1(input).println()
	part2(input).println()
}
