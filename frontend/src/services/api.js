import axios from "axios";

const api = axios.create({
    baseURL: "http://13.63.236.228:8080",
    headers: {
        "Content-Type": "application/json"
    }
});

export default api;