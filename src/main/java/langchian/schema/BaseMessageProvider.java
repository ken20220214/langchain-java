package langchian.schema;

import java.util.List;
import java.util.Map;

public interface BaseMessageProvider {

    public List<BaseMessage> provideMessages(Map<String, Object> kwargs);

}
