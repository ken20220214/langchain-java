package langchain.schema;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Data
@SuperBuilder
public class BaseMessage {

    protected String content;
    protected String role;
    protected Map<String, Object> additionalKwargs;
}
