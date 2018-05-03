package xyz.liuzm.accumulation.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ObservableMain {

    /**
     * 用来测试 RXJAVA 的简单操作符
     * @param args
     */
    public static void main(String[] args) {
        Observable.create( new ObservableOnSubscribe<Integer>(){

            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                observableEmitter.onNext(2);
                observableEmitter.onNext(3);
                observableEmitter.onComplete();
                observableEmitter.onNext(4);
            }
        }).map(i -> i*10).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable disposable) {
                System.out.println(1);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });
    }

}
