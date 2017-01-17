package com.trainingdataprocessor.database;

import com.trainingdataprocessor.calculator.BigramProbabilityCalculator;
import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.syntax.BigramData;
import com.trainingdataprocessor.data.syntax.SubPathData;
import com.trainingdataprocessor.data.token.TokenDatabaseData;
import com.trainingdataprocessor.data.token.TokenTagData;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by Oliver on 11/7/2016.
 */
public class TrainingDataDatabaseAccessorImpl implements TrainingDataDatabaseAccessor {

    private final static Logger LOGGER = Logger.getLogger(TrainingDataDatabaseAccessorImpl.class.getName());


    private JdbcTemplate jdbcTemplate;

    public TrainingDataDatabaseAccessorImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertTag(String tag) {
        FrequencyIdPair frequencyIdPair = findTagFrequency(tag);
        boolean tagExistsInDB = frequencyIdPair.id > 0;
        frequencyIdPair.frequency++;
        String sql = "";
        if (tagExistsInDB) {
            sql = "update jos_nlp_tags set frequency = ? where id = ?";
            LOGGER.info("update jos_nlp_tags -> tag: " + tag + " -> frequency: " + frequencyIdPair.frequency);
            jdbcTemplate.update(sql, new Object[]{frequencyIdPair.frequency, frequencyIdPair.id});
        } else {
            sql = "insert into jos_nlp_tags (tag, frequency) values(?,?)";
            LOGGER.info("insert jos_nlp_tags -> tag: " + tag + " -> frequency: " + frequencyIdPair.frequency);
            jdbcTemplate.update(sql, new Object[]{tag, frequencyIdPair.frequency});
        }
    }

    @Override
    public void insertBigramData(BigramData bigramData) {
        FrequencyIdPair bigramFrequencyIdPair = findBigramFrequency(bigramData);
        boolean bigramExistsInDB = bigramFrequencyIdPair.id > 0;
        bigramFrequencyIdPair.frequency++;
        bigramData.setBigramFrequency(bigramFrequencyIdPair.frequency);
        FrequencyIdPair tagFrequencyIdPair = findTagFrequency(bigramData.getTag1());
        double bigramProbability = BigramProbabilityCalculator.calculate(bigramFrequencyIdPair.frequency, tagFrequencyIdPair.frequency);
        bigramData.setBigramProbability(bigramProbability);
        String bigram = bigramData.getTag1() + " " + bigramData.getTag2();
        String sql = "";
        if (bigramExistsInDB) {
            sql = "update jos_nlp_bigrams set frequency = ?, probability = ? where id = ?";
            LOGGER.info("update jos_nlp_bigrams -> bigram: " + bigram + " -> frequency: " + bigramFrequencyIdPair.frequency +
                    " -> probability: " + bigramProbability);
            jdbcTemplate.update(sql, new Object[]{bigramFrequencyIdPair.frequency, bigramProbability, bigramFrequencyIdPair.id});
        } else {
            sql = "insert into jos_nlp_bigrams (frequency, bigram, tag1, tag2, probability, is_tag1_constant, is_tag2_constant) values (?,?,?,?,?,?,?)";
            LOGGER.info("insert jos_nlp_bigrams -> bigram: " + bigram + " -> frequency: " + bigramFrequencyIdPair.frequency +
                    " -> probability: " + bigramProbability);
            jdbcTemplate.update(sql, new Object[]{bigramFrequencyIdPair.frequency, bigram, bigramData.getTag1(), bigramData.getTag2(), bigramProbability,
                    bigramData.isTag1Constant(), bigramData.isTag2Constant()});
        }
    }

    @Override
    public void insertSubPathData(SubPathData subPathData) {
        String subPathAsString = subPathData.getSubPath();
        FrequencyIdPair frequencyIdPair = findSubPathFrequency(subPathAsString);
        boolean subPathExistsInDB = frequencyIdPair.id > 0;
        frequencyIdPair.frequency++;
        String sql = "";
        if (subPathExistsInDB) {
            sql = "update jos_nlp_subpaths set frequency = ? where id = ?";
            LOGGER.info("update jos_nlp_subpaths -> subPath: " + subPathData.getSubPath() + " -> frequency: " + frequencyIdPair.frequency);
            jdbcTemplate.update(sql, new Object[]{frequencyIdPair.frequency, frequencyIdPair.id});
        } else {
            sql = "insert into jos_nlp_subpaths (start_tag, end_tag, subpath, length, frequency, is_constant_subpath) values (?,?,?,?,?,?)";
            LOGGER.info("insert jos_nlp_subpaths -> subPath: " + subPathData.getSubPath() + " -> frequency: " + frequencyIdPair.frequency
                    + " -> startTag: " + subPathData.getStartTag() + " -> endTag: " + subPathData.getEndTag() + " -> length: " + subPathData.getLength());
            jdbcTemplate.update(sql, new Object[]{subPathData.getStartTag(), subPathData.getEndTag(),
                    subPathAsString, subPathData.getLength(), frequencyIdPair.frequency, subPathData.isConstantSubPath()});
        }
    }

    @Override
    public void insertSemanticData(SemanticExtractionData semanticExtractionData) {
        final String sql = "insert into jos_nlp_semantic_data (atomic_subject,extended_subject,atomic_verb_predicate,extended_verb_predicate," +
                "atomic_noun_predicate,extended_noun_predicate) values (?,?,?,?,?,?)";
        LOGGER.info(sql);
        jdbcTemplate.update(sql, new Object[]{semanticExtractionData.getAtomicSubject(), semanticExtractionData.getExtendedSubject(),
                semanticExtractionData.getAtomicVerbPredicate(),
                semanticExtractionData.getExtendedVerbPredicate(), semanticExtractionData.getAtomicNounPredicate(),
                semanticExtractionData.getExtendedNounPredicate()});
    }

    @Override
    public void insertTokenTagData(TokenTagData tokenTagData) {
        String sql = "";
        if (tokenTagData.tokenExistsInDB()) {
            sql = "update jos_nlp_tokens set is_noun = ?, is_noun_frequency = ?, is_adjective = ?, is_adjective_frequency = ?," +
                    "is_verb = ?, is_verb_frequency = ?, is_verbEd = ?, is_verbEd_frequency = ?, " +
                    "is_verbIng = ?, is_verbIng_frequency = ?, is_adverb = ?, is_adverb_frequency = ?, " + "total_frequency = ? where token = ? ";
            LOGGER.info("update jos_nlp_tokens -> token: " + tokenTagData.getToken() + " -> total_frequency -> " + tokenTagData.getTotalFrequency());
            jdbcTemplate.update(sql, new Object[]{tokenTagData.isNoun(), tokenTagData.getIsNounFrequency(), tokenTagData.isAdjective(), tokenTagData.getIsAdjectiveFrequency(),
                    tokenTagData.isVerb(), tokenTagData.getIsVerbFrequency(), tokenTagData.isVerbEd(), tokenTagData.getIsVerbEdFrequency(), tokenTagData.isVerbIng(),
                    tokenTagData.getIsVerbIngFrequency(), tokenTagData.isAdverb(), tokenTagData.getIsAdverbFrequency(), tokenTagData.getTotalFrequency(),
                    tokenTagData.getToken()});
        } else {
            sql = "insert into jos_nlp_tokens (token, is_noun, is_noun_frequency, is_adjective, is_adjective_frequency, is_verb, is_verb_frequency, " +
                    "is_verbEd, is_verbEd_frequency, is_verbIng, is_verbIng_frequency, is_adverb, is_adverb_frequency, total_frequency) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            LOGGER.info("insert jos_nlp_tokens -> token: " + tokenTagData.getToken() + " -> total_frequency -> " + tokenTagData.getTotalFrequency());
            jdbcTemplate.update(sql, new Object[]{tokenTagData.getToken(), tokenTagData.isNoun(), tokenTagData.getIsNounFrequency(), tokenTagData.isAdjective(), tokenTagData.getIsAdjectiveFrequency(),
                    tokenTagData.isVerb(), tokenTagData.getIsVerbFrequency(), tokenTagData.isVerbEd(), tokenTagData.getIsVerbEdFrequency(), tokenTagData.isVerbIng(),
                    tokenTagData.getIsVerbIngFrequency(), tokenTagData.isAdverb(), tokenTagData.getIsAdverbFrequency(), tokenTagData.getTotalFrequency()});
        }
    }

    @Override
    public void insertEncodedPath(String encodedPath) {
        FrequencyIdPair frequencyIdPair = findEncodedPathFrequency(encodedPath);
        boolean encodedPathExistsInDB = frequencyIdPair.id > 0;
        frequencyIdPair.frequency++;
        String sql = "";
        if (encodedPathExistsInDB) {
            sql = "update jos_nlp_encoded_paths set frequency = ? where path = ?";
            LOGGER.info("update jos_nlp_encoded_paths -> path: " + encodedPath + " -> frequency: " + frequencyIdPair.frequency);
            jdbcTemplate.update(sql, new Object[]{frequencyIdPair.frequency, encodedPath});
        } else {
            LOGGER.info("insert jos_nlp_encoded_paths -> path: " + encodedPath + " -> frequency: " + frequencyIdPair.frequency);
            sql = "insert into jos_nlp_encoded_paths (length, path, frequency) values(?,?,?)";
            jdbcTemplate.update(sql, new Object[]{encodedPath.length(), encodedPath, frequencyIdPair.frequency});
        }
    }

    @Override
    public Optional<TokenDatabaseData> getTokenDatabaseData(String token) {
        final String sql = "select is_noun, is_adjective, is_verb, is_verbEd, " +
                "is_verbIng, is_adverb, is_noun_frequency, is_adjective_frequency, is_verb_frequency, is_verbEd_frequency, " +
                "is_verbIng_frequency, is_adverb_frequency, " + "total_frequency from jos_nlp_tokens where token = ?";
        final List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{token});
        if (rows.size() > 0) {
            TokenDatabaseData tokenDatabaseData = new TokenDatabaseData();
            for (final Map row : rows) {
                tokenDatabaseData.setNoun((Integer) row.get("is_noun") == 1);
                tokenDatabaseData.setAdjective((Integer) row.get("is_adjective") == 1);
                tokenDatabaseData.setVerb((Integer) row.get("is_verb") == 1);
                tokenDatabaseData.setVerbEd((Integer) row.get("is_verbEd") == 1);
                tokenDatabaseData.setVerbIng((Integer) row.get("is_verbIng") == 1);
                tokenDatabaseData.setAdverb((Integer) row.get("is_adverb") == 1);
                tokenDatabaseData.setIsNounFrequency((Integer) row.get("is_noun_frequency"));
                tokenDatabaseData.setIsAdjectiveFrequency((Integer) row.get("is_adjective_frequency"));
                tokenDatabaseData.setIsVerbFrequency((Integer) row.get("is_verb_frequency"));
                tokenDatabaseData.setIsVerbEdFrequency((Integer) row.get("is_verbEd_frequency"));
                tokenDatabaseData.setIsVerbIngFrequency((Integer) row.get("is_verbIng_frequency"));
                tokenDatabaseData.setIsAdverbFrequency((Integer) row.get("is_adverb_frequency"));
                tokenDatabaseData.setTotalFrequency((Integer) row.get("total_frequency"));
            }
            return Optional.of(tokenDatabaseData);
        }
        return Optional.empty();
    }

    @Override
    public void insertNumberOfSentences(int number) {
        String sql = "update jos_nlp_number_of_sentences set number = ? where id = 1";
        jdbcTemplate.update(sql, new Object[]{number});
    }

    private FrequencyIdPair findTagFrequency(String tag) {
        final String sql = "select id, frequency from jos_nlp_tags where tag=?";
        List<Map<String, Object>> row = jdbcTemplate.queryForList(sql, new Object[]{tag});
        if (row.size() == 1) {
            int id = (Integer) row.get(0).get("id");
            int tagFrequency = (Integer) row.get(0).get("frequency");
            return new FrequencyIdPair(id, tagFrequency);
        } else {
            return new FrequencyIdPair(0, 0);
        }
    }

    private FrequencyIdPair findEncodedPathFrequency(String encodedPath) {
        final String sql = "select id, frequency from jos_nlp_encoded_paths where path=?";
        List<Map<String, Object>> row = jdbcTemplate.queryForList(sql, new Object[]{encodedPath});
        if (row.size() == 1) {
            int id = (Integer) row.get(0).get("id");
            int pathFrequency = (Integer) row.get(0).get("frequency");
            return new FrequencyIdPair(id, pathFrequency);
        } else {
            return new FrequencyIdPair(0, 0);
        }
    }

    private FrequencyIdPair findBigramFrequency(BigramData bigramData) {
        String bigram = bigramData.getTag1() + " " + bigramData.getTag2();
        final String sql = "select id, frequency from jos_nlp_bigrams where bigram=?";
        List<Map<String, Object>> row = jdbcTemplate.queryForList(sql, new Object[]{bigram});
        if (row.size() == 1) {
            int id = (Integer) row.get(0).get("id");
            int bigramFrequency = (Integer) row.get(0).get("frequency");
            return new FrequencyIdPair(id, bigramFrequency);
        } else {
            return new FrequencyIdPair(0, 0);
        }
    }

    private FrequencyIdPair findSubPathFrequency(String subPath) {
        final String sql = "select id, frequency from jos_nlp_subpaths where subpath =?";
        List<Map<String, Object>> row = jdbcTemplate.queryForList(sql, new Object[]{subPath});
        if (row.size() == 1) {
            int id = (Integer) row.get(0).get("id");
            int subPathFrequency = (Integer) row.get(0).get("frequency");
            return new FrequencyIdPair(id, subPathFrequency);
        } else {
            return new FrequencyIdPair(0, 0);
        }
    }

    private class FrequencyIdPair {
        int id;
        int frequency;

        public FrequencyIdPair(int id, int frequency) {
            this.id = id;
            this.frequency = frequency;
        }
    }

}