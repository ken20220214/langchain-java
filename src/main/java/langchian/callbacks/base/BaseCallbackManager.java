package langchian.callbacks.base;

import lombok.Builder;

import java.util.List;

@Builder
public class BaseCallbackManager implements LifeCycle {

    private List<CallbackHandler> handlers;

    @Override
    public void onLlmStart() {
        for (CallbackHandler handler: handlers) {
            handler.onLlmStart();
        }
    }

    @Override
    public void onLlmEnd() {
        for (CallbackHandler handler: handlers) {
            handler.onLlmEnd();
        }
    }

    @Override
    public void onLlmError() {
        for (CallbackHandler handler: handlers) {
            handler.onLlmError();
        }
    }

    @Override
    public void onChainStart() {
        for (CallbackHandler handler: handlers) {
            handler.onChainStart();
        }
    }

    @Override
    public void onChainEnd() {
        for (CallbackHandler handler: handlers) {
            handler.onChainEnd();
        }
    }

    @Override
    public void onChainError() {
        for (CallbackHandler handler: handlers) {
            handler.onChainError();
        }
    }

    @Override
    public void onAgentAction() {
        for (CallbackHandler handler: handlers) {
            handler.onAgentAction();
        }
    }

    @Override
    public void onAgentFinish() {
        for (CallbackHandler handler: handlers) {
            handler.onAgentFinish();
        }
    }

    @Override
    public void onToolStart() {
        for (CallbackHandler handler: handlers) {
            handler.onToolStart();
        }
    }

    @Override
    public void onToolEnd() {
        for (CallbackHandler handler: handlers) {
            handler.onToolEnd();
        }
    }

    @Override
    public void onToolError() {
        for (CallbackHandler handler: handlers) {
            handler.onToolError();
        }
    }
}
