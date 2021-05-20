import React, { useContext, useEffect } from "react";
import { withRouter } from "react-router-dom";

import UserContext from "./UserContext";

const withAuthorization = (condition) => (Component) => {
  const WithAuthorization = (props) => {
    const userContext = useContext(UserContext);

    useEffect(() => {
      if (!condition(userContext)) {
        props.history.push("/");
      }
    }, [userContext, props.history]);

    return condition(userContext) ? (
      <Component authUser={userContext} {...props} />
    ) : null;
  };

  return withRouter(WithAuthorization);
};

export default withAuthorization;
