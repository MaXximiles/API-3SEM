import React, { useState, useEffect } from "react";

const DocumentEdit = ({ onSubmit, dataEntry }) => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [nivel, setNivel] = useState("");
  const [login, setLogin] = useState("");

  useEffect(() => {
    if (dataEntry) {
      console.log(dataEntry);
      setName(dataEntry.usuarionome);
      setEmail(dataEntry.usuarioemail);
      setNivel(dataEntry.usuarionivel);
      setLogin(dataEntry.usuariologin);
    } else {
      clearFields();
    }
  }, [dataEntry]);

  const clearFields = () => {
    setName("");
    setEmail("");
    setNivel("");
    setLogin("");
  };

  const submit = async () => {
    const submitedEntry = {
      usuarionome: name,
      usuarioemail: email,
      usuarionivel: nivel,
      usuariologin: login,
      usuariosenha: "12345678",
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
            <label>Email</label>
            <input
              type="text"
              name="email"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>
          <div className="field">
            <label>Nivel</label>
            <input
              type="text"
              name="nivel"
              placeholder="Nível"
              value={nivel}
              onChange={(e) => setNivel(e.target.value)}
            />
          </div>
          <div className="field">
            <label>Login</label>
            <input
              type="text"
              name="login"
              placeholder="Login"
              value={login}
              onChange={(e) => setLogin(e.target.value)}
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
