package zfaria.npuzzle

import com.google.common.base.Strings
import com.google.common.hash.Hashing
import java.nio.charset.Charset

class Node(private val size: Int, var value: List<Int>, var scorer: Scorer? = null, private val parent: Node? = null, var steps: Int = 0) {

    @Suppress("UnstableApiUsage")
    private val hashFunction = Hashing.farmHashFingerprint64()

    val zeroIndex = value.indexOf(0)

    var score: Int = scorer?.invoke(this) ?: 0

    private val hash = hashFunction.hashString(value.toString(), Charset.defaultCharset()).asInt()

    fun getX(n: Int): Int {
        return n % size
    }

    fun getY(n: Int): Int {
        return n / size
    }

    fun expand(): List<Node> {
        return listOfNotNull(expandDown(), expandUp(), expandLeft(), expandRight())
    }

    fun expandDir(delta: Int, index: (Int) -> Int): Node? {
        if (index(zeroIndex) <= 0 && delta < 0) {
            return null
        } else if (index(zeroIndex) >= size - 1 && delta > 0) {
            return null
        }
        val temp = value[zeroIndex]
        val newVal = value.toMutableList()

        newVal[zeroIndex] = newVal[zeroIndex + delta]
        newVal[zeroIndex + delta] = temp
        return Node(size, newVal, scorer, this, steps + 1)
    }

    fun expandUp(): Node? {
        return expandDir(-size, this::getY)
    }

    fun expandDown(): Node? {
        return expandDir(size, this::getY)
    }

    fun expandLeft(): Node? {
        return expandDir(-1, this::getX)
    }

    fun expandRight(): Node? {
        return expandDir(1, this::getX)
    }

    private fun numWidth() = value.fold(0, { w, i -> if (i.toString().length > w) i.toString().length else w })

    fun toBoard(): String {
        val builder = StringBuilder()

        val width = numWidth()

        builder.append("[")
        for (i in value.indices) {
            if (i % size == 0 && i != 0)
                builder.append("]\n[")
            else if (i != 0)
                builder.append(" ")
            builder.append(Strings.padStart(value[i].toString(), width, ' '))
        }
        return builder.append("]").toString()
    }

    fun printFamily() {
        if (parent == null) {
            return
        }
        parent.printFamily()
        println(parent.toBoard())
        println(Strings.padStart("|", ((numWidth() + 1) * size) / 2 + 1, ' '))
        println(Strings.padStart("V", ((numWidth() + 1) * size) / 2 + 1, ' '))
    }

    override fun toString(): String {
        return "Node($steps, $value)"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Node) return false

        return value == other.value
    }

    override fun hashCode(): Int {
        return hash
    }
}
