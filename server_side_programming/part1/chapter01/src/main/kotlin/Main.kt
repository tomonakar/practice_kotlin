fun main() {
    list1535()
}

/**
 * 変数
 */
fun list131() {
    val id: Int = 100 // 変更不可
    var name: String = "abe" // 変更化

    // Unitは何もない型
    fun displayMessage(message: String): Unit {
        println(message)
    }
}

/**
 * 分岐
 */
fun ifExample(num: Int) {
    if (num == 100) {
        println("num is 100")
    }
}

fun ifExample2(num: Int) {
    if (num < 100) {
        println("Less than 100")
    } else if (num == 100) {
        println("Equal to 100")
    } else {
        println("Greater than 100")
    }
}

/**
 * when: switch文やcase文
 */
fun whenExample(num: Int) {
    when (num) {
        100 -> {
            println("Equal to 100")
        }

        200 -> {
            println("Equal to 200")
        }

        else -> {
            println("Undefined Value")
        }
    }
}

// whenの条件式を以下のように書くこともできる
fun whenExample2(num: Int) {
    when {
        num < 100 -> {
            println("Less than 100")
        }

        num == 100 -> {
            println("Equal to 100")
        }

        else -> {
            println("Greater than 100")
        }
    }
}

/**
 * ループ
 */
// while
fun list1511() {
    var i = 1
    while (i < 10) {
        println("i is $i")
        i++
    }

    // for
    for (i in 1..10) {
        println("i is $i")
    }

    // in 開始 until 終了 step 増加量
    for (i in 1 until 10 step 2) {
        println("i is $i")
    }

    // コレクションの値を順に取り出す
    var list = listOf(1, 2, 5, 6, 10)
    for (i in list) {
        println("i is $i")
    }
}

/**
 * クラス
 */
class Human {
    fun showName(name: String) {
        println(name)
    }
}

fun list1516() {
    val human = Human()
    human.showName("Takahara")

}

/**
 * 継承
 * - 継承させたいクラスにはopenキーワードを付ける
 * - オーバーライドさせたい関数もopenを付ける
 */
open class Animal(val name: String) {
    fun showName() = println("name is $name")
    open fun cries() = println("")
}

// クラス名：クラス名で継承
class Dog(name: String, age: Int) : Animal(name) {
    override fun cries() = println("bowwow")
}

// シールドクラスで継承を制限
sealed class Platform {
    abstract fun showName()
}


// 同一ファイル内の定義であれば、クラスは継承可能
class AndroidPlatform : Platform() {
    override fun showName() {
        println("Android.")
    }
}

class IosPlatform : Platform() {
    override fun showName() {
        println("iOS.")
    }
}

/**
 * インターフェース
 */
interface Greeter {
    fun hello()
}

class GreeterImpl : Greeter {
    override fun hello() {
        println("Hello.")
    }
}

/**
 * コレクション
 */

// List
fun list1526() {
    val intList: List<Int> = listOf<Int>(1, 2, 3)
    println(intList)
    println(intList[1])

    val stringList: List<String> = listOf<String>("one", "two", "three")
    println(stringList)
    println(stringList[1])
}

// 型推論で<>を省略
fun list1527() {
    val intList = listOf(1, 2, 3)
    val stringLst = listOf("a", "b", "c")
    println(intList)
    println(stringLst)
}

// Listを更新
fun list1528() {
    val immutableList: List<Int> = listOf(1, 2, 3)
    // immutableList.add(4)  <- コンパイルエラー

    val mutableList: MutableList<Int> = mutableListOf(1, 2, 3)
    mutableList.add(4)
}

// Map
fun list1529() {
    val map: Map<Int, String> = mapOf(1 to "one", 2 to "two", 3 to "three")
    println(map)
    println(map[2])
}

// containsKey
fun list1530() {
    val map = mapOf(1 to "one", 2 to "two", 3 to "three")
    println(map.containsKey(3))
    println(map.containsKey(4))
}

fun list1531() {
    val immutableMap: Map<Int, String> = mapOf(1 to "one", 2 to "2", 3 to "three")
    // immutableMap[4] = "four"

    val mutableMap: MutableMap<Int, String> = mutableMapOf(1 to "one", 2 to "2", 3 to "three")
    mutableMap[4] = "four"
    println(mutableMap)
}

// Set: 重複しないリスト、順序の概念を持たない
fun list1532() {
    val set = setOf("one", "two", "three", "four")
    println(set)
}

fun list1534() {
    val set: Set<String> = setOf<String>("one", "two", "three")
    println(set.contains("three"))
    println(set.contains("four"))
}

fun list1535() {
    val immutableSet = setOf("one", "two")
    // immutableSet.add("three")

    val mutableSet = mutableSetOf("one", "two")
    mutableSet.add("three")
    println(mutableSet)
}
