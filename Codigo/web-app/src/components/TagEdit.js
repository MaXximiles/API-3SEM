import React, { useState, useEffect } from "react";

const DocumentEdit = ({ onSubmit, dataEntry }) => {
  const [name, setName] = useState("");

  useEffect(() => {
    if (dataEntry) {
      setName(dataEntry.tagNome);
    } else {
      clearFields();
    }
  }, [dataEntry]);

  const clearFields = () => {
    setName("");
  };

  const submit = async () => {
    const submitedEntry = {
      tagNome: name,
    };

    if (dataEntry) {
      await onSubmit(submitedEntry, dataEntry);
    } else {
      await onSubmit(submitedEntry);
    }

    clearFields();
  };

  return (
    <React.Fragment>
      <div className="content">
        <form className="ui form">
          <div className="field">
            <label>Nome</label>
            <input
              type="text"
              name="name"
              placeholder="Nome"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </div>
        </form>
      </div>
      <div className="actions">
        <div className="ui positive right labeled icon button" onClick={submit}>
          Confirmar
          <i className="checkmark icon"></i>
        </div>
      </div>
    </React.Fragment>
  );
};

export default DocumentEdit;
