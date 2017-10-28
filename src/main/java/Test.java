import java.io.IOException;
import java.util.List;

import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;

public class Test {
	public static void main(String []args) throws IOException {
		TurkishMorphology morphology=TurkishMorphology.createWithDefaults();
		List<WordAnalysis> results=morphology.analyze("şimdilerde");
		results.forEach(s -> System.out.println(s.formatLong()));
	}
}
