package langchian.schema.prompts.base;

import com.google.common.collect.Maps;
import langchian.schema.PromptValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BasePromptTemplate {

    protected List<String> inputVariables = null;

    /**
     * some states(stored as Memory module in LangChain, such as chat history) may be inserted into prompt,
     * you can load states values at this point, then decorate PromptValue in the formatPrompt
     * @param inputs
     * @return
     */
    protected Map<String, Object> mergePartialAndUserInputs(Map<String, String> inputs) {
        HashMap<String, Object> ret = Maps.newHashMap();
        ret.putAll(inputs);
        return ret;
    }

    public PromptValue formatPrompt(Map<String, String> userInputs) {
        Map<String, Object> innerStateAndUserInput = mergePartialAndUserInputs(userInputs);
        return innerFormatPrompt(innerStateAndUserInput);
    }

    public abstract PromptValue innerFormatPrompt(Map<String, Object> kwargs);

    public abstract String format(Map<String, String> kwargs);

}
