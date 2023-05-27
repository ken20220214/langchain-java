package langchian.schema;

import java.util.List;
import java.util.Map;

public abstract class BaseMemory {

    private List<String> memoryVariables;

    public abstract Map<String, String> loadMemoryVariables(Map<String, String> inputs);

    public abstract void saveContext(Map<String, String> inputs, Map<String, String> outputs);

    public abstract void clear();

}
