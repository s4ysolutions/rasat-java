package solutions.s4y.rasat;

import java.util.HashSet;
import java.util.Set;

public class Observable<T> {
    private final Set<Handler<T>> handlers = new HashSet<>();
    T value;

    public Observable(T value) {
        this.value = value;
    }

    public Observable() {
        this.value = null;
    }

    void remove(Handler<T> handler) {
        synchronized (handlers) {
            handlers.remove(handler);
        }
    }

    public Disposable<T> subscribe(Handler<T> handler) {
        synchronized (handlers) {
            handlers.add(handler);
        }
        return new Disposable<>(handler, this);
    }

    public T value() {
        return value;
    }

    Set<Handler<T>> handlers() {
        Set<Handler<T>> ret;
        synchronized (handlers)  {
            ret = new HashSet<>(handlers);
        }
        return ret;
    }
}
