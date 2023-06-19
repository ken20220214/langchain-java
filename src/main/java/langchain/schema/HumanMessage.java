package langchain.schema;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class HumanMessage extends BaseMessage{

    private static final String TYPE = "user";
    private boolean example = false;

    @Override
    public String getRole() {
        return TYPE;
    }
}
