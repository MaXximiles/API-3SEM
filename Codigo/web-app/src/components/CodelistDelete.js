import React from "react";

const CodelistDelete = ({ onSubmit, dataEntry }) => {
  return (
    <React.Fragment>
      <div className="content">
        Tem certeza que deseja deletar o bloco{" "}
        {dataEntry ? dataEntry.codelistnbloco : ""}?
      </div>
      <div className="actions">
        <div className="ui negative button" onClick={onSubmit}>
          Não
        </div>
        <div
          className="ui positive right labeled icon button"
          onClick={() => onSubmit(dataEntry)}
        >
          Sim
          <i className="checkmark icon"></i>
        </div>
      </div>
    </React.Fragment>
  );
};

export default CodelistDelete;
