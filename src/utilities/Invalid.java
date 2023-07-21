package utilities;

public class Invalid implements RacerAlertState{
    @Override
    public String alert(AlertStateContext ctx) {
        return "Invalid";
    }
}
