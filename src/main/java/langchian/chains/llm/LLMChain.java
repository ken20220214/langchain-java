package langchian.chains.llm;

import langchian.schema.Chain;
import langchian.schema.prompts.base.BasePromptTemplate;
import langchian.schema.prompts.base.language.BaseLanguageModel;

import java.util.Map;

public class LLMChain extends Chain {

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
    protected Map<String, String> _call(Map<String, String> inputs) {
        return null;
    }
}
