fun main() {
    list234()
}

/**
 * kotlinではif/whileは文(statement)ではなく、式(expression)なので、
 * if式を評価した結果はそのまま変数に代入したり、関数の戻り値として利用できる
 */

// まずは見慣れたこんな関数
fun printOddOrEvenNumberText(num:Int) {
    var text = ""
    if (num % 2 == 1) {
        text = "odd"
    } else {
        text = "even"
    }
    println(text)
}

// if を expressionとして使ってみる
fun printOddOrEvenNumberText2(num:Int) {

    // ifはexpressionなのでこのように書ける
    val text = if (num % 2 == 1) {
        "奇数"
    } else {
        "偶数"
    }
    println(text)
}

// さらに短く書いてみる
fun printOddOrEvenNumberText3(num:Int) {
    val text = if (num % 2 == 1) "奇数" else "偶数"
    println(text)
}

// 戻り値がある場合は、直接 if をreturn すれば値が返却される
fun printOddOrEvenNumberText4(num:Int):String {
    return if (num % 2 == 1) {
         "奇数"
    } else {
         "偶数"
    }
}

// whileも同様
fun printNumText(num: Int): String {
   return  when(num) {
        100 -> {
            "Equal to 100"
        }
        200 -> {
            "Equal to 200"
        }
        else -> {
           "Undefined Value"
        }
    }
}

/**
 * アクセサメソッドが不要
 */
class User1 {
    var name: String = ""
}

fun list222() {
    val user = User1()

    // 内部的にはnameのsetterを経由してnameに値が格納されている
    user.name = "abe"

    // 内部的にnameのgetterを経由してprintlnに値を渡している
    println(user.name)
}

// アクセサメソッドの衝突
class User11 {
    var name: String = ""

    // このようにアクセサメソッドを作成すると内部的に宣言されている定義と衝突しコンパイルエラーとなる
    // fun getName(): String {
    //   return this.name
    // }
}

// valでの定義はGetterのみ生成
class User2(id:Int) {
    val id: Int = id
    var name: String = ""
}

fun list225() {
    val user = User2(1)
    user.name = "abe"

    // これはコンパイルエラーとなる
    // user.id = 2
}

/**
 * lateinit
 * - インスタンス生成時に初期値は不要だがgetterを呼び出す時点では値が格納されている必要がある
 */
class User3 {
    lateinit var name: String
}

fun list227() {
    val user = User3()
    user.name = "takahashi"
    println(user.name)
}

/**
 * 拡張プロパティでgetter,setterの処理を拡張
 */
class User4 {
    lateinit var name: String
    val isValidName: Boolean
        get() = name != ""
}

fun list229() {
    val user = User4()
    user.name = "aaa"
    println(user.isValidName)
}

class User5 {
    var name: String = ""
        set(value) {
            // setterを拡張する場合は、field識別子に値を格納する
            field = if (value == "") {
                "Kotlin"
            } else {
                value
            }
        }
}

fun list2211() {
    val user = User5()
    user.name = ""
    println(user.name)
    user.name = "abc"
    println(user.name)
}

/**
 * オブジェクト比較
 *
 */

class User6 {
    val id:Int = 1
    val name = "Kotlin"
}

fun list232() {
    val userA = User6()
    val userB = User6()

    // toStringの結果
    println(userA.toString())
    println(userB.toString())

    // hashCode関数の結果
    println(userA.hashCode())
    println(userB.hashCode())

    // equals関数での比較
    println(userA == userB)

    // hashCode関数、equals関数での比較
    val set  = hashSetOf(userA)
    println(set.contains(userB))
}

class User62 {
    val id: Int = 1
    val name = "Kotlin"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as User62
        if (id != other.id) return false
        if (name != other.name) return false
        return true
    }

    override fun hashCode(): Int {
        return 31 * name.hashCode() + id
    }

    override fun toString(): String {
        return "User6(id=$id, name=$name)"
    }
}

fun list234() {
    val userA = User62()
    val userB = User62()
    println(userA.toString())
    println(userB.toString())
    println(userA == userB)
    val set = hashSetOf(userA)
    println(userA.hashCode())
    println(userB.hashCode())
    println(set.contains(userB))
}