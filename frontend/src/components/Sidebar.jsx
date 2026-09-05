import { Link, useNavigate } from "react-router-dom";

function Sidebar() {

    const navigate = useNavigate();

    function handleLogout() {

        localStorage.removeItem("token");

        navigate("/login");
    }

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
                    <Link to="/threats">
                        Threat Analytics
                    </Link>
                </li>

            </ul>

            <button onClick={handleLogout}>
                Logout
            </button>

        </div>
    );
}

export default Sidebar;