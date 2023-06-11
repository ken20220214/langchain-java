package langchain.schema;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Data
@SuperBuilder
public class AgentAction extends Action {

    private String tool;
    private Map<String, String> toolInput;

}
