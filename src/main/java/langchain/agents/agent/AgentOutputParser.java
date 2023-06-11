package langchain.agents.agent;

import langchain.schema.Action;
import langchain.schema.BaseOutputParser;

public interface AgentOutputParser extends BaseOutputParser<Action> {
}
