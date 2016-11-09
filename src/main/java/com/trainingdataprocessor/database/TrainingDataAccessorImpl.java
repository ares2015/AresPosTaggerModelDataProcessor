package com.trainingdataprocessor.database;

import com.trainingdataprocessor.data.BigramData;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Oliver on 11/7/2016.
 */
public class TrainingDataAccessorImpl implements TrainingDataAccessor {

    private JdbcTemplate jdbcTemplate;

    public TrainingDataAccessorImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void populateBigramFrequencyData(BigramData bigramData) {

    }

    @Override
    public void populateBigramTag1FrequencyData(BigramData bigramData) {

    }
}
