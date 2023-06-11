package langchain.agents.agent;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import langchain.callbacks.base.BaseCallbackManager;
import langchain.schema.Action;
import langchain.schema.AgentAction;
import langchain.schema.AgentFinish;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
public abstract class BaseActionAgent {

    private List<String> returnValues;
    private List<String> inputVariables;
    private String agentType;

    public List<String> getReturnValues() {
        List<String> ret = Lists.newLinkedList();
        ret.add("output");
        return ret;
    }

    public abstract List<String> getAllowedTools();

    @Builder
    @Data
    public static class SingleStep {
        private AgentAction action;
        private String observation;
    }

    public abstract List<Action> plan(
            List<SingleStep> intermediateSteps,
            BaseCallbackManager manager,
            Map<String, Object> inputs
    ) throws IOException;


    public static final String FORCE_STOP = "force";
    public static final Map<String, Object> FORCE_RETURN_MAP = Maps.newHashMap();
    public static final AgentFinish FINISH_ACTION = AgentFinish.builder().build();
    static {
        FORCE_RETURN_MAP.put("output", "Agent stopped due to iteration limit or time limit.");
        FINISH_ACTION.setReturnValues(FORCE_RETURN_MAP);
    }

    public AgentFinish returnStoppedResponse(
            String earlyStoppingMethod,
            List<SingleStep> intermediateSteps,
            Map<String, Object> inputs
    ) throws IOException {
        if (FORCE_STOP.equals(earlyStoppingMethod)) {
            return FINISH_ACTION;
        } else {
            throw new IOException("unsupported stopping method " + earlyStoppingMethod);
        }
    }

}
