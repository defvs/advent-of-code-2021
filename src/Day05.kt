import kotlin.math.max

data class Vent(
	val points: List<Pair<Int, Int>>,
	val x: List<Int>,
	val y: List<Int>,
) {
	companion object Constructor {
/*		fun fromString(string: String) =
			string.split("->")
				.map { s -> s.trim().split(",").map { it.toInt() } }
				.let { Vent(it[0][0] toward it[1][0], it[0][1] toward it[1][1]) }*/
		
		fun fromString(string: String) =
			string.split("->")
				.map { s -> s.trim().split(",").map { it.toInt() } }
				.let {
					val x = (it[0][0] toward it[1][0]).toList()
					val y = (it[0][1] toward it[1][1]).toList()
					val max = maxOf(x.size, y.size)
					Vent(x.padWith(max, x[0]).zip(y.padWith(max, y[0])), x, y)
				}
	}
}

fun main() {
	fun part1(input: List<String>): Int {
		val vents = input.map { Vent.fromString(it) }
			.filter { it.x.first() == it.x.last() || it.y.first() == it.y.last() }
		val gridMax = vents.maxOf { vent -> vent.points.maxOf { max(it.first, it.second) } + 1 }
		val grid = Array(gridMax) { Array(gridMax) { 0 } }
		
		vents.forEach { vent ->
			vent.points.forEach { grid[it.second][it.first] += 1 }
		}
		
		return grid.sumOf { it.count { it >= 2 } }
	}
	
	fun part2(input: List<String>): Int {
		val vents = input.map { Vent.fromString(it) }
		val gridMax = vents.maxOf { vent -> vent.points.maxOf { max(it.first, it.second) } + 1 }
		val grid = Array(gridMax) { Array(gridMax) { 0 } }
		
		vents.forEach { vent ->
			vent.points.forEach { grid[it.second][it.first] += 1 }
		}
		
		return grid.sumOf { it.count { it >= 2 } }
	}
	
	val input = readInput("Day05")
	part1(input).println()
	part2(input).println()
}
