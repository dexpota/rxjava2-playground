package me.destro.playground.rxjava2.subjects

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

fun main() {
    val source1 = Observable.interval(1, TimeUnit.SECONDS).map { "Seconds: $it" }
    val source2 = Observable.interval(100, TimeUnit.MILLISECONDS).map { "Milliseconds: $it" }.take(15)

    val subject = PublishSubject.create<String>()

    subject.subscribe({
        println("New item: $it")
    }, {
        println("On error called.")
    }, {
        println("On complete called.")
    })

    // the first source sends an infinite numbers of events
    source1.subscribe(subject)
    // but the second source will send only 15 events, after that it will call onComplete and the subject
    // will stop receiving events, because its onComplete event have been called.
    source2.subscribe(subject)

    Thread.sleep(4000)
}