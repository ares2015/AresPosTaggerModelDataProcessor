package com.trainingdataprocessor.database;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.syntax.BigramData;
import com.trainingdataprocessor.data.syntax.StartTagEndTagPair;
import com.trainingdataprocessor.data.token.TokenTagData;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Oliver on 11/7/2016.
 */
public class TrainingDataDatabaseAccessorImpl implements TrainingDataDatabaseAccessor {

    private JdbcTemplate jdbcTemplate;

    public TrainingDataDatabaseAccessorImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertBigramData(BigramData bigramData) {

    }

    @Override
    public void insertStartTagEndTagPair(StartTagEndTagPair startTagEndTagPair) {

    }

    @Override
    public void populateBigramFrequencyData(BigramData bigramData) {

    }

    @Override
    public void populateBigramTag1FrequencyData(BigramData bigramData) {

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
    public void insertTokenTagData(TokenTagData tokenTagData) {

    }


}
