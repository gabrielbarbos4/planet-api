package com.example.planetapi.common;

import com.fasterxml.jackson.databind.ObjectWriter;

public class ObjectMapper {
    public static final ObjectWriter jsonMapper = new com.fasterxml.jackson.databind.ObjectMapper().writer().withDefaultPrettyPrinter();
}
