package rx.study.java;


import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

public class Main {
    public static void main(String[] args) {
        Observable<String> myObservable = Observable.create(sub -> {
            sub.onNext("Hello, world!");
            sub.onCompleted();
        });

        Observable<String> myObservable2 = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        );

        Subscription subscribe = Observable.just("Hello, world!").subscribe(System.out::println);

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }
        };

        myObservable.subscribe(mySubscriber);
    }
}
