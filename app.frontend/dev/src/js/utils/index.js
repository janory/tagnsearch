import { UserAuthWrapper } from 'redux-auth-wrapper'
import { push } from 'redux-router';

export const requireAuthentication = UserAuthWrapper({
    authSelector: state => state.user,
    predicate: user => user.isAuthenticated,
    // convert history location descriptor from 2.0 to 1.0
    /*
    redirectAction: ({ pathname, query }) => {
      if (query.redirect) {
        return push(null, `${pathname}?next=${query.redirect}`)
      } else {
        return push(null, pathname)
      }

    },*/
    wrapperDisplayName: 'UserIsJWTAuthenticated'
})
