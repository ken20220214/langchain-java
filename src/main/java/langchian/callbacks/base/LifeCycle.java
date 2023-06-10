package langchian.callbacks.base;

public interface LifeCycle {

    public abstract void onLlmStart();
    public abstract void onLlmEnd();
    public abstract void onLlmError();

    public abstract void onChainStart();
    public abstract void onChainEnd();
    public abstract void onChainError();

    public abstract void onAgentAction();
    public abstract void onAgentFinish();

    public abstract void onToolStart();
    public abstract void onToolEnd();
    public abstract void onToolError();

}
