import { useEffect, useState } from "react";

import api from "../services/api";

import ThreatChart from "../components/ThreatChart";
import ThreatDistributionChart
    from "../components/ThreatDistributionChart";

function ThreatsPage() {

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
                "Failed to load threat logs",
                error
            );

        } finally {

            setLoading(false);
        }
    };

    useEffect(() => {

        async function loadInitialThreatLogs() {

            await fetchLogs();
        }

        loadInitialThreatLogs();

    }, []);

    return (

        <div>

            <h1>
                Threat Analytics
            </h1>

            <button onClick={fetchLogs}>
                {loading
                    ? "Loading..."
                    : "Refresh Analytics"}
            </button>

            <ThreatChart logs={logs} />

            <ThreatDistributionChart
                logs={logs}
            />

        </div>
    );
}

export default ThreatsPage;