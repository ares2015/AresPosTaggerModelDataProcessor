package com.trainingdataprocessor.factories.multilist;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Oliver on 8/6/2016.
 */
public class MultiListFactoryImpl implements MultiListFactory {

    private final static Logger LOGGER = Logger.getLogger(MultiListFactoryImpl.class.getName());

    @Override
    public List<List<String>> create(List<String> words) {
        LOGGER.info("ENTERING create method of MultiListFactoryImpl... ");
        LOGGER.info("*********************************************************************");

        List<List<String>> multiList = new ArrayList<List<String>>();
        List<String> subPath = new ArrayList<>();
        int index = 0;
        for (String word : words) {
            //IF TOKEN DOES NOT END WITH COMMA, JUST ADD IT TO THE SUBPATH
            if (!(word.endsWith(","))) {
                subPath.add(word);
            } else {
                //ADD THE ACTUAL TOKEN AND ADD ACTUAL SUBPATH TO THE SUBPATHS LIST.
                //AND CREATE NEW EMPTY SUBPATH, INTO WHICH NEXT TOKENS WILL BE INSERTED
                String wordWithoutComma = word.substring(0, word.length() - 1);
                subPath.add(wordWithoutComma);
                multiList.add(subPath);
                LOGGER.info("Created subPath with size " + subPath.size() + " and added into multiList.");
                subPath = new ArrayList<>();
            }
            //ALWAYS ADD SUBPATH STARTING WITH THE LAST COMMA IN SENTENCE AND ENDING WITH THE
            //LAST TOKEN IN SENTENCE
            if (index == words.size() - 1) {
                multiList.add(subPath);
                LOGGER.info("Created subPath with size " + subPath.size() + " and added into multiList.");
            }
            index++;
        }
        LOGGER.info("LEAVING create method of MultiListFactoryImpl with  " + multiList.size() + " " +
                " subPaths created.");
        LOGGER.info("*********************************************************************");

        return multiList;
    }

}
