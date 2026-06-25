import {
    BrowserRouter,
    Routes,
    Route
} from "react-router-dom";

import Sidebar from "../components/Sidebar";

import DashboardPage from "../pages/DashboardPage";
import LogsPage from "../pages/LogsPage";
import ThreatsPage from "../pages/ThreatsPage";
import AskAIPage from "../pages/AskAIPage";

function AIPage() {

    return (

        <BrowserRouter>

            <div className="app-layout">

                <Sidebar />

                <div className="main-content">

                    <Routes>

                        <Route
                            path="/"
                            element={
                                <DashboardPage />
                            }
                        />

                        <Route
                            path="/ask"
                            element={
                                <AskAIPage />
                            }
                        />

                        <Route
                            path="/logs"
                            element={
                                <LogsPage />
                            }
                        />

                        <Route
                            path="/threats"
                            element={
                                <ThreatsPage />
                            }
                        />

                    </Routes>

                </div>

            </div>

        </BrowserRouter>
    );
}

export default AIPage;