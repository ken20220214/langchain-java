package langchian.chat.base;

import com.google.common.collect.Lists;
import langchian.agents.agent.Agent;
import langchian.agents.agent.AgentOutputParser;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class ChatAgent extends Agent {
    @Override
    public List<String> getStops() {
        List<String> ret = Lists.newArrayList("Observation:");
        return ret;
    }

    @Override
    public AgentOutputParser getParser() {
        return null;
    }

    @Override
    public String getLlmPrefix() {
        return "Thought:";
    }

    @Override
    public String getObservationPrefix() {
        return "Observation: ";
    }

    @Override
    public List<String> getAllowedTools() {
        return null;
    }
}
