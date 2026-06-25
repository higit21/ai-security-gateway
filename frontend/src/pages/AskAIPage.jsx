import { useState } from "react";

import api from "../services/api";

import ChatBox from "../components/ChatBox";
import SecurityPanel from "../components/SecurityPanel";

function AskAIPage({ loadLogs }) {

    const [prompt, setPrompt] =
        useState("");

    const [response, setResponse] =
        useState(null);

    const [loading, setLoading] =
        useState(false);

    const askAI = async () => {

        if (!prompt.trim()) {
            alert("Please enter a prompt");
            return;
        }

        setLoading(true);

        try {

            const res =
                await api.post(
                    "/ai/ask",
                    {
                        prompt
                    }
                );

            setResponse(res.data);

            if (loadLogs) {
                await loadLogs();
            }

        } catch (error) {

            console.error(
                "Request failed",
                error
            );

            alert("Request failed");

        } finally {

            setLoading(false);
        }
    };

    return (

        <div>

            <h1>Ask AI</h1>

            <div className="top-section">

                <ChatBox
                    prompt={prompt}
                    setPrompt={setPrompt}
                    askAI={askAI}
                    loading={loading}
                />

                <SecurityPanel
                    response={response}
                />

            </div>

            {response &&
                !response.blocked && (

                <div className="card">

                    <h2>AI Response</h2>

                    <p>
                        {response.response}
                    </p>

                </div>
            )}

        </div>
    );
}

export default AskAIPage;