import axios from "axios";
import md5 from "md5";


export function loginUser(username, password) {
	return {
		type: "LOGIN_USER",
		payload: axios.post('login', {
			username: username,
			password: md5(password)
		})
	}
}
