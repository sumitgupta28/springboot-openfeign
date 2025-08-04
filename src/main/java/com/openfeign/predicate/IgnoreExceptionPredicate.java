package com.openfeign.predicate;

import com.openfeign.exception.MovieNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;
@Slf4j
public class IgnoreExceptionPredicate implements Predicate<Throwable> {

    @Override
    public boolean test(Throwable throwable) {
        log.debug("Condition predicate is called. Movie details is {} " , throwable instanceof MovieNotFoundException);
        return throwable instanceof MovieNotFoundException;
    }
}
