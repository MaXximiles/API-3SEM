import React, { useState } from "react";

const ListItemContext = () => {
  const [isDropdownActive, toggleDropdown] = useState(false);

  return (
    <div className="ui secondary vertical menu">
      <div className="ui dropdown item">Deletar</div>
      <div className="ui dropdown item">Fazer Upload</div>
      <div
        className={`ui dropdown item 
        ${isDropdownActive ? "active visible" : ""}`}
        onMouseOver={() => toggleDropdown(true)}
        onMouseOut={() => toggleDropdown(false)}
      >
        <i className="dropdown icon"></i>
        Criar Novo...
        <div className={`menu ${isDropdownActive ? "transition visible" : ""}`}>
          <div className="header">Tipo</div>
          <div className="item">Diretório</div>
        </div>
      </div>
    </div>
  );
};

export default ListItemContext;
