import { createStore } from "redux";

const reducer = (state, action) => {
	if ( action.type === "CHANGE_NAME" ) {
		return state.user.username = action.payload;
	}
	return state;
};

const store = createStore(reducer, 
	 { user: {
		username: "",
		password: ""
	} 
});

const showNewValues = (newState) => {
	console.log(newState)
};

store.subscribe( () => {
	showNewValues(store.getState());
})

store.dispatch({type: "CHANGE_NAME", payload: "Janek"});