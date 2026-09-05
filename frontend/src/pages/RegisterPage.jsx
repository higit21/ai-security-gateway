import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

function RegisterPage() {

    const navigate = useNavigate();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    async function handleRegister(event) {

        event.preventDefault();

        try {

            await api.post(
                "/auth/register",
                {
                    username: username,
                    password: password
                }
            );

            console.log("Registration successful");

            navigate("/login");

        } catch (error) {

            console.error("Registration failed:", error);

        }
    }

    return (
        <div>

            <h1>Register</h1>

            <form onSubmit={handleRegister}>

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
                    Register
                </button>

            </form>

        </div>
    );
}

export default RegisterPage;