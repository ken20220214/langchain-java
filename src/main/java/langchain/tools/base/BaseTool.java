package langchain.tools.base;

import langchain.callbacks.base.BaseCallbackManager;

import java.util.Map;

public abstract class BaseTool {

    private String name;
    private String description;
    private boolean returnDirect;
    private boolean verbose;
    private BaseCallbackManager manager;

    public abstract Object run(Map<String, Object> toolInput);
}
