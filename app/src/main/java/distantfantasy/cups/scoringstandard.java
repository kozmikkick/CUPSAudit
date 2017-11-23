package distantfantasy.cups;

/**
 * Created by kozmikkick on 5/23/17.
 */

public class scoringstandard {
    public String scoringstandard(long score){
        if (score <= 39) {
            return "F";
        } else if (score >= 39 && score <= 70) {
            return "D";
        } else {
            return "C";
        }
    }
}
