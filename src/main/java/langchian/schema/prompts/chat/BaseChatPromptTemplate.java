package langchian.schema.prompts.chat;

import langchian.schema.BaseMessage;
import langchian.schema.BaseMessageProvider;
import langchian.schema.PromptValue;
import langchian.schema.prompts.base.BasePromptTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BaseChatPromptTemplate extends BasePromptTemplate {

    private List<BaseMessageProvider> providers = null;

    public List<BaseMessageProvider> getProviders() {
        return providers;
    }

    public void setProviders(List<BaseMessageProvider> providers) {
        this.providers = providers;
    }

    @Override
    public PromptValue innerFormatPrompt(Map<String, Object> kwargs) {

        LinkedList<BaseMessage> prompts = new LinkedList<>();
        if(providers != null) {
            for (BaseMessageProvider provider: providers) {
                prompts.addAll(provider.provideMessages(kwargs));
            }
        }
        return new ChatPromptValue(prompts);
    }

    @Override
    public String format(Map<String, Object> kwargs) {
        return this.formatPrompt(kwargs).convertToString();
    }
}
