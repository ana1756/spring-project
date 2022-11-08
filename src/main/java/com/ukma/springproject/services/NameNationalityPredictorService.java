package com.ukma.springproject.services;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface NameNationalityPredictorService {
    String getNationality(String query) throws JsonProcessingException;
}
