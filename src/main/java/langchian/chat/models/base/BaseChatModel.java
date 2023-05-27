package langchian.chat.models.base;

import com.google.common.collect.Lists;
import langchian.schema.*;
import langchian.schema.prompts.base.language.BaseLanguageModel;

import java.util.List;

public abstract class BaseChatModel extends BaseLanguageModel<ChatGeneration> {

    protected abstract ChatResult generate(PromptValue pv, List<String> stop);

    @Override
    public LLMResult<ChatGeneration> generatePrompt(List<PromptValue> prompts, List<String> stop) {

        List<List<ChatGeneration>> generations = Lists.newLinkedList();
        for (PromptValue pv: prompts) {
            ChatResult r = generate(pv, stop);
            generations.add(r.getGenerations());
        }
        LLMResult<ChatGeneration> result = new LLMResult();
        result.setGenerations(generations);
        return result;
    }

}
