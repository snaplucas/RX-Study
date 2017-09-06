package rx.study.java;


import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

public class Main {


    public static void main(String[] args) {
        Observable<String> myObservable = Observable.create(sub -> {
            sub.onNext("Hello, world!");
            sub.onCompleted();
        });

        Observable<String> myObservable2 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> sub) {
                sub.onNext("Hello, world!");
                sub.onCompleted();
            }
        });

        Subscription subscription = Observable.just("Hello, world!").subscribe(System.out::println);

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

        Observable.just("Hello, world!").map(s -> s + " -Dan").subscribe(System.out::println);

        Observable.just("Hello, world!").map(String::hashCode).subscribe(i -> System.out.println(Integer.toString(i)));

        Observable.just("Hello, world!").map(String::hashCode).map(i -> Integer.toString(i)).subscribe(System.out::println);

        Integer[] numbers = { 0, 1, 2, 3, 4, 5 };
        Observable numberObservable = Observable.from(numbers);


//        java 7
        numberObservable.subscribe(
                new Action1<Integer>() {
                    @Override
                    public void call(Integer incomingNumber) {
                        System.out.println(incomingNumber);
                    }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable error) {
                        System.out.println("Error in synchronous observable");
                    }
                },
                new Action0() {
                    @Override
                    public void call() {
                        System.out.println("This observable is finished");
                    }

                }
        );

//        java 8
        numberObservable.subscribe(
                (incomingNumber) -> System.out.println("incomingNumber " + incomingNumber),
                (error) -> System.out.println("Something went wrong" + ((Throwable)error).getMessage()),
                () -> System.out.println("This observable is finished")
        );
    }
}
