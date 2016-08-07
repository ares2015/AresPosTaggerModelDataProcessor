package com.trainingdataprocessor.factories;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Oliver on 8/6/2016.
 */
public class SubPathsListFactoryImpl implements SubPathsListFactory {

    private final static Logger LOGGER = Logger.getLogger(SubPathsListFactoryImpl.class.getName());

    @Override
    public List<List<String>> create(List<String> words) {
        LOGGER.info("ENTERING create method of SubPathsListFactoryImpl... ");
        LOGGER.info("*********************************************************************");

        List<List<String>> subPathsList = new ArrayList<List<String>>();
        List<String> subPath = new ArrayList<>();
        int index = 0;
        for (String word : words) {
            //IF TOKEN DOES NOT END WITH COMMA, JUST ADD IT TO THE SUBPATH
            if (!(word.endsWith(","))) {
                subPath.add(word);
            } else {
                //ADD THE ACTUAL TOKEN AND ADD ACTUAL SUBPATH TO THE SUBPATHS LIST.
                //AND CREATE NEW EMPTY SUBPATH, INTO WHICH NEXT TOKENS WILL BE INSERTED
                subPath.add(word);
                subPathsList.add(subPath);
                LOGGER.info("Created subPath with size " + subPath.size() + " and added into subPathsList.");
                subPath = new ArrayList<>();
            }
            //ALWAYS ADD SUBPATH STARTING WITH THE LAST COMMA IN SENTENCE AND ENDING WITH THE
            //LAST TOKEN IN SENTENCE
            if (index == words.size() - 1) {
                subPathsList.add(subPath);
                LOGGER.info("Created subPath with size " + subPath.size() + " and added into subPathsList.");
            }
            index++;
        }
        LOGGER.info("LEAVING create method of SubPathsListFactoryImpl with  " + subPathsList.size() + " " +
                " subPaths created.");
        LOGGER.info("*********************************************************************");

        return subPathsList;
    }

}
