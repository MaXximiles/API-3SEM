import React, { useEffect } from "react";
import { withRouter } from "react-router-dom";

const withAuthorization = (condition) => (Component) => {
  const WithAuthorization = (props) => {
    const userContext = window.localStorage.getItem("token");

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
