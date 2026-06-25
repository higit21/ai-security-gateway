import { useEffect, useState } from "react";

import api from "../services/api";
import AuditLogs from "../components/AuditLogs";

function LogsPage() {

    const [logs, setLogs] =
        useState([]);

    const [loading, setLoading] =
        useState(false);

    const fetchLogs = async () => {

        setLoading(true);

        try {

            const res =
                await api.get("/ai/logs");

            setLogs(res.data);

        } catch (error) {

            console.error(
                "Failed to load logs",
                error
            );

        } finally {

            setLoading(false);
        }
    };

    useEffect(() => {

        async function loadInitialLogs() {

            await fetchLogs();
        }

        loadInitialLogs();

    }, []);

    return (

        <div>

            <h1>
                Audit Logs
            </h1>

            <button onClick={fetchLogs}>
                {loading
                    ? "Loading..."
                    : "Refresh Logs"}
            </button>

            <AuditLogs logs={logs} />

        </div>
    );
}

export default LogsPage;