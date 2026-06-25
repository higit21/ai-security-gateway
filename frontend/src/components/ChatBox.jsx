function ChatBox({
    prompt,
    setPrompt,
    askAI,
    loading
}) {

    return (

        <div className="card">

            <h2>Ask AI</h2>

            <textarea
                rows="6"
                value={prompt}
                onChange={(e) =>
                    setPrompt(e.target.value)
                }
                placeholder="Enter your prompt..."
            />

            <br />

            <button
                onClick={askAI}
                disabled={loading}
            >
                {loading
                    ? "Analyzing..."
                    : "Ask AI"}
            </button>

        </div>
    );
}

export default ChatBox;