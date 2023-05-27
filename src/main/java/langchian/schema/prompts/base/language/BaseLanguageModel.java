package langchian.schema.prompts.base.language;

import langchian.schema.Generation;
import langchian.schema.LLMResult;
import langchian.schema.PromptValue;

import java.util.List;

public abstract class BaseLanguageModel<T extends Generation> {

    public abstract LLMResult<T> generatePrompt(List<PromptValue> prompts, List<String> stop);

}
