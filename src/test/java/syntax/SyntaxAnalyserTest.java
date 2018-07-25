package syntax;

import com.aresPosTaggerModelDataProcessor.data.preprocessing.TrainingDataRow;
import com.aresPosTaggerModelDataProcessor.factories.bigram.BigramDataListFactory;
import com.aresPosTaggerModelDataProcessor.factories.bigram.BigramDataListFactoryImpl;
import com.aresPosTaggerModelDataProcessor.factories.subpath.SubPathDataListFactory;
import com.aresPosTaggerModelDataProcessor.factories.subpath.SubPathDataListFactoryImpl;
import com.aresPosTaggerModelDataProcessor.tags.Tags;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 11/12/2016.
 */
public class SyntaxAnalyserTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    BigramDataListFactory bigramDataListFactory = new BigramDataListFactoryImpl();

    SubPathDataListFactory subPathDataListFactory = new SubPathDataListFactoryImpl();


    @Test
    public void test() throws InterruptedException {
        String sentence = "King George visited Hanover again from May to November 1719";
        List<String> tagsList = new ArrayList<String>();
        tagsList.add(Tags.NOUN);
        tagsList.add(Tags.NOUN);
        tagsList.add(Tags.VERB);
        tagsList.add(Tags.NOUN);
        tagsList.add(Tags.ADVERB);
        tagsList.add(Tags.PREPOSITION);
        tagsList.add(Tags.NOUN);
        tagsList.add(Tags.PREPOSITION);
        tagsList.add(Tags.NOUN);
        tagsList.add(Tags.NUMBER);

        List<TrainingDataRow> trainingDataRowList = new ArrayList<>();
        TrainingDataRow trainingDataRow = new TrainingDataRow();
        trainingDataRow.setTagsList(tagsList);

        trainingDataRowList.add(trainingDataRow);


    }



}
