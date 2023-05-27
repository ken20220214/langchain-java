package langchian.chat.models.openai;

import langchian.chat.models.base.BaseChatModel;
import langchian.schema.ChatResult;
import langchian.schema.PromptValue;

import java.util.List;

public class ChatOpenAI extends BaseChatModel {
    @Override
    protected ChatResult generate(PromptValue pv, List<String> stop) {
        return null;
    }
}
