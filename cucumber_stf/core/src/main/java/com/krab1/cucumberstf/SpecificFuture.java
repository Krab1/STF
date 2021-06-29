package com.krab1.cucumberstf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SpecificFuture<T> {

    private static final int DEFAULT_TIMEOUT = 10;
    private final CompletableFuture<T> completableFuture;

    public SpecificFuture(){
        this.completableFuture = new CompletableFuture<>();
    }

    public T get(String exceptionMessage, long timeout, TimeUnit unit){
        try {
            return completableFuture.get(timeout, unit);
        }catch (InterruptedException | ExecutionException | TimeoutException e){
            throw new AssertionError(exceptionMessage + " - Event was not fetched withing timeout: " + timeout, e);
        }
    }

    public T get(String exceptionMessage){ return get(exceptionMessage, DEFAULT_TIMEOUT, TimeUnit.SECONDS); }

    public T get(){ return get(null, DEFAULT_TIMEOUT, TimeUnit.SECONDS); }

    public void complete(T record){ completableFuture.complete(record); }
}
