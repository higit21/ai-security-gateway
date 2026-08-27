import { useState } from "react";
import api from "../services/api";

function LoginPage() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    async function handleLogin(event) {

    event.preventDefault();

    try {

    const response = await api.post(
        "/auth/login",
        {
            username: username,
            password: password
        }
    );

    localStorage.setItem("token", response.data);

    console.log("Login successful");
    console.log(response.data);

} catch (error) {

    console.error("Login failed:", error);

    }
}

    return (
        <div>

            <h1>Login</h1>

            <form onSubmit={handleLogin}>

                <input
                    type="text"
                    placeholder="Username"
                    value={username}
                    onChange={(event) =>
                        setUsername(event.target.value)
                    }
                />

                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(event) =>
                        setPassword(event.target.value)
                    }
                />

                <button type="submit">
                    Login
                </button>

            </form>

        </div>
    );
}

export default LoginPage;