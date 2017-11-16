package General;

import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;

import java.io.IOException;
import java.util.List;

public class StemmingAndLemmatization {
    TurkishMorphology morphology;

    public StemmingAndLemmatization(TurkishMorphology morphology) {
        this.morphology = morphology;
    }

    public void analyze(String word) {
        System.out.println("Word = " + word);

        System.out.println("Parses: ");
        List<WordAnalysis> results = morphology.analyze(word);
        for (WordAnalysis result : results) {
            
            System.out.println("\tStems = " + result.getStems().get(0));
            System.out.println(result.formatNoSurface());
            System.out.println(result.formatOflazer());
            System.out.println(result.formatOnlyIgs());
            System.out.println(result.formatNoEmpty());
        }
    }
}