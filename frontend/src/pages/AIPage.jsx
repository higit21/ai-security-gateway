import {
    BrowserRouter,
    Routes,
    Route
} from "react-router-dom";

import Sidebar from "../components/Sidebar";
import ProtectedRoute from "../components/ProtectedRoute";
import TokenExpirationHandler from "../components/TokenExpirationHandler";

import DashboardPage from "./DashboardPage";
import LogsPage from "./LogsPage";
import ThreatsPage from "./ThreatsPage";
import AskAIPage from "./AskAIPage";
import LoginPage from "./LoginPage";
import RegisterPage from "./RegisterPage";


function AppLayout() {

    return (

        <div className="app-layout">
            
            <TokenExpirationHandler />

            <Sidebar />

            <div className="main-content">

                <Routes>

                    <Route
                        path="/"
                        element={
                            <ProtectedRoute>
                                <DashboardPage />
                            </ProtectedRoute>
                        }
                    />

                    <Route
                        path="/ask"
                        element={
                            <ProtectedRoute>
                                <AskAIPage />
                            </ProtectedRoute>
                        }
                    />

                    <Route
                        path="/logs"
                        element={
                            <ProtectedRoute>
                                <LogsPage />
                            </ProtectedRoute>
                        }
                    />

                    <Route
                        path="/threats"
                        element={
                            <ProtectedRoute>
                                <ThreatsPage />
                            </ProtectedRoute>
                        }
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

                {/* Public routes */}

                <Route
                    path="/login"
                    element={<LoginPage />}
                />

                <Route
                    path="/register"
                    element={<RegisterPage />}
                />


                {/* Protected application */}

                <Route
                    path="/*"
                    element={<AppLayout />}
                />

            </Routes>

        </BrowserRouter>
    );
}

export default AIPage;