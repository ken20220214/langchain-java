package langchian.schema;

import java.util.Map;

public class SystemMessage extends BaseMessage{

    private static final String TYPE = "system";

    public SystemMessage(String content, Map<String, String> additionalKwargs) {
        super(content, TYPE, additionalKwargs);
    }
}
