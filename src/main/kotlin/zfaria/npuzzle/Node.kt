package zfaria.npuzzle

import com.google.common.base.Strings
import com.google.common.hash.*
import zfaria.npuzzle.heuristics.HeuristicScorer
import java.nio.charset.Charset

class Node(val size: Int, var value: List<Int>, var scorer: HeuristicScorer? = null, val parent: Node? = null, var steps: Int = 0) {

    @Suppress("UnstableApiUsage")
    private val hashFunction = Hashing.farmHashFingerprint64()

    val zeroIndex = value.indexOf(0)

    var score: Int = scorer?.score(this) ?: 0

    fun getX(n: Int): Int {
        return n % size
    }

    fun getY(n: Int): Int {
        return n / size
    }

    fun isValid(): Boolean {
        val set = setOf(value)
        return set.size == value.size
    }

    fun expand(): List<Node> {
        return listOf(expandDown(), expandUp(), expandLeft(), expandRight()).filterNotNull()
    }

    fun expandUp(): Node? {
        if (getY(zeroIndex) <= 0) {
            return null
        }
        val temp = value[zeroIndex]
        val newVal = value.toMutableList()

        newVal[zeroIndex] = newVal[zeroIndex - size]
        newVal[zeroIndex - size] = temp
        return Node(size, newVal, scorer, this, steps + 1)
    }

    fun expandDown(): Node? {
        if (getY(zeroIndex) >= size - 1) {
            return null
        }
        val temp = value[zeroIndex]
        val newVal = value.toMutableList()

        newVal[zeroIndex] = newVal[zeroIndex + size]
        newVal[zeroIndex + size] = temp
        return Node(size, newVal, scorer,this, steps + 1)
    }

    fun expandLeft(): Node? {
        if (getX(zeroIndex) <= 0) {
            return null
        }
        val temp = value[zeroIndex]
        val newVal = value.toMutableList()

        newVal[zeroIndex] = newVal[zeroIndex - 1]
        newVal[zeroIndex - 1] = temp
        return Node(size, newVal, scorer,this, steps + 1)
    }

    fun expandRight(): Node? {
        if (getX(zeroIndex) >= size - 1) {
            return null
        }
        val temp = value[zeroIndex]
        val newVal = value.toMutableList()

        newVal[zeroIndex] = newVal[zeroIndex + 1]
        newVal[zeroIndex + 1] = temp
        return Node(size, newVal, scorer, this, steps + 1)
    }

    fun getLength(): Int {
        var node = this
        var length = 0

        while (node.parent != null) {
            length++
            node = node.parent!!
        }
        return length
    }

    fun toBoard(): String {
        var builder = StringBuilder()

        for (i in value.indices) {
            if (i % size == 0 && i != 0)
                builder.append("\n")
            else if (i != 0)
                builder.append(" ")
            builder.append(value[i])
        }
        return builder.toString()
    }

    fun printFamily() {
        if (parent == null)
            return
        parent.printFamily()
        println(parent.toBoard())
        println(Strings.padStart("|", size, ' '))
        println(Strings.padStart("V", size, ' '))
    }

    override fun toString(): String {
        return "Node($steps, $value)"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Node) return false

        return value == other.value
    }

    override fun hashCode(): Int {
        return hashFunction.hashString(value.toString(), Charset.defaultCharset()).asInt()
    }
}
