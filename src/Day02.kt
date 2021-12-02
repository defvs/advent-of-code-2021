class Submarine1(
	var hpos: Int = 0,
	var depth: Int = 0,
) {
	fun runCommand(command: String) = command.split(' ').let {
		val argument = it[1].toInt()
		when (it[0]) {
			"forward" -> hpos += argument
			"down" -> depth += argument
			"up" -> depth -= argument
			else -> throw Exception("Unknown command \"${it[1]}\".")
		}
	}
}

class Submarine2(
	var hpos: Int = 0,
	var depth: Int = 0,
	var aim: Int = 0,
) {
	fun runCommand(command: String) = command.split(' ').let {
		val argument = it[1].toInt()
		when (it[0]) {
			"forward" -> {
				hpos += argument
				depth += argument * aim
			}
			"down" -> aim += argument
			"up" -> aim -= argument
			else -> throw Exception("Unknown command \"${it[1]}\".")
		}
	}
}

fun main() {
	fun part1(input: List<String>) = with(Submarine1()) {
		input.forEach(::runCommand)
		hpos * depth
	}
	
	fun part2(input: List<String>) = with(Submarine2()) {
		input.forEach(::runCommand)
		hpos * depth
	}
	
	val input = readInput("Day02")
	part1(input).println()
	part2(input).println()
}
