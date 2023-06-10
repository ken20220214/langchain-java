package langchian.schema;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ChatGeneration extends Generation{

    private BaseMessage message;
}
