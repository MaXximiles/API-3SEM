import React, { useState, useEffect } from "react";

const DocumentEdit = ({ onSubmit, dataEntry }) => {
  const [name, setName] = useState("");
  const [pn, setPn] = useState("");

  useEffect(() => {
    if (dataEntry) {
      setName(dataEntry.documentonome);
      setPn(dataEntry.documentopn);
    } else {
      clearFields();
    }
  }, [dataEntry]);

  const clearFields = () => {
    setName("");
    setPn("");
  };

  const submit = async () => {
    const submitedEntry = {
      documentonome: name,
      documentopn: pn,
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
              name="section"
              placeholder="Seção"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </div>
          <div className="field">
            <label>PN</label>
            <input
              type="text"
              name="subsection"
              placeholder="Subseção"
              value={pn}
              onChange={(e) => setPn(e.target.value)}
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
