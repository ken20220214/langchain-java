package langchain.schema;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
public class ChatResult {

    private List<ChatGeneration> generations;
    private Map<String, Object> llmOutput;
}
