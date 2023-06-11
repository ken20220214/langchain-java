package langchain.agents.agent;

import com.google.common.collect.Lists;
import langchain.chains.base.Chain;
import langchain.tools.base.BaseTool;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
public class AgentExecutor extends Chain {

    private BaseActionAgent agent;
    private List<BaseTool> tools;
    private boolean returnIntermediateSteps = false;
    private int maxIterations = 15;
    private String earlyStoppingMethod = BaseActionAgent.FORCE_STOP;

    @Override
    public List<String> getInputKeys() {
        return this.agent.getInputVariables();
    }

    @Override
    public List<String> getOutputKeys() {
        if (!returnIntermediateSteps)
            return this.agent.getReturnValues();
        else {
            List<String> ret = Lists.newLinkedList();
            ret.addAll(this.agent.getReturnValues());
            ret.add(Agent.SCRATCH_PAD_KEY);
            return ret;
        }
    }

    @Override
    protected Map<String, Object> _call(Map<String, Object> inputs) throws IOException {
        return null;
    }
}
