package General;


import zemberek.core.logging.Log;
import zemberek.morphology.ambiguity.Z3MarkovModelDisambiguator;
import zemberek.morphology.analysis.SentenceAnalysis;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;
import zemberek.morphology.analysis.tr.TurkishSentenceAnalyzer;

import java.io.IOException;

public class FindPOS {


    public FindPOS(TurkishSentenceAnalyzer analyzer) {
        
    }

    public static String test(String s,TurkishSentenceAnalyzer analyzer) {
        //System.out.println("Sentence  = " + s);
        SentenceAnalysis analysis = analyzer.analyze(s);
        analyzer.disambiguate(analysis);


        for (SentenceAnalysis.Entry entry : analysis) {
            WordAnalysis wa = entry.parses.get(0);
            String prop=wa.dictionaryItem.secondaryPos.toString();
            /*Log.info("%s -> %s : %s ",
                    entry.input,
                    wa.dictionaryItem.primaryPos,
                    wa.dictionaryItem.secondaryPos);*/
            if(prop.equalsIgnoreCase("ProperNoun")) {
            	//System.out.println(entry.input);
            	return entry.input;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        TurkishMorphology morphology = TurkishMorphology.createWithDefaults();
        Z3MarkovModelDisambiguator disambiguator = new Z3MarkovModelDisambiguator();
        TurkishSentenceAnalyzer sentenceAnalyzer = new TurkishSentenceAnalyzer(
                morphology,
                disambiguator
        );
  

    }
}