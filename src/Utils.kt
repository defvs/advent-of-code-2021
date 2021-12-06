@file:Suppress("unused")

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Print this [Int]
 */
fun Int.println() = println(this)

/**
 * Transpose a 2D Array
 */
fun <T> List<List<T>>.transpose(): List<List<T>> {
	val ret: MutableList<List<T>> = ArrayList()
	for (i in 0 until this[0].size) {
		val col: MutableList<T> = ArrayList()
		for (row in this) {
			col.add(row[i])
		}
		ret.add(col)
	}
	return ret
}