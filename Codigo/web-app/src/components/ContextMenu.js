import "./ContextMenu.css";
import React from "react";

const ContextMenu = ({ xy, children }) => {
  return (
    <div className="Context-Menu" style={xy}>
      {children}
    </div>
  );
};

export default ContextMenu;
