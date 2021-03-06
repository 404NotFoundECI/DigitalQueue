package edu.eci.arsw.digitalqueue.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.eci.arsw.digitalqueue.exception.NoTurnsInServiceException;

/**
 * TurnNotFoundAdvice
 */
@ControllerAdvice
public class TurnAlreadyCancelledAdvice {

    @ResponseBody
    @ExceptionHandler(NoTurnsInServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String noTurnsInQueueHandler(NoTurnsInServiceException exception) {
        return exception.getMessage();
    }

}