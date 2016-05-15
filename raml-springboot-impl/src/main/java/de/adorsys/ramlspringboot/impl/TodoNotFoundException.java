package de.adorsys.ramlspringboot.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Florian Hirsch
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No Todo with given id found")
public class TodoNotFoundException extends RuntimeException {

}
