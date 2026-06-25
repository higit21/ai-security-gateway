function AuditLogs({ logs }) {

    const getRiskClass = (riskScore) => {
        if (riskScore >= 90) return "critical";
        if (riskScore >= 70) return "high";
        if (riskScore >= 40) return "medium";
        return "low";
    };

    const formatDate = (dateValue) => {
        if (!dateValue) return "-";

        return new Date(dateValue).toLocaleString();
    };

    return (
        <div className="card">

            <h2>Audit Logs</h2>

            {logs.length === 0 ? (
                <p>No audit logs found.</p>
            ) : (
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Status</th>
                        <th>Risk</th>
                        <th>Threats</th>
                        <th>Prompt</th>
                        <th>Created At</th>
                    </tr>
                    </thead>

                    <tbody>
                    {logs.map((log) => (
                        <tr
                            key={log.id}
                            className={
                                log.blocked ? "blocked-row" : ""
                            }
                        >
                            <td>{log.id}</td>

                            <td>
                                <span
                                    className={
                                        log.blocked
                                            ? "badge badge-blocked"
                                            : "badge badge-allowed"
                                    }
                                >
                                    {log.status}
                                </span>
                            </td>

                            <td>
                                <span className={getRiskClass(log.riskScore)}>
                                    {log.riskScore}
                                </span>
                            </td>

                            <td>{log.issues || "-"}</td>

                            <td>{log.prompt}</td>

                            <td>{formatDate(log.createdAt)}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}

        </div>
    );
}

export default AuditLogs;