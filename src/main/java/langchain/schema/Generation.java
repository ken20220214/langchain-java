package langchain.schema;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Data
@SuperBuilder
public class Generation {

    private String text;
    private Map<String, Object> generationInfo;

}
