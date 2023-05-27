package langchian.schema;

import java.util.List;
import java.util.Map;

public class ChatResult {

    List<ChatGeneration> generations;
    Map<String, Object> llmOut;

    public List<ChatGeneration> getGenerations() {
        return generations;
    }

    public void setGenerations(List<ChatGeneration> generations) {
        this.generations = generations;
    }

    public Map<String, Object> getLlmOut() {
        return llmOut;
    }

    public void setLlmOut(Map<String, Object> llmOut) {
        this.llmOut = llmOut;
    }
}
