import React from "react";

const NegativeMessage = ({ error, onClose }) => {
  return (
    <div className="ui negative message">
      <i
        className="close icon"
        onClick={onClose({
          header: "",
          message: "",
        })}
      ></i>
      <div className="header">{error.header}</div>
      <p>{error.message}</p>
    </div>
  );
};

export default NegativeMessage;
