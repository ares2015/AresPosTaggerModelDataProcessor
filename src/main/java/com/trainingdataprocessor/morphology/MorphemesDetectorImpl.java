package com.trainingdataprocessor.morphology;

import com.trainingdataprocessor.cache.SuffixesCache;

/**
 * Created by Oliver on 3/20/2017.
 */
public class MorphemesDetectorImpl implements MorphemesDetector {

    @Override
    public String detectSuffix(String token) {
        for (Integer suffixLength : SuffixesCache.suffixesLengthsModelMap) {
            if (token.length() > suffixLength) {
                String suffix = getSuffixFromToken(token, suffixLength);
                if (cacheContainsSuffix(suffix)) {
                    return suffix;
                }
            }
        }
        return detectShortOrSpecialSuffix(token);
    }

    private boolean cacheContainsSuffix(String suffix) {
        return SuffixesCache.suffixesCache.contains(suffix);
    }

    private String getSuffixFromToken(String token, int startIndex) {
        return token.substring(token.length() - startIndex, token.length());
    }

    private String detectShortOrSpecialSuffix(final String token) {
        if (token.length() > 2 && token.endsWith(Suffixes.S)
                && !token.endsWith(Suffixes.SES) && !token.endsWith(Suffixes.XES)
                && !token.endsWith(Suffixes.IES)) {
            return Suffixes.S;
        } else if ((token.length() > 3 && (token.endsWith(Suffixes.SES) || token.endsWith(Suffixes.XES)))
                && !token.endsWith(Suffixes.IES)) {
            return Suffixes.ES;
        } else if (token.endsWith(Suffixes.ER)) {
            return Suffixes.ER;
        } else if (token.endsWith(Suffixes.ERS)) {
            return Suffixes.ERS;
        } else if (token.endsWith(Suffixes.EST)) {
            return Suffixes.EST;
        } else if (token.endsWith(Suffixes.LY)) {
            return Suffixes.LY;
        } else if (token.endsWith(Suffixes.AL)) {
            return Suffixes.AL;
        } else if (token.length() > 3 && token.endsWith(Suffixes.IES)) {
            return Suffixes.IES;
        }
        return "no suffix";
    }


}