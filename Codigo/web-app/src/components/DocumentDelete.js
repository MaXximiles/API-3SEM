import React from "react";

const DocumentDelete = ({ onSubmit, dataEntry }) => {
  return (
    <React.Fragment>
      <div className="content">
        Tem certeza que deseja deletar o manual{" "}
        {dataEntry ? dataEntry.documentonome + "-" + dataEntry.documentopn : ""}
        ?
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

export default DocumentDelete;
