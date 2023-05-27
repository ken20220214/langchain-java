package langchian.schema;

import java.util.Map;

public class HumanMessage extends BaseMessage{

    private static final String TYPE = "human";

    private boolean exmpale = false;

    public HumanMessage(String content, Map<String, String> additionalKwargs) {
        super(content, TYPE, additionalKwargs);
    }

    public boolean isExmpale() {
        return exmpale;
    }

    public void setExmpale(boolean exmpale) {
        this.exmpale = exmpale;
    }
}
