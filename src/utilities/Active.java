package utilities;

public class Active implements RacerAlertState{
    @Override
    public String alert(AlertStateContext ctx) {
        return "Active";
    }

}
