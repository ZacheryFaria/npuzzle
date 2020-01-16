package zfaria.npuzzle

import com.google.common.hash.*
import java.nio.charset.Charset

class Node(val size: Int, var value: List<Int>) {

    var hashFunction = Hashing.farmHashFingerprint64()

    val zeroIndex = value.indexOf(0)

    private fun getX(n: Int): Int {
        return n % size
    }

    private fun getY(n: Int): Int {
        return n / size
    }

    fun isValid(): Boolean {
        val set = setOf(value)
        return set.size == value.size
    }


    override fun hashCode(): Int {
        return hashFunction.hashString(value.toString(), Charset.defaultCharset()).asInt()
    }
}
