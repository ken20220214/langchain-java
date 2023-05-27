package langchian.schema.prompts.base;

import com.google.common.collect.Maps;
import langchian.schema.PromptValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BasePromptTemplate {

    private List<String> inputVariables = null;

    public List<String> getInputVariables() {
        return inputVariables;
    }

    public void setInputVariables(List<String> inputVariables) {
        this.inputVariables = inputVariables;
    }

    /**
     * some states(stored as Memory module in LangChain, such as chat history) may be inserted into prompt,
     * you can load states values at this point, then decorate PromptValue in the formatPrompt
     * @param inputs
     * @return
     */
    protected Map<String, Object> mergePartialAndUserInputs(Map<String, Object> inputs) {
        HashMap<String, Object> ret = Maps.newHashMap();
        ret.putAll(inputs);
        return ret;
    }

    public PromptValue formatPrompt(Map<String, Object> userInputs) {
        Map<String, Object> innerStateAndUserInput = mergePartialAndUserInputs(userInputs);
        return innerFormatPrompt(innerStateAndUserInput);
    }

    public abstract PromptValue innerFormatPrompt(Map<String, Object> kwargs);

    public abstract String format(Map<String, Object> kwargs);

}
