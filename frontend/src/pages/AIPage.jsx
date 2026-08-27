import {
    BrowserRouter,
    Routes,
    Route
} from "react-router-dom";

import Sidebar from "../components/Sidebar";

import DashboardPage from "./DashboardPage";
import LogsPage from "./LogsPage";
import ThreatsPage from "./ThreatsPage";
import AskAIPage from "./AskAIPage";
import LoginPage from "./LoginPage";

function AppLayout() {
    return (
        <div className="app-layout">

            <Sidebar />

            <div className="main-content">
                <Routes>

                    <Route
                        path="/"
                        element={<DashboardPage />}
                    />

                    <Route
                        path="/ask"
                        element={<AskAIPage />}
                    />

                    <Route
                        path="/logs"
                        element={<LogsPage />}
                    />

                    <Route
                        path="/threats"
                        element={<ThreatsPage />}
                    />

                </Routes>
            </div>

        </div>
    );
}

function AIPage() {

    return (

        <BrowserRouter>

            <Routes>

                {/* Login has no Sidebar */}
                <Route
                    path="/login"
                    element={<LoginPage />}
                />

                {/* Application layout */}
                <Route
                    path="/*"
                    element={<AppLayout />}
                />

            </Routes>

        </BrowserRouter>
    );
}

export default AIPage;