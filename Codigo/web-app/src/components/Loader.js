import React from "react";

const Loader = ({ isLoading, text = "" }) => {
  return (
    <div className={`ui ${isLoading ? "active" : ""} inverted dimmer`}>
      <div className="ui medium text loader">{text}</div>
    </div>
  );
};

export default Loader
