package com.trainingdataprocessor.database;

import com.trainingdataprocessor.calculator.BigramProbabilityCalculator;
import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.syntax.BigramData;
import com.trainingdataprocessor.data.syntax.StartTagEndTagPair;
import com.trainingdataprocessor.data.token.TokenTagData;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public void insertTag(String tag) {
        int tagFrequency = findTagFrequency(tag);
        boolean tagExistsInDB = tagFrequency > 0;
        tagFrequency ++;
        String sql = "";
        if(tagExistsInDB){
            sql = "update jos_nlp_tags set frequency = ? where tag = ?";
            jdbcTemplate.update(sql, new Object[]{tagFrequency, tag});
        }else {
            sql = "insert into jos_nlp_tags (tag, frequency) values(?,?)";
            jdbcTemplate.update(sql, new Object[]{tag, tagFrequency});
        }
    }

    @Override
    public void insertBigramData(BigramData bigramData) {
        int bigramFrequency = findBigramFrequency(bigramData);
        boolean bigramExistsInDB = bigramFrequency > 0;
        bigramFrequency++;
        bigramData.setBigramFrequency(bigramFrequency);
        int tag1Frequency = findTagFrequency(bigramData.getTag1());
        tag1Frequency++;
        double bigramProbability = BigramProbabilityCalculator.calculate(bigramFrequency, tag1Frequency);
        bigramData.setBigramProbability(bigramProbability);
        String bigram = bigramData.getTag1() + " " + bigramData.getTag2();
        String sql = "";
        if (bigramExistsInDB) {
            sql = "update jos_nlp_bigrams set frequency = ?, probability = ? where bigram = ?";
            jdbcTemplate.update(sql, new Object[]{bigramFrequency, bigramProbability, bigram});
        } else {
            sql = "insert into jos_nlp_bigrams (frequency, bigram, tag1, tag2, probability, is_tag1_constant, is_tag2_constant) values (?,?,?,?,?,?,?)";
            jdbcTemplate.update(sql, new Object[]{bigramFrequency, bigram, bigramData.getTag1(), bigramData.getTag2(), bigramProbability,
                    bigramData.isTag1Constant(), bigramData.isTag2Constant()});
        }
    }

    @Override
    public void insertStartTagEndTagPair(StartTagEndTagPair startTagEndTagPair) {
        String subPath = startTagEndTagPair.getSubPath();
        int subPathFrequency = findSubPathFrequencyForStartTagEndTagPair(subPath);
        boolean subPathExistsInDB = subPathFrequency > 0;
        subPathFrequency ++;
        String sql = "";
        if(subPathExistsInDB){
            sql = "update jos_nlp_start_tag_end_tag_pairs set frequency = ? where subpath = ?";
            jdbcTemplate.update(sql, new Object[]{subPathFrequency, subPath});
        }else {
            sql = "insert into jos_nlp_start_tag_end_tag_pairs (start_tag, end_tag, subpath, length, frequency, contains_constant) values (?,?,?,?,?,?)";
            jdbcTemplate.update(sql, new Object[]{startTagEndTagPair.getStartTag(), startTagEndTagPair.getEndTag(),
                    subPath, startTagEndTagPair.getLength(), subPathFrequency, startTagEndTagPair.containsConstant()});
        }
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

    @Override
    public void insertEncodedPath(String encodedPath) {

    }

    @Override
    public void insertEncodedSubPath(String encodedSubPath) {

    }

    @Override
    public void insertPath(String path) {

    }

    @Override
    public void insertSubPath(String subPath) {

    }

    @Override
    public void insertSentence(String sentence) {

    }

    private int findBigramFrequency(BigramData bigramData) {
        int bigramFrequency = 0;
        String bigram = bigramData.getTag1() + " " + bigramData.getTag2();
        final String sql = "select frequency from jos_nlp_bigrams where bigram=?";
        try {
            bigramFrequency = jdbcTemplate.queryForInt(sql, new Object[]{bigram});
        } catch (final EmptyResultDataAccessException e) {
            return 0;
        }
        return bigramFrequency;
    }

    private int findTagFrequency(String tag) {
        int tagFrequency = 0;
        final String sql = "select frequency from jos_nlp_tags where tag=?";
        try {
            tagFrequency = jdbcTemplate.queryForInt(sql, new Object[]{tag});
        } catch (final EmptyResultDataAccessException e) {
            return 0;
        }
        return tagFrequency;
    }

    private int findSubPathFrequencyForStartTagEndTagPair(String subPath){
        int startTagEndTagPairFrequency = 0;
        String sql = "select frequency from jos_nlp_start_tag_end_tag_pairs where subpath = ?";
        try {
            startTagEndTagPairFrequency = jdbcTemplate.queryForInt(sql, new Object[]{subPath});
        } catch (final EmptyResultDataAccessException e) {
            return 0;
        }
        return startTagEndTagPairFrequency;
    }


}