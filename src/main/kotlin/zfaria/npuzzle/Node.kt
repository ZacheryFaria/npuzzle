package zfaria.npuzzle

import com.google.common.hash.*
import java.nio.charset.Charset

class Node(val size: Int, var value: List<Int>) {

    var hashFunction = Hashing.farmHashFingerprint64()

    override fun hashCode(): Int {
        return hashFunction.hashString(value.toString(), Charset.defaultCharset()).asInt()
    }
}

//142604