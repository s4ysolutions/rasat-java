# Rasat java

A lightweight implementation of pub-sub/observer pattern for Java.

The idea is borrowed from iOS [Rasat](https://github.com/gokselkoksal/Rasat) project and 
replicates its concepts.

## Components

### Channel

Channel is a bus the producer can transmit the values to

```
Channel ch = new Channel<Long>()
...
ch.broadcast(1)
ch.broadcast(2)
```

### Observable

The channel has an observer property that passes the values to
all handlers added to it

```
Disposable disposable = ch.observable.add(new Handler(){
   void handle(T event){
     ...
   };
)
```

### Disposable and DisposableBag

To remove the handlers from the observable call `dispose` method of every
disposable or add the disposable to DisposableBag object and dispose them all at once

```
DisposableBag bag = DisposableBag()
...
bag.add(disposable1)
bag.add(disposable2)
...
bag.dispose()
```

## Threading

Rasat runs all the handler in the same thread the producer broadcasted
the value sequentially one by one. 
