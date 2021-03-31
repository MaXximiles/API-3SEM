import React, { useState } from "react";

const DirectoryNodeContext = React.forwardRef(({ createNew }, ref) => {
  const [isDropdownActive, toggleDropdown] = useState(false);

  return (
    <div ref={ref} className="ui secondary vertical menu">
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
          <div className="item" onClick={createNew}>
            Diretório
          </div>
        </div>
      </div>
    </div>
  );
});

export default DirectoryNodeContext;
