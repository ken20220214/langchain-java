package langchian.schema;

import java.util.List;
import java.util.Map;

public class LLMResult<T extends Generation> {

    private List<List<T>> generations = null;
    private Map<String, Object> llmOutput = null;

    public List<List<T>> getGenerations() {
        return generations;
    }

    public void setGenerations(List<List<T>> generations) {
        this.generations = generations;
    }

    public Map<String, Object> getLlmOutput() {
        return llmOutput;
    }

    public void setLlmOutput(Map<String, Object> llmOutput) {
        this.llmOutput = llmOutput;
    }
}
