import {
    BarChart,
    Bar,
    XAxis,
    YAxis,
    Tooltip,
    ResponsiveContainer,
    CartesianGrid
} from "recharts";

function ThreatDistributionChart({ logs }) {

    const threatMap = {};

    logs.forEach(log => {

        if (!log.issues) {
            return;
        }

        log.issues
            .split(",")
            .forEach(issue => {

                const trimmed =
                    issue.trim();

                if (!trimmed) {
                    return;
                }

                threatMap[trimmed] =
                    (threatMap[trimmed] || 0) + 1;
            });
    });

    const data =
        Object.entries(threatMap)
            .map(([name, count]) => ({
                name,
                count
            }));

    return (

        <div className="card">

            <h2>
                Threat Distribution
            </h2>

            <ResponsiveContainer
                width="100%"
                height={350}
            >

                <BarChart data={data}>

                    <CartesianGrid />

                    <XAxis dataKey="name" />

                    <YAxis />

                    <Tooltip />

                    <Bar dataKey="count" />

                </BarChart>

            </ResponsiveContainer>

        </div>
    );
}

export default ThreatDistributionChart;