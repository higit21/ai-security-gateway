import { Link } from "react-router-dom";

function Sidebar() {
    return (
        <div className="sidebar">
            <h2>AI Shield</h2>

            <ul>
                <li>
                    <Link to="/">Dashboard</Link>
                </li>

                <li>
                    <Link to="/ask">Ask AI</Link>
                </li>

                <li>
                    <Link to="/logs">Audit Logs</Link>
                </li>

                <li>
                    <Link to="/threats">Threat Analytics</Link>
                </li>
            </ul>
        </div>
    );
}

export default Sidebar;