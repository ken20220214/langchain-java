package langchian.schema;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

public abstract class BaseMessage implements BaseMessageProvider, StringConverter {

    protected String content;
    protected String type;
    protected Map<String, String> additionalKwargs = null;

    public BaseMessage(String content, String type, Map<String, String> additionalKwargs) {
        this.content = content;
        this.type = type;
        this.additionalKwargs = additionalKwargs;
    }

    @Override
    public List<BaseMessage> provideMessages(Map<String, Object> kwargs) {
        BaseMessage[] tmp = new BaseMessage[0];
        return Lists.asList(this, tmp);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getAdditionalKwargs() {
        return additionalKwargs;
    }

    public void setAdditionalKwargs(Map<String, String> additionalKwargs) {
        this.additionalKwargs = additionalKwargs;
    }

    @Override
    public String convertToString() {

        StringBuilder sb = new StringBuilder();
        sb.append(this.type).append(": ").append(this.content).append("\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        String.format("");
    }
}
