import "./ContextMenu.css";
import React from "react";
import ReactDOM from "react-dom";

const ContextMenu = ({ xy, children }) => {
  return ReactDOM.createPortal((
    <div className="Context-Menu" style={xy}>
      {children}
    </div>
  ), document.querySelector("#root"));
};

export default ContextMenu;
