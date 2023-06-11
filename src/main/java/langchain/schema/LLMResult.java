package langchain.schema;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
public class LLMResult<T extends Generation> {

    private List<List<T>> generations;
    private Map<String, Object> llmOutput;

}
