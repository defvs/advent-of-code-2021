fun main() {
	fun calcFishes(input: List<String>, time: Int): Long {
		var ages = Array(9) { 0L }
		input[0].split(",").map { it.toInt() }.forEach { ages[it] += 1L }
		
		for (i in 1..time) {
			ages = ages.rotate(1)
			ages[6] += ages[8]
		}
		
		return ages.sum()
	}
	
	fun part1(input: List<String>) = calcFishes(input, 80)
	fun part2(input: List<String>) = calcFishes(input, 256)
	
	val input = readInput("Day06")
	part1(input).println()
	part2(input).println()
}
