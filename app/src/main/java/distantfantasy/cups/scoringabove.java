package distantfantasy.cups;

/**
 * Created by kozmikkick on 5/23/17.
 */

public class scoringabove {
    public String scoringabove(long score){
        if (score <= 34) {
            return "F";
        } else if (score >= 34 && score <= 50) {
            return "D";
        } else if (score >= 50 && score <= 69) {
            return "C";
        } else if (score >= 69 && score <= 80) {
            return "B";
        } else {
            return "A";
        }
    }
}
