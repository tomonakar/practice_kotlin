import kotlinx.coroutines.*


fun main() {
    coroutinesAreLightWeight()
}


fun myFirstCoroutine() = runBlocking {
    launch {
        doWorld2()
    }
}

suspend fun doWorld()  {
    delay(1000L)
    println("world")
}

suspend fun doWorld2() = coroutineScope {
    launch {
        delay(1000L)
        println("World")
    }
    println("Hello")
}

fun scopeBuilderAndConCurrency() = runBlocking {
    doWorld3()
    println("done")
}

suspend fun doWorld3() = coroutineScope {
    launch {
        delay(2000L)
        println("world2")
    }
    launch {
        delay(1000L)
        println("world1")
    }
    println("Hello")
}


/**
 * An explicit job
 */
fun explicitJob() = runBlocking {
    val job = launch {
        delay(1000L)
        println("World")
    }
    println("Hello")
    job.join()  // 子のcoroutineが終わるまで待機
    println("Done")
}

/**
 * Coroutines are light-weight
 * - JVMスレッドより軽い
 * - JVMスレッドだとメモリを使い果たす場合でもcoroutineでは実行可能
 */

fun coroutinesAreLightWeight() = runBlocking {
    repeat(100_000) {
        launch {
            delay(5000L)
            println(".")
        }
    }
}
