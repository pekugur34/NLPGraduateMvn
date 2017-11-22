package Process;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class RegularExpression {
	
	   public static void main(String[] args) {
	   
	      // Input for matching the regexe pattern
	      String input = "Jeanne d'Arc ([ʒanˈdaʁk]) (d. 6 Ocak 1412 - ö. 30 Mayıs 1431)[1] (İngilizce'de Joan of Arc, Türkçede bazen sadeleştirilerek Jan Dark şeklinde de yazılır.)";
	      // Regexe to be matched
	      String regexe = "\\(\\[";
	      String regexe2 = "\\]\\)";
	      // Step 1: Allocate a Pattern object to compile a regexe
	      //Pattern pattern = Pattern.compile(regexe);
	      Pattern pattern = Pattern.compile(regexe, Pattern.CASE_INSENSITIVE);  // case-insensitive matching
	      Pattern pattern2=Pattern.compile(regexe2, Pattern.CASE_INSENSITIVE);
	      // Step 2: Allocate a Matcher object from the compiled regexe pattern,
	      //         and provide the input to the Matcher
	      Matcher matcher = pattern.matcher(input);
	      Matcher matcher2 = pattern2.matcher(input);
	      // Step 3: Perform the matching and process the matching result
	   
	      // Use method find()
	      while (matcher.find()) {     // find the next match
	         System.out.println("find() found the pattern \"" + matcher.group()
	               + "\" starting at index " + matcher.start()
	               + " and ending at index " + matcher.end());
	      }
	      while (matcher2.find()) {     // find the next match
		         System.out.println("find() found the pattern \"" + matcher.group()
		               + "\" starting at index " + matcher.start()
		               + " and ending at index " + matcher.end());
		      }
	   
	      // Use method matches()
	      if (matcher.matches()) {
	         System.out.println("matches() found the pattern \"" + matcher.group()
	               + "\" starting at index " + matcher.start()
	               + " and ending at index " + matcher.end());
	      } else {
	         System.out.println("matches() found nothing");
	      }
	   
	      // Use method lookingAt()
	      if (matcher.lookingAt()) {
	         System.out.println("lookingAt() found the pattern \"" + matcher.group()
	               + "\" starting at index " + matcher.start()
	               + " and ending at index " + matcher.end());
	      } else {
	         System.out.println("lookingAt() found nothing");
	      }
	   }
	
}
