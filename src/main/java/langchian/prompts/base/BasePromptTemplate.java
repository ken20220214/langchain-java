package langchian.prompts.base;

import langchian.schema.BaseOutputParser;
import langchian.schema.PromptValue;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
public abstract class BasePromptTemplate<T> {

    private List<String> inputVariables;
    private BaseOutputParser<T> parser;
    private Map<String, String> partialVariables;

    public abstract PromptValue formatPrompt(Map<String, Object> input);
    public abstract String format(Map<String, Object> input);

}
