package utilities;

public class Broken implements RacerAlertState{
    @Override
    public String alert(AlertStateContext ctx) {
        return "Broken";
    }
}
