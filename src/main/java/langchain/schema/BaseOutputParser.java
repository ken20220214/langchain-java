package langchain.schema;

public abstract interface BaseOutputParser<T> {

    /**
     * Parse the output of an LLM call.
     *
     *         A method which takes in a string (assumed output of a language model )
     *         and parses it into some structure.
     * @param text output of language model
     * @return structured output
     */
    public abstract T parse(String text);

    /**
     * Optional method to parse the output of an LLM call with a prompt.
     *
     *         The prompt is largely provided in the event the OutputParser wants
     *         to retry or fix the output in some way, and needs information from
     *         the prompt to do so.
     * @param content output of language model
     * @param prompt prompt value
     * @return structured output
     */
    public abstract T parseWithPrompt(String content, PromptValue prompt);

    /**
     * Instructions on how the LLM output should be formatted.
     * @return
     */
    public abstract String getFormatInstructions();

}
