import { UserAuthWrapper } from 'redux-auth-wrapper'

export const requireAuthentication = UserAuthWrapper({
    authSelector: state => state.user,
    predicate: user => user.isAuthenticated,
    wrapperDisplayName: 'UserIsJWTAuthenticated'
})
