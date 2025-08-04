package com.openfeign.predicate;

import com.openfeign.exception.MovieNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;
@Slf4j
public class RecordFailurePredicate implements Predicate<Throwable> {
    @Override
    public boolean test(Throwable throwable) {
        log.debug("Exception predicate is called. throwable instanceof MovieNotFoundException {} ", throwable instanceof MovieNotFoundException);
        return throwable instanceof MovieNotFoundException;
    }
}
