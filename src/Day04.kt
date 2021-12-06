data class Board(
	val numbers: List<List<Int>>,
) {
	val marks = Array(5) { BooleanArray(5) { false } }
	
	fun mark(number: Int) {
		for (i in 0 until 5)
			for (j in 0 until 5)
				if (numbers[i][j] == number) marks[i][j] = true
	}
	
	fun hasBingo(): Boolean {
		val transposed = marks.map { it.toList() }.toList().transpose()
		return (0 until 5).any { i -> marks[i].all { it } || transposed[i].all { it } }
	}
	
	fun sumOfUnmarked() =
		numbers.withIndex().sumOf { row ->
			row.value.withIndex().filterNot { col ->
				marks[row.index][col.index]
			}.sumOf { it.value }
		}
	
	override fun toString(): String =
		numbers.joinToString("\n") { row ->
			row.joinToString(" ") { col ->
				col.toString().padStart(2)
			}
		} + "\n" + marks.joinToString("\n") { it.joinToString(" ") { if (it) " X" else "  "} }
}

data class BingoGame(
	val boards: List<Board>,
	val numbers: List<Int>,
) {
	constructor(input: List<String>): this(
		input.drop(2).chunked(6) { boardStrings ->
			boardStrings.mapNotNull { boardLine ->
				boardLine.ifEmpty { null }?.chunked(3)?.map { it.trim().toInt() }
			}
		}.map { Board(it) },
		input[0].split(",").map { it.toInt() }
	)
}

fun main() {
	fun part1(input: List<String>): Int {
		val game = BingoGame(input)
		var lastNumber = 0
		for (drawnNumber in game.numbers) {
			if (game.boards.any { it.hasBingo() }) break
			lastNumber = drawnNumber
			game.boards.onEach { it.mark(drawnNumber) }
		}
		return game.boards.filter { it.hasBingo() }[0].sumOfUnmarked().times(lastNumber)
	}
	
	fun part2(input: List<String>): Int {
		val game = BingoGame(input)
		val remainingBoards = game.boards.toMutableList()
		var lastNumber = 0
		for (drawnNumber in game.numbers) {
			lastNumber = drawnNumber
			remainingBoards.onEach { it.mark(drawnNumber) }
			if (remainingBoards.size == 1 && remainingBoards[0].hasBingo()) break
			remainingBoards.removeAll { it.hasBingo() }
		}
		return remainingBoards[0].sumOfUnmarked().times(lastNumber)
	}
	
	val input = readInput("Day04")
	part1(input).println()
	part2(input).println()
}
