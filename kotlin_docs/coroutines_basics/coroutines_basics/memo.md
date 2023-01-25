# memo
- reference: https://kotlinlang.org/docs/coroutines-guide.html
 
## コルーチン
- 中断可能なインスタンス
- 特定のスレッドにバインドされない（軽量のスレッドに似ているがスレッドではない）

## 要約 キーワード
- 
- `launch` - coroutineビルダー
- `delay` - suspend関数 coroutineを中断できる
- `runBlocking` - coroutineと非coroutineの世界を橋渡し。 メソッドが現在のスレッドを待機のためにブロックする
- `coroutineScope` - coroutineのスコープを宣言。独自のスコープを定義できる。メソッドがスレッドの処理を一時停止