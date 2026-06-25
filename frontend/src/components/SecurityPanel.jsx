function SecurityPanel({ response }) {

    if (!response) {
        return (
            <div className="card">
                <h2>Security Analysis</h2>
                <p>No prompt analyzed yet.</p>
            </div>
        );
    }

    const getStatusClass = () => {
        if (response.status === "BLOCKED") {
            return "status-blocked";
        }

        if (response.status === "ALLOWED_AFTER_REDACTION") {
            return "status-redacted";
        }

        return "status-allowed";
    };

    const formatStatus = (status) => {
        if (status === "ALLOWED_AFTER_REDACTION") {
            return "ALLOWED AFTER REDACTION";
        }

        return status;
    };

    const getActionMessage = () => {
        if (response.status === "BLOCKED") {
            return "Request was blocked and not sent to the AI provider.";
        }

        if (response.status === "ALLOWED_AFTER_REDACTION") {
            return "Sensitive data was removed before sending the request to AI.";
        }

        return "No security issue found. Request was sent to AI.";
    };

    return (
        <div className="card">

            <h2>Security Analysis</h2>

            <p>
                <strong>Status: </strong>

                <span className={getStatusClass()}>
                    {formatStatus(response.status)}
                </span>
            </p>

            <p>
                <strong>Risk Score: </strong>
                {response.riskScore}
            </p>

            <p>
                <strong>Action Taken: </strong>
                {getActionMessage()}
            </p>

            <h3>Threats</h3>

            {response.issues &&
            response.issues.length > 0 ? (

                <ul>
                    {response.issues.map((issue, index) => (
                        <li key={index}>
                            <strong>{issue.type}</strong>
                            {" - "}
                            {issue.message}
                            {" ("}
                            {issue.severity}
                            {")"}
                        </li>
                    ))}
                </ul>

            ) : (

                <p>No threats detected.</p>
            )}

        </div>
    );
}

export default SecurityPanel;