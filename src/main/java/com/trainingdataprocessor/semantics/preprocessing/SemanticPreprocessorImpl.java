package com.trainingdataprocessor.semantics.preprocessing;

import com.trainingdataprocessor.cache.SemanticExtractionFilterCache;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.tags.Tags;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 2/17/2017.
 */
public class SemanticPreprocessorImpl implements SemanticPreprocessor {

    @Override
    public SemanticPreprocessingData preprocess(List<String> tokens, List<String> tags) {
        List<String> filteredTokens = new ArrayList<>();
        List<String> filteredTags = new ArrayList<>();
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        int tagsListIndex = 0;
        int verbIndex = -1;
        int modalVerbIndex = -1;
        boolean containsBeforeVerbPreposition = false;
        int afterVerbFirstPrepositionIndex = -1;
        boolean containsAfterVerbVerbIng = false;
        boolean containsSubject = false;

        for (String tag : tags) {
            if (SemanticExtractionFilterCache.semanticExtractionAllowedTags.contains(tag)) {
                if (!Tags.DETERMINER.equals(tag) && !Tags.PRONOUN_POSSESIVE.equals(tag)) {
                    filteredTags.add(tag);
                    filteredTokens.add(tokens.get(tagsListIndex));
                    if ((Tags.VERB.equals(tag) || Tags.IS_ARE.equals(tag)) && verbIndex == -1) {
                        verbIndex = filteredTags.size() - 1;
                    } else if ((Tags.VERB.equals(tag) || Tags.IS_ARE.equals(tag)) && verbIndex > -1) {
                        return semanticPreprocessingData;
                    }
                    if (Tags.MODAL_VERB.equals(tag)) {
                        modalVerbIndex = filteredTags.size() - 1;
                    }
                    if ((Tags.PREPOSITION.equals(tag) || Tags.TO.equals(tag)) && afterVerbFirstPrepositionIndex == -1 && verbIndex > -1) {
                        afterVerbFirstPrepositionIndex = filteredTags.size() - 1;
                    }
                    if ((Tags.PREPOSITION.equals(tag) || Tags.TO.equals(tag)) && verbIndex == -1) {
                        containsBeforeVerbPreposition = true;
                    }
                    if (Tags.VERB_ING.equals(tag) && verbIndex > -1) {
                        containsAfterVerbVerbIng = true;
                    }
                    if ((Tags.NOUN.equals(tag) || Tags.VERB_ED.equals(tag)) && verbIndex == -1) {
                        containsSubject = true;
                    }
                }
                tagsListIndex++;
            } else {
                if (verbIndex > -1) {
                    break;
                } else {
                    return semanticPreprocessingData;
                }
            }
        }
        if (verbIndex == -1 || !containsSubject) {
            return semanticPreprocessingData;
        } else {
            semanticPreprocessingData.setTagsList(filteredTags);
            semanticPreprocessingData.setTokensList(filteredTokens);
            semanticPreprocessingData.setContainsBeforeVerbPreposition(containsBeforeVerbPreposition);
            semanticPreprocessingData.setVerbIndex(verbIndex);
            semanticPreprocessingData.setModalVerbIndex(modalVerbIndex);
            semanticPreprocessingData.setAfterVerbFirstPrepositionIndex(afterVerbFirstPrepositionIndex);
            semanticPreprocessingData.setContainsAfterVerbVerbIng(containsAfterVerbVerbIng);
            semanticPreprocessingData.setCanGoToExtraction(true);
            return semanticPreprocessingData;
        }
    }

}