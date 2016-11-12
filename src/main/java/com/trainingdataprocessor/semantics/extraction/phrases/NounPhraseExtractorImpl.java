package com.trainingdataprocessor.semantics.extraction.phrases;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.tokenizing.Tokenizer;

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
            String extendedSubject = tokenizer.convertSubListToString(semanticPreprocessingData.getTokens(), startIndex, endIndex);
            semanticExtractionData.setExtendedSubject(extendedSubject);
            String atomicSubject = semanticPreprocessingData.getTokens().get(endIndex - 1);
            semanticExtractionData.setAtomicSubject(atomicSubject);
            LOGGER.info("AtomicSubject found: " + atomicSubject);
            LOGGER.info("ExtendedSubject found: " + extendedSubject);
        }
        if (semanticPreprocessingData.containsAfterVerbNounPhrase() && "".equals(semanticExtractionData.getExtendedNounPredicate())) {
            int startIndex = semanticPreprocessingData.getAfterVerbNounPhrase().getStartIndex();
            int endIndex = semanticPreprocessingData.getAfterVerbNounPhrase().getEndIndex();
            String extendedNounPredicate = tokenizer.convertSubListToString(semanticPreprocessingData.getTokens(), startIndex, endIndex);
            semanticExtractionData.setExtendedNounPredicate(extendedNounPredicate);
            String atomicNounPredicate = semanticPreprocessingData.getTokens().get(endIndex - 1);
            semanticExtractionData.setAtomicNounPredicate(atomicNounPredicate);
            LOGGER.info("AtomicNounPredicate found: " + atomicNounPredicate);
            LOGGER.info("ExtendedNounPredicate found: " + extendedNounPredicate);

        }else if(semanticPreprocessingData.containsAfterVerbNounPhrase() && semanticExtractionData.getExtendedNounPredicate() != null){
            int endIndex = semanticPreprocessingData.getAfterVerbNounPhrase().getEndIndex();
            String atomicNounPredicate = semanticPreprocessingData.getTokens().get(endIndex - 1);
            semanticExtractionData.setAtomicNounPredicate(atomicNounPredicate);
            LOGGER.info("AtomicNounPredicate found: " + atomicNounPredicate);
        }
    }

}