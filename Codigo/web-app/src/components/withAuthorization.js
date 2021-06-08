import React, { useEffect } from "react";
import { withRouter } from "react-router-dom";

const withAuthorization = (condition, pushPath) => (Component) => {
  const WithAuthorization = (props) => {
    const userContext = window.localStorage.getItem("token");

    useEffect(() => {
      if (!condition(userContext)) {
        if (pushPath) {
          props.history.push(pushPath);
        } else {
          props.history.push("/");
        }
      }
    }, [userContext, props.history]);

    return condition(userContext) ? (
      <Component authUser={userContext} {...props} />
    ) : null;
  };

  return withRouter(WithAuthorization);
};

export default withAuthorization;
