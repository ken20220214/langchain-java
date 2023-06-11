package langchain.prompts.base;

import langchain.schema.PromptValue;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Data
@SuperBuilder
public abstract class StringPromptTemplate<T> extends BasePromptTemplate<T>{

    @Override
    public PromptValue formatPrompt(Map<String, Object> input) {
        return StringPromptValue.builder().text(this.format(input)).build();
    }
}
