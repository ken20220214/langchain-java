package langchian.schema.prompts.chat;

import langchian.schema.BaseMessage;
import langchian.schema.BaseMessageProvider;

import java.util.List;
import java.util.Map;

public abstract class BaseMessagePromptTemplate implements BaseMessageProvider {

    private List<String> inputVariables = null;

    protected abstract List<BaseMessage> formatMessages(Map<String, Object> kwargs);

    @Override
    public List<BaseMessage> provideMessages(Map<String, Object> kwargs) {
        return this.formatMessages(kwargs);
    }
}
