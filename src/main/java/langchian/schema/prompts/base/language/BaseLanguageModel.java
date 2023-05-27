package langchian.schema.prompts.base.language;

import langchian.schema.LLMResult;
import langchian.schema.PromptValue;

import java.util.List;

public abstract class BaseLanguageModel {

    public abstract LLMResult generatePrompt(List<PromptValue> prompts, List<String> stop);

    public abstract int getNumTokens();

}
