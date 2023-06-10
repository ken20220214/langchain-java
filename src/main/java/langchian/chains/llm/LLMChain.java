package langchian.chains.llm;

import com.google.common.collect.Lists;
import langchian.chains.base.Chain;
import langchian.prompts.prompt.PromptTemplate;
import langchian.schema.PromptValue;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
public class LLMChain extends Chain {

    private static final String STOP_KEY = "stop";

    private PromptTemplate template;


    @Builder
    public static class PromptInfo {

        private List<PromptValue> prompts;
        private List<String> stops;
    }

    private PromptInfo prepPrompts(List<Map<String, Object>> inputLists) {

        List<String> stops = null;

        Map<String, Object> first = inputLists.get(0);
        if (first.containsKey(STOP_KEY)) {
            stops = (List<String>) first.get(STOP_KEY);
        }

        List<PromptValue> prompts = Lists.newLinkedList();
        for (Map<String, Object> input: inputLists) {


        }

        return null;
    }


    @Override
    protected Map<String, Object> _call(Map<String, Object> inputs) throws IOException {
        return null;
    }
}
