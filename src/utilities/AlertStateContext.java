package utilities;

public class AlertStateContext extends Obserable{
    private RacerAlertState currentState;
    public AlertStateContext(){
        currentState = new Active();
    }
    public void setState(RacerAlertState state){
        currentState = state;
    }
    public String alert(){
        return currentState.alert(this);
    }
}
