package frc.robot;

public class RobotState {
    public enum State {
        NO_TARGET,
        VALID_TARGET,
        ALIGNING,
        SPEEDING_UP,
        READY,
        FAIL_ALIGN,
        TIMER,
        ERROR,
        DISABLED,
        ENABLED, 
        SUCCESSFUL_ALIGN
    }
    private State state;

    public RobotState() {}

    public RobotState.State getState() { 
        if (state != null){return state;}
        else {return State.DISABLED;}
    }
    public void update(RobotState.State updatedState){
        state = updatedState;
    }
}
