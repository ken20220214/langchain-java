package langchain.schema;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AIMessage extends BaseMessage {

    private static final String TYPE = "assistant";
    private boolean example = false;

    @Override
    public String getRole() {
        return TYPE;
    }
}
