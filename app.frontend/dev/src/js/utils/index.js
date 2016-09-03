import { UserAuthWrapper } from 'redux-auth-wrapper'
import { push } from 'redux-router';

export const requireAuthentication = UserAuthWrapper({
    authSelector: state => state.user,
    predicate: user => user.isAuthenticated,
    wrapperDisplayName: 'UserIsJWTAuthenticated'
})
