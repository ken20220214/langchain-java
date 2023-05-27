package langchian.schema;

import java.util.List;
import java.util.Map;

public abstract class BaseMemory {

    private List<String> memoryVariables;

    public abstract Map<String, Object> loadMemoryVariables(Map<String, Object> inputs);

    public abstract void saveContext(Map<String, Object> inputs, Map<String, Object> outputs);

    public abstract void clear();

}
