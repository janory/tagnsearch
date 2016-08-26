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

// way 1
/*
store.subscribe(function(){
	console.log(store.getState());
})
*/

// way 2
//store.subscribe( () => { console.log(store.getState()); })

// way 3
store.subscribe( () => console.log(store.getState()) )


store.dispatch({type: "CHANGE_NAME", payload: "Janek"});