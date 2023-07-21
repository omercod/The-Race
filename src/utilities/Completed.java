package utilities;

public class Completed implements RacerAlertState{
    @Override
    public String alert(AlertStateContext ctx) {
        return "Completed";
    }
}

