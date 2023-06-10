package langchian.schema;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class HumanMessage extends BaseMessage{

    private static final String TYPE = "human";
    private boolean example = false;

    @Override
    public String getType() {
        return TYPE;
    }
}
