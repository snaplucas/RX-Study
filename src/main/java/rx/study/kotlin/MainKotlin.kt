package rx.study.kotlin

import rx.Observable
import rx.Subscriber

fun main(args: Array<String>) {
    val myObservable = Observable.create(
            Observable.OnSubscribe<String> { sub ->
                sub.onNext("Hello, world!")
                sub.onCompleted()
            }
    )

    val mySubscriber = object : Subscriber<String>() {
        override fun onNext(s: String) {
            println(s)
        }

        override fun onCompleted() {}

        override fun onError(e: Throwable) {}
    }

    myObservable.subscribe(mySubscriber)
}