package me.destro.playground.rxjava2.observables.creation

import io.reactivex.Observable

fun main() {
    val source = Observable.create<String> { emitter ->
        try {
            emitter.onNext("Alpha")
            emitter.onNext("Beta")
            emitter.onNext("Gamma")
            emitter.onNext("Delta")
            emitter.onNext("Epsilon")
            emitter.onComplete()
        } catch (e: Throwable) {
            emitter.onError(e)
        }
    }
    source.subscribe { s -> println("RECEIVED: $s") }
}