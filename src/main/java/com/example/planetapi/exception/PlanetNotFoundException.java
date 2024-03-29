package com.example.planetapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Planet not found")
public class PlanetNotFoundException extends RuntimeException { }
