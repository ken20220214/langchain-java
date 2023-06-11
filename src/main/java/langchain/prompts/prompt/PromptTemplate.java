package langchain.prompts.prompt;

import langchain.prompts.base.StringPromptTemplate;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.Map;

@Data
@SuperBuilder
public class PromptTemplate extends StringPromptTemplate<Object> {

    // such as "${name} hello, i am $${role}"
    private String template;

    @Override
    public String format(Map<String, Object> input) {
        StrSubstitutor ss = new StrSubstitutor(input);
        return ss.replace(template);
    }
}
