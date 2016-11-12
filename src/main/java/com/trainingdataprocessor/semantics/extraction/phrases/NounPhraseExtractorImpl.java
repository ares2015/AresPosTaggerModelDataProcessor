package com.trainingdataprocessor.semantics.extraction.phrases;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.tokens.Tokenizer;

import java.util.logging.Logger;

/**
 * Created by Oliver on 10/22/2016.
 */
public class NounPhraseExtractorImpl implements PhraseExtractor {

    private final static Logger LOGGER = Logger.getLogger(NounPhraseExtractorImpl.class.getName());

    private Tokenizer tokenizer;

    public NounPhraseExtractorImpl(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    @Override
    public void extract(SemanticPreprocessingData semanticPreprocessingData, SemanticExtractionData semanticExtractionData) {
        if ((!semanticPreprocessingData.containsBeforeVerbPrepositionPhrase() && semanticPreprocessingData.containsBeforeVerbNounPhrase())) {
            int startIndex = semanticPreprocessingData.getBeforeVerbNounPhrase().getStartIndex();
            int endIndex = semanticPreprocessingData.getBeforeVerbNounPhrase().getEndIndex();
            if (containsExtendedNoun(endIndex, startIndex)) {
                String extendedSubject = tokenizer.convertSubListToString(semanticPreprocessingData.getTokens(), startIndex, endIndex);
                semanticExtractionData.setExtendedSubject(extendedSubject);
                LOGGER.info("ExtendedSubject found: " + extendedSubject);
            }
            String atomicSubject = semanticPreprocessingData.getTokens().get(endIndex - 1);
            semanticExtractionData.setAtomicSubject(atomicSubject);
            LOGGER.info("AtomicSubject found: " + atomicSubject);
        }
        if (semanticPreprocessingData.containsAfterVerbNounPhrase() && "".equals(semanticExtractionData.getExtendedNounPredicate())) {
            int startIndex = semanticPreprocessingData.getAfterVerbNounPhrase().getStartIndex();
            int endIndex = semanticPreprocessingData.getAfterVerbNounPhrase().getEndIndex();
            if (containsExtendedNoun(endIndex, startIndex)) {
                String extendedNounPredicate = tokenizer.convertSubListToString(semanticPreprocessingData.getTokens(), startIndex, endIndex);
                semanticExtractionData.setExtendedNounPredicate(extendedNounPredicate);
                LOGGER.info("ExtendedNounPredicate found: " + extendedNounPredicate);
            }
            String atomicNounPredicate = semanticPreprocessingData.getTokens().get(endIndex - 1);
            semanticExtractionData.setAtomicNounPredicate(atomicNounPredicate);
            LOGGER.info("AtomicNounPredicate found: " + atomicNounPredicate);
        } else if (semanticPreprocessingData.containsAfterVerbNounPhrase() && !"".equals(semanticExtractionData.getExtendedNounPredicate())) {
            int endIndex = semanticPreprocessingData.getAfterVerbNounPhrase().getEndIndex();
            String atomicNounPredicate = semanticPreprocessingData.getTokens().get(endIndex - 1);
            semanticExtractionData.setAtomicNounPredicate(atomicNounPredicate);
            LOGGER.info("AtomicNounPredicate found: " + atomicNounPredicate);
        }
    }

    private boolean containsExtendedNoun(int endIndex, int startIndex) {
        return endIndex - startIndex > 1;
    }

}