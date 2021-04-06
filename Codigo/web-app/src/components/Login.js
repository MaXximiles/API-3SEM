import "./Login.css";
import React, { useState } from "react";
import NegativeMessage from "./NegativeMessage";

const Login = ({ onSubmit, href }) => {
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [error, setError] = useState({
    header: "",
    message: "",
  });

  const onLogin = async (event) => {
    event.preventDefault();
    if (await onSubmit(email, senha)) {
      window.history.pushState({}, "", href);

      const navEvent = new PopStateEvent("popstate");
      window.dispatchEvent(navEvent);
    } else {
      setError({
        header: "Não foi possível realizar o login",
        message: "Email ou senha incorretos",
      });
    }
  };

  const showError = () => {
    if (error.message !== "") {
      return <NegativeMessage error={error} setError={setError} />;
    }

    return null;
  };

  return (
    <div className="Login">
      <div className="ui two column grid">
        <div className="two column row">
          <div className="center aligned column">
            <h2 className="ui teal image header">
              <img
                className="image"
                src="https://user-images.githubusercontent.com/18652465/111547833-88631a00-8758-11eb-863c-ccf1e6e93f39.png"
                alt="TraceFinder"
              />
              <div className="content">Faça login em sua conta</div>
            </h2>
            <form className="ui form" onSubmit={(e) => onLogin(e)}>
              <div className="ui stacked segment">
                {showError()}
                <div className="field">
                  <div className="ui left icon input">
                    <i className="user icon"></i>
                    <input
                      type="text"
                      name="email"
                      placeholder="Email"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                    />
                  </div>
                </div>
                <div className="field">
                  <div className="ui left icon input">
                    <i className="lock icon"></i>
                    <input
                      type="password"
                      name="password"
                      placeholder="Senha"
                      value={senha}
                      onChange={(e) => setSenha(e.target.value)}
                    />
                  </div>
                </div>
                <input
                  type="submit"
                  value="Login"
                  className="ui fluid large teal submit button"
                />
              </div>
            </form>

            <div className="ui message">Não possuí uma conta? Cadastre-se</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
