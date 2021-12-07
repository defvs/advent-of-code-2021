import kotlin.math.absoluteValue

fun main() {
	fun part1(input: List<String>): Int {
		val crabs = input[0].split(",").map { it.toInt() }
		val range = crabs.minOrNull()!!..crabs.maxOrNull()!!
		
		val costs = range.map { vPoint ->
			crabs.sumOf { it.minus(vPoint).absoluteValue }
		}
		
		return costs.minOrNull()!!
	}
	
	fun part2(input: List<String>): Int {
		val crabs = input[0].split(",").map { it.toInt() }
		val range = crabs.minOrNull()!!..crabs.maxOrNull()!!
		
		val costs = range.map { vPoint ->
			crabs.sumOf {
				it.minus(vPoint).absoluteValue.let {
					it.toBigInteger().pow(2).toInt().plus(it).div(2) // (n^2 + n) / 2
				}
			}
		}
		
		return costs.minOrNull()!!
	}
	
	val input = readInput("Day07")
	part1(input).println()
	part2(input).println()
}
