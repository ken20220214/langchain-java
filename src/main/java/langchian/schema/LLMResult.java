package langchian.schema;

import java.util.List;
import java.util.Map;

public class LLMResult {

    private List<List<Generation>> generations = null;
    private Map<String, Object> llmOutput = null;

    public List<List<Generation>> getGenerations() {
        return generations;
    }

    public void setGenerations(List<List<Generation>> generations) {
        this.generations = generations;
    }

    public Map<String, Object> getLlmOutput() {
        return llmOutput;
    }

    public void setLlmOutput(Map<String, Object> llmOutput) {
        this.llmOutput = llmOutput;
    }
}
