package langchian.schema;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Data
@SuperBuilder
public class AgentFinish extends Action{

    private Map<String, Object> returnValues;
}
