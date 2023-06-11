package langchian.agents.agent;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import langchian.callbacks.base.BaseCallbackManager;
import langchian.chains.llm.LLMChain;
import langchian.schema.Action;
import langchian.schema.AgentFinish;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
public abstract class Agent extends BaseActionAgent {

    private LLMChain llmChain;

    public abstract List<String> getStops();

    public abstract AgentOutputParser getParser();

    @Override
    public List<Action> plan(
            List<SingleStep> intermediateSteps, BaseCallbackManager manager,
            Map<String, Object> inputs) throws IOException {
        Map<String, Object> tmp = this.getFullInputs(intermediateSteps, inputs);
        String ret = llmChain.predict(tmp);
        return Lists.newArrayList(getParser().parse(ret));
    }

    private static final String SCRATCH_PAD_KEY = "agent_scratchpad";
    private static final String STOP_KEY = "stop";

    private Map<String, Object> getFullInputs(
            List<SingleStep> intermediateSteps, Map<String, Object> inputs) {

        Object scratchPad = constructScratchPad(intermediateSteps);

        assert (scratchPad instanceof String || scratchPad instanceof List);

        Map<String, Object> ret = Maps.newHashMap();
        ret.put(SCRATCH_PAD_KEY, scratchPad);
        ret.put(STOP_KEY, getStops());

        ret.putAll(inputs);
        return ret;
    }

    public abstract String getLlmPrefix();

    public abstract String getObservationPrefix();

    protected Object constructScratchPad(List<SingleStep> intermediateSteps) {

        StringBuilder thought = new StringBuilder();
        for (SingleStep ss : intermediateSteps) {
            thought.append(ss.getAction().getLog())
                    .append("\n").append(getObservationPrefix()).append(ss.getObservation())
                    .append("\n").append(getLlmPrefix());
        }

        return thought.toString();
    }


    public static final String GENERATION_STOP = "generation";
    private static final String SUFFIX = "I now need to return a final answer based on the previous steps:";
    private static final String SCRATCHPAD_KEY = "agent_scratchpad";

    @Override
    public AgentFinish returnStoppedResponse(String earlyStoppingMethod, List<SingleStep> intermediateSteps,
                                             Map<String, Object> inputs) throws IOException {

        if (GENERATION_STOP.equals(earlyStoppingMethod)) {
            String thought = constructScratchPad(intermediateSteps).toString();
            Map<String, Object> tmp = Maps.newHashMap();
            tmp.put(SCRATCH_PAD_KEY, thought);
            tmp.put(STOP_KEY, this.getStops());
            tmp.putAll(inputs);
            String output = this.llmChain.predict(inputs);
            Action action = this.getParser().parse(output);

            if (action instanceof AgentFinish) {
                return (AgentFinish) action;
            } else {
                Map<String, Object> returnValues = Maps.newHashMap();
                returnValues.put("output", output);
                AgentFinish ret = AgentFinish.builder().returnValues(returnValues).log(output).build();
                return ret;
            }
        } else {
            return super.returnStoppedResponse(earlyStoppingMethod, intermediateSteps, inputs);
        }
    }
}
