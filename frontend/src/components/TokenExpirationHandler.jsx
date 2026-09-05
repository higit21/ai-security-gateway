import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

function TokenExpirationHandler() {

    const navigate = useNavigate();

    useEffect(() => {

        const token = localStorage.getItem("token");

        if (!token) {
            return;
        }

        try {

            const payload = token.split(".")[1];

            const decodedPayload = JSON.parse(
                atob(payload)
            );

            const expirationTime =
                decodedPayload.exp * 1000;

            const currentTime = Date.now();

            const timeout = expirationTime - currentTime;

            if (timeout <= 0) {

                localStorage.removeItem("token");

                navigate("/login");

                return;
            }

            const timer = setTimeout(() => {

                localStorage.removeItem("token");

                navigate("/login");

            }, timeout);

            return () => clearTimeout(timer);

        } catch {

            localStorage.removeItem("token");

            navigate("/login");

        }

    }, [navigate]);

    return null;
}

export default TokenExpirationHandler;