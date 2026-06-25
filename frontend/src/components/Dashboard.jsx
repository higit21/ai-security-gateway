import { useEffect, useState } from "react";
import api from "../services/api";

function Dashboard() {

    const [stats, setStats] =
        useState(null);

    useEffect(() => {

        const loadDashboard =
            async () => {

                try {

                    const res =
                        await api.get(
                            "/ai/dashboard"
                        );

                        console.log(
                        "Dashboard Response",
                        res.data
                    );

                    setStats(
                        res.data
                    );

                } catch (error) {

                    console.error(
                        error
                    );
                }
            };

        loadDashboard();

    }, []);

    if (!stats) {

        return (
            <p>
                Loading Dashboard...
            </p>
        );
    }

    return (

        <div>

            <div className="stats-grid">

                <div className="stat-card">

                    <h3>
                        Total Requests
                    </h3>

                    <p>
                        {
                            stats.totalRequests
                        }
                    </p>

                </div>

                <div className="stat-card">

                    <h3>
                        Blocked
                    </h3>

                    <p className="critical">
                        {
                            stats.blockedRequests
                        }
                    </p>

                </div>

                <div className="stat-card">

                    <h3>
                        Safe
                    </h3>

                    <p className="low">
                        {
                            stats.allowedRequests
                        }
                    </p>

                </div>

                <div className="stat-card">

                    <h3>
                        Avg Risk
                    </h3>

                    <p>
                        {
                            Number(
                                stats.averageRiskScore
                            ).toFixed(1)
                        }
                    </p>

                </div>

            </div>

            <div className="card">

                <h2>
                    Top Threats
                </h2>

                <table
                    className="threat-table"
                >

                    <thead>

                    <tr>

                        <th>
                            Threat
                        </th>

                        <th>
                            Count
                        </th>

                    </tr>

                    </thead>

                    <tbody>

                    {Object.entries(
                        stats.threatCounts
                    ).map(
                        ([threat, count]) => (

                        <tr
                            key={threat}
                        >

                            <td>
                                {threat}
                            </td>

                            <td>
                                {count}
                            </td>

                        </tr>

                    ))}

                    </tbody>

                </table>

            </div>

        </div>
    );
}

export default Dashboard;