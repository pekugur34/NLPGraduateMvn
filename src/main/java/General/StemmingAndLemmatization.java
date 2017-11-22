package General;

import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;

import java.io.IOException;
import java.util.List;

public class StemmingAndLemmatization {
    private static TurkishMorphology morphology;
	
    public StemmingAndLemmatization(TurkishMorphology morphology) {
        this.morphology = morphology;
    }

    public static String analyze(String word,TurkishMorphology morphology) {
       // System.out.println("Word = " + word);

        String stem="";
       // System.out.println("Parses: ");
        List<WordAnalysis> results = morphology.analyze(word);
        for (WordAnalysis result : results) {
            
            /*System.out.println("\tStems = " + result.getStems().get(0));
            System.out.println(result.formatNoSurface());
            System.out.println(result.formatOflazer());
            System.out.println(result.formatOnlyIgs());
            System.out.println(result.formatNoEmpty());*/
            stem=result.getStems().get(0);
        }
        
        return stem;
    }
}