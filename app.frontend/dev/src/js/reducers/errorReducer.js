export default function reducer(state = {
    messageTitle: null,
    messageBody: null
}, action) {

    switch (action.type) {
        case "ERROR_SET": {
                return {...state, messageTitle: action.payload.message, messageBody: action.payload.response.data.message}
        }
        case "ERROR_CLEAR": {
                return {...state, messageTitle: null, messageBody: null}
        }
    }

    return state;
}
