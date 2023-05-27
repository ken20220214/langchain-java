package langchian.schema;

import java.util.Map;

public class AIMessage extends BaseMessage {

    private static final String TYPE = "ai";

    private boolean example = false;

    public AIMessage(String content, Map<String, String> additionalKwargs) {
        super(content, TYPE, additionalKwargs);
    }

    public boolean isExample() {
        return example;
    }

    public void setExample(boolean example) {
        this.example = example;
    }
}
