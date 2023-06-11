package langchain.baselanguage;

import langchain.callbacks.base.BaseCallbackManager;
import langchain.schema.BaseMessage;
import langchain.schema.Generation;
import langchain.schema.LLMResult;
import langchain.schema.PromptValue;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.IOException;
import java.util.List;

@Data
@SuperBuilder
public abstract class BaseLanguageModel<T extends Generation> {

    public abstract LLMResult<T> generatePrompt(
            List<PromptValue> prompts, List<String> stops,
            BaseCallbackManager manager, boolean verbose) throws IOException;

    public abstract String predict(
            String text, List<String> stops,
            BaseCallbackManager manager, boolean verbose) throws IOException;

    public abstract BaseMessage predictMessages(
            List<BaseMessage> messages, List<String> stops,
            BaseCallbackManager manager, boolean verbose) throws IOException;

}
