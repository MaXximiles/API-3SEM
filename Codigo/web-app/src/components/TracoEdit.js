import React, { useState, useEffect } from "react";
import Dropdown from "./Dropdown";
import restAPI from "../apis/restAPI";

const DocumentEdit = ({ onSubmit, dataEntry }) => {
  const [name, setName] = useState("");
  const [descricao, setDescricao] = useState("");
  const [codigo, setCodigo] = useState("");

  useEffect(() => {
    if (dataEntry) {
      setName(dataEntry.tracodocnome);
      setDescricao(dataEntry.tracodocdescricao);
      setCodigo(dataEntry.tracodoccodigo);
    } else {
      clearFields();
    }
  }, [dataEntry]);

  const clearFields = () => {
    setName("");
    setDescricao("");
    setCodigo("");
  };

  const submit = async () => {
    const submitedEntry = {
      tracodocnome: name,
      tracodocdescricao: descricao,
      tracodoccodigo: codigo,
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
          <div className="field">
            <label>Codigo</label>
            <input
              type="text"
              name="codigo"
              placeholder="Código"
              value={codigo}
              onChange={(e) => setCodigo(e.target.value)}
            />
          </div>
          <div className="field">
            <label>Descricao</label>
            <input
              type="text"
              name="descricao"
              placeholder="Descricao"
              value={descricao}
              onChange={(e) => setDescricao(e.target.value)}
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
