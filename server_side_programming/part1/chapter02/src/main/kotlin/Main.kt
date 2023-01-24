fun main() {
list2712()

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
 * Kotlinのオブジェクト比較の機能を利用することで,データクラスでボイラープレートを減らせる
 * - toString
 * - hashCode
 * - equals
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

/**
 * データクラスの定義
 * クラス名の前に `data class` キーワードをつけてインスタンスを作成すると、
 * それぞれのプロパティのフィールドと、それに対する以下のメソッドが作られる
 * - アクセサメソッド（valで定義した場合は, getterのみ）
 * - equals/toString/hashCode/componentN/copy
 */

// データクラスはコンストラクタ定義が必須のため、生成時に値を渡す必要がある
data class User7(val id: Int, var name:String)

// equalsは同じ値をもったクラスのインスタンスを比較しbooleanを返す
fun list237() {
    val user = User7(1, "abe")
    val same = User7(1, "abe")
    val other = User7(2, "abe")

    println(user == same)
    println(user == other)
}

// hashCode
fun list238() {
    val user = User7(1, "abe")
    val same = User7(1, "abe")
    val other = User7(2, "abe")

    println("user=${user.hashCode()}")
    println("same=${same.hashCode()}")
    println("other=${other.hashCode()}")

    val set = hashSetOf(user)
    println(set.contains(same))
    println(set.contains(other))
}

// toString
fun list239() {
    val user = User7(1, "abe")
    println(user.toString())
}

// componentN
// 順番を指定してプロパティの値を取得したい場合に使う
fun list2310() {
    val user = User7(1, "abe")
    println(user.component1())
    println(user.component2())
}

// copy
fun list2311() {
    val user = User7(1, "abe")
    val updated = user.copy(1, "tanaka")
    println(updated.toString())
}

// データクラスで拡張プロパティを使う
data class User72(val id: Int, var name: String) {
    val isValidName: Boolean
        get() = name != ""
}

fun list2312() {
    val a = User72(1, "abe")
    println(a.isValidName)
}

/**
 * デフォルト引数と名前付き引数で関数呼び出しをシンプルにできる
 */
fun printUserInfo(id:Int, name:String = "default") {
    println("id=$id, name=$name")
}

/**
 * 名前付き引数
 */
data class User9(val id: Int, val nam: String = "default", val age:Int)

fun list247(){
    val user = User9(id = 1, age = 30)
    println(user.toString())
}

/**
 * 関数型と高階関数、タイプエイリアスでロジックを再利用しやすくする
 */
fun list251() {
    // 関数を値として記述しているものを関数リテラルという
    // {}の中に引数と処理を記述する方式をラムダ式という
    val calc: (Int,Int) -> Int = {num1: Int, num2:Int -> num1 + num2}
    println(calc(10,4))
}

// 型推論を利用しラムダ式の引数の型を省略して書ける
fun list253() {
    val calc: (Int, Int) -> Int = {num1, num2 -> num1 + num2}
    println(calc(10, 8))
}

// 引数が１つの場合は引数名も省略可能
// 引数名を省略した場合、引数は暗黙的に `it`で扱われる
fun list254() {
    val squared:(Int) -> Int = {it * it}
    println(squared(10))
}

// 無名関数（関数リテラルのもう１つの書き方）
// 基本はラムダ式を使うが、戻り値の型を明示的に示す必要がある時は、無名関数を利用する場合もある
fun list255() {
    val squared:(Int) -> Int = fun(num:Int):Int {return num * num}
    println(squared(20))
}

/**
 * 高階関数
 */
fun printCalcResult(num1:Int, num2:Int, calc:(Int,Int) -> Int) {
    val result = calc(num1, num2)
    println(result)
}

fun list257() {
    printCalcResult(10,20,{ num1, num2 -> num1 + num2 })
    printCalcResult(10,20,{ num1, num2 -> num1 * num2 })
}


fun printAdditionResult(x:Int, y:Int) {
    println("足し算の結果")
    printCalcResult(x,y, {num1, num2 -> num1 + num2})
}

fun printMultiplicationResult(x: Int, y: Int) {
    printCalcResult(x, y, {num1, num2 -> num1 * num2})
}

fun list259() {
    // 一番後ろで関数リテラルを渡す場合は、見通しがよくなるので、()の外に記述することが推奨されている
    printCalcResult(10, 20) { num1, num2 ->
        num1 + num2
    }
}


/**
 * タイプエイリアス
 */
typealias Calc = (Int, Int) -> Int
fun printCalcResult3(num1:Int, num2:Int, calc:Calc) {
    val result = calc(num1, num2)
    println(result)
}

/**
 * 拡張関数で柔軟にロジックを追加
 * - 既存のクラスに対して、関数を追加したかのように処理を記述できる
 * - thisで拡張関数を実行しているクラス自身を参照可能
 * - スコープは通常の関数と同様に扱える
 * - private修飾子をつけることでスコープを絞ることも可能
 */

// Intを拡張して二乗した値の結果を返す処理をIntを拡張して追加
fun Int.square():Int = this * this

fun list262() {
    println(40.square())
}


/**
 * スコープ関数でオブジェクトへの処理をシンプルにできる
 * - with/run/let/apply/also
 */

/**
 * with - 複数の処理をまとめて実行
 */
// まずは普通にかく
fun list271() {
    val list = mutableListOf<Int>()
    for (i in 1..10) {
        if (i % 2 == 1) list.add(i)
    }
    val oddNumbers = list.joinToString(separator = " ")
    println(oddNumbers)
}

fun list272() {
    // withの第一引数にレシーバーオブジェクト
    // 第二引数にレシーバーオブジェクトを処理し任意の型を返す関数を渡す
    // thisでレシーバーにアクセスできる
    val oddNumbers = with(mutableListOf<Int>()) {
        for(i in 1..10) {
            if (i % 2 == 1) this.add(i)
        }
        this.joinToString(separator = " ")
    }
    println(oddNumbers)
}


// withの中で利用するthisは省略可能
fun list273() {
    val oddNumbers = with(mutableListOf<Int>()) {
        for (i in 1..10) {
            if (i % 2 == 1) add(i)
        }
        joinToString(separator = " ")
    }
    println(oddNumbers)
}

/**
 * run - Nullableなオブジェクトに複数の処理をまとめて書く
 * withと似てるが、レシーバーオブジェクトを引数に取るのではなく、レシーバーオブジェクトに対してノイ拡張関数として引数を実装する
 * thisの省略もwith同様可能
 * nullableなオブジェクトを扱う時に便利
 */
// 普通に使う場合
fun list274() {
    val oddNumbers = mutableListOf<Int>().run {
        for (i in 1..10) {
            if (i % 2 == 1) this.add(i)
        }
        this.joinToString(separator = ",")
    }
    println(oddNumbers)
}

// Nullableなオブジェクトで利用
data class User12(var name: String)
fun getUserString(user: User12?, newName: String): String? {
    return user?.run {
        name = newName
        toString()
    }
}

/**
 * let - Nullableなオブジェクトに名前をつけて処理を行う
 */
fun list276() {
    // listという名前でnullAbleなレシーバオブジェクトを扱っている
    /* ただしこの例ではlist.が冗長な書き方になるので、runでthisを省略してかくとkotlinらしい */ var oddNumbers = mutableListOf<Int>().let { list ->
        for (i in 1..10) {
            if (i % 2 == 1) list.add(i)
        }
        list.joinToString(separator = " ")
    }
    println(oddNumbers)
}

// 以下のようなNullableなオブジェクトの時に、letは有効
data class User(val name: String)
fun createUser(name: String?): User? {
    return name?.let{n -> User(n)}
}

fun list278() {
    println(createUser("abe"))
    println(createUser(null))
}

// createUserをletを使わうずに記述すると以下のようになる
// このように if (xxx != null) ...が出てきたらletが利用される場面
fun createUser2(name: String?): User? {
    return if (name != null) User(name) else null
}


// さらにletはレシーバを省略して暗黙の名前でレシーバを扱うことができる
fun createUser3(name: String?): User? {
    return name?.let{User(it)}
}

/**
 * apply - オブジェクトに変更を加えて返す
 * 戻り値としてレシーバオブジェクト自体を返却する
 */
fun list2711() {
    val oddNumbers = mutableListOf<Int>().apply{
        for (i in 1..10) {
            if (i % 2 == 1) this.add(i)
        }
        this.joinToString(separator = " " )
    }
    println(oddNumbers)
}


data class User10(var id: Int, var name: String, var address: String)

fun list2712() {
    fun getUser(id: Int):User10 {
        return User10(1, "abe", "Tokyo")
    }
    fun updateUser(newId:Int, newName:String, newAddress:String) {
        var address = ""
        val user = getUser(newId).apply {
            // applyもthisでレシーバーオブジェクトにアクセス可能
            this.id = newId
            // thisの省略も可能
            name = newName
            // thisを省略した場合、レシーバーオブジェクトのプロパティ名と同じ名前のローカル変数があると,
            // ローカル変数に値が代入されレシーバーオブジェクトは更新されない
            address = newAddress
        }
        println(user)
    }
    updateUser(100, "hello", "saitama")
}

/**
 * also - オブジェクトに変更を加えて返す（名前をつけて返す）
 * applyとほぼ一緒だが、レシーバーに名前を付与できる
 */

fun list2717() {
    fun getUser(id: Int):User10 {
        return User10(1, "abe", "Tokyo")
    }

    // レシーバーをuという名前で扱う
    fun updateUser(newId: Int, newName: String, newAddress: String) {
        val user = getUser(newId).also { u ->
            u.id=newId
            u.name =newName
            u.address = newAddress
        }
        println(user)
    }

    // レシーバー名を省略して暗黙のitを利用
    fun updateUser2(newId: Int, newName:String, newAddress: String) {
        val user2 = getUser(newId).also {
            it.id = newId
            it.name = newName
            it.address = newAddress
        }
        println(user2)
    }
}

/**
 * 演算子のオーバーロード
 */
data class Num(val value:Int) {
    operator fun plus(num:Num): Num{
        return Num(value + num.value)
    }

    operator fun compareTo(num: Num) :Int {
        return value.compareTo(num.value)
    }
}
fun list282() {
    val num = Num(10) + Num(1)
    println(num)
}

fun list284() {
    val greaterThan = Num(5) > Num(1)
    val lessThan = Num(5 ) < Num(1)
    println(greaterThan)
    println(lessThan)
}
