package langchian.chains.llm;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import langchian.schema.Chain;
import langchian.schema.Generation;
import langchian.schema.LLMResult;
import langchian.schema.PromptValue;
import langchian.schema.prompts.base.BasePromptTemplate;
import langchian.schema.prompts.base.language.BaseLanguageModel;

import java.util.List;
import java.util.Map;

public class LLMChain<T extends Generation> extends Chain {

    private BaseLanguageModel llm = null;
    private String outputKey = "text";
    private BasePromptTemplate prompt = null;

    public LLMChain(BaseLanguageModel llm, String outputKey, BasePromptTemplate prompt) {
        this.llm = llm;
        this.outputKey = outputKey;
        this.prompt = prompt;
    }

    public BaseLanguageModel getLlm() {
        return llm;
    }

    public void setLlm(BaseLanguageModel llm) {
        this.llm = llm;
    }

    public String getOutputKey() {
        return outputKey;
    }

    public void setOutputKey(String outputKey) {
        this.outputKey = outputKey;
    }

    public BasePromptTemplate getPrompt() {
        return prompt;
    }

    public void setPrompt(BasePromptTemplate prompt) {
        this.prompt = prompt;
    }

    @Override
    protected Map<String, Object> _call(Map<String, Object> inputs) {
        List<Map<String, Object>> tmp = Lists.newLinkedList();
        tmp.add(inputs);
        PromptAndStop pas = this.prepPrompts(tmp);
        LLMResult<T> result = this.llm.generatePrompt(pas.getValues(), pas.getStop());

        return this.createOutputs(result).get(0);
    }

    public static class PromptAndStop {

        private List<PromptValue> values;
        private List<String> stop;

        public List<PromptValue> getValues() {
            return values;
        }

        public void setValues(List<PromptValue> values) {
            this.values = values;
        }

        public List<String> getStop() {
            return stop;
        }

        public void setStop(List<String> stop) {
            this.stop = stop;
        }

        public PromptAndStop(List<PromptValue> values, List<String> stop) {
            this.values = values;
            this.stop = stop;
        }
    }

    private static final String STOP_KEY = "stop";

    private PromptAndStop prepPrompts(List<Map<String, Object>> inputs) {

        List<String> variables = this.prompt.getInputVariables();
        List<String> stop = (List<String>) inputs.get(0).get(STOP_KEY);
        List<PromptValue> values = Lists.newLinkedList();

        for(Map<String, Object> input: inputs) {
            Map<String, Object> params = Maps.newHashMap();
            for (String variable: variables) {
                params.put(variable, input.get(variable));
            }
            PromptValue value = this.prompt.formatPrompt(params);
            values.add(value);
        }

        PromptAndStop pas = new PromptAndStop(values, stop);
        return pas;
    }


    private List<Map<String, Object>> createOutputs(LLMResult<T> result) {
        List<Map<String, Object>> ret = Lists.newLinkedList();
        for (List<T> generation : result.getGenerations()) {
            Map<String, Object> partOut = Maps.newHashMap();
            partOut.put(this.outputKey, generation.get(0).getTxt());
            ret.add(partOut);
        }
        return ret;
    }


}
