package langchian.schema;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class SystemMessage extends BaseMessage {

    private static final String TYPE = "system";

    @Override
    public String getType() {
        return TYPE;
    }
}
