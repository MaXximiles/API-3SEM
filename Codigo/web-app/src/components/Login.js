import "./Login.css";
import React, { useState } from "react";
import { Link, withRouter } from "react-router-dom";
import NegativeMessage from "./NegativeMessage";

const Login = ({ onSubmit, history }) => {
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [error, setError] = useState({
    header: "",
    message: "",
  });
  const [isLoading, setIsLoading] = useState(false);

  const onLogin = async (event) => {
    event.preventDefault();

    setIsLoading(true);

    if (await onSubmit(email, senha)) {
      history.push("/documento");
    } else {
      setError({
        header: "Não foi possível realizar o login",
        message: "Email ou senha incorretos",
      });
    }

    setIsLoading(false);
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
            <form
              className={`ui form ${isLoading ? "loading" : ""}`}
              onSubmit={(e) => onLogin(e)}
            >
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
            <div className="ui message">
              Não possuí uma conta? <Link to="/cadastrar">Cadastre-se</Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default withRouter(Login);
