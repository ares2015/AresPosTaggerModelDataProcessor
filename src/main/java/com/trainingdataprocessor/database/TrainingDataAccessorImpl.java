package com.trainingdataprocessor.database;

import com.trainingdataprocessor.data.BigramData;
import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Oliver on 11/7/2016.
 */
public class TrainingDataAccessorImpl implements TrainingDataAccessor {

    private JdbcTemplate jdbcTemplate;

    public TrainingDataAccessorImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertSemanticData(SemanticExtractionData semanticExtractionData) {
        final String sql = "insert into jos_nlp_semantic_data (atomic_subject,extended_subject,atomic_verb_predicate,extended_verb_predicate," +
                "atomic_noun_predicate,extended_noun_predicate) values (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{semanticExtractionData.getAtomicSubject(), semanticExtractionData.getExtendedSubject(),
                semanticExtractionData.getAtomicVerbPredicate(),
                semanticExtractionData.getExtendedVerbPredicate(), semanticExtractionData.getAtomicNounPredicate(),
                semanticExtractionData.getExtendedNounPredicate()});
    }

    @Override
    public void populateBigramFrequencyData(BigramData bigramData) {

    }

    @Override
    public void populateBigramTag1FrequencyData(BigramData bigramData) {

    }
}
