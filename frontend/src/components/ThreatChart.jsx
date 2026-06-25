import {
    BarChart,
    Bar,
    XAxis,
    YAxis,
    Tooltip,
    CartesianGrid,
    ResponsiveContainer
} from "recharts";

function ThreatChart({ logs }) {

    const blockedCount =
        logs.filter(log => log.blocked).length;

    const safeCount =
        logs.filter(log => !log.blocked).length;

    const data = [
        {
            name: "Blocked",
            count: blockedCount
        },
        {
            name: "Safe",
            count: safeCount
        }
    ];

    return (

        <div className="card">

            <h2>
                Threat Overview
            </h2>

            <ResponsiveContainer
                width="100%"
                height={300}
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

export default ThreatChart;