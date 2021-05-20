import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";
import restAPI from "../apis/restAPI";

import NegativeMessage from "./NegativeMessage";

const SignUpPage = () => (
  <div className="ui container">
    <h1>SignUp</h1>
    <SignUpForm />
  </div>
);

const INITIAL_STATE = {
  name: "",
  username: "",
  email: "",
  passwordOne: "",
  passwordTwo: "",
  error: null,
  loading: false,
};

class SignUpFormBase extends Component {
  constructor(props) {
    super(props);

    this.state = { ...INITIAL_STATE };
  }

  onSubmit = (event) => {
    event.preventDefault();

    this.setState({ loading: true });

    const { name, username, email, passwordOne } = this.state;

    restAPI
      .post("/usuarios/", {
        usuarionome: name,
        usuariologin: username,
        usuariosenha: passwordOne,
        usuarioemail: email,
        usuarionivel: "0",
      })
      .then(() => {
        this.setState({ ...INITIAL_STATE });
        this.props.history.push("/documento");
      })
      .catch((error) => {
        this.setState({ error, loading: false });
      });
  };

  onChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  onCloseMessage = () => {
    this.setState({ error: null });
  };

  render() {
    const { name, username, email, passwordOne, passwordTwo, error, loading } =
      this.state;

    const isInvalid =
      passwordOne !== passwordTwo ||
      passwordOne === "" ||
      email === "" ||
      username === "" ||
      name === "";

    const isMatch =
      passwordOne === passwordTwo || (passwordOne === "" && passwordTwo === "");

    return (
      <form
        className={`ui form ${loading ? "loading" : ""}`}
        onSubmit={this.onSubmit}
      >
        {error && (
          <NegativeMessage error={error} onClose={this.onCloseMessage} />
        )}
        <div className="field">
          <input
            name="name"
            value={name}
            onChange={this.onChange}
            type="text"
            placeholder="Nome Completo"
          />
        </div>
        <div className="field">
          <input
            name="username"
            value={username}
            onChange={this.onChange}
            type="text"
            placeholder="Login"
          />
        </div>
        <div className="field">
          <input
            name="email"
            value={email}
            onChange={this.onChange}
            type="text"
            placeholder="Email"
          />
        </div>
        <div className="field">
          <input
            name="passwordOne"
            value={passwordOne}
            onChange={this.onChange}
            type="password"
            placeholder="Senha"
          />
        </div>
        <div className="field">
          <input
            name="passwordTwo"
            value={passwordTwo}
            onChange={this.onChange}
            type="password"
            placeholder="Confirmar Senha"
          />
        </div>
        {isMatch ? "" : "As senhas não são identicas."}
        <button className="ui button" disabled={isInvalid} type="submit">
          Sign Up
        </button>
      </form>
    );
  }
}

const SignUpLink = () => (
  <p>
    Don't have an account? <Link to="/cadastrar">Sign Up</Link>
  </p>
);

const SignUpForm = withRouter(SignUpFormBase);

export default SignUpPage;

export { SignUpForm, SignUpLink };
