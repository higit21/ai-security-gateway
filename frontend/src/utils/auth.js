export function isTokenValid() {

    const token = localStorage.getItem("token");

    if (!token) {
        return false;
    }

    try {

        const payload = token.split(".")[1];

        const decodedPayload = JSON.parse(
            atob(payload)
        );

        const currentTime = Date.now() / 1000;

        if (decodedPayload.exp < currentTime) {

            localStorage.removeItem("token");

            return false;
        }

        return true;

    } catch {

        localStorage.removeItem("token");

        return false;
    }
}