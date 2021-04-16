import React from "react";
import Link from "./Link";

const Header = () => {
  return (
    <div className="ui secondary pointing menu">
      <Link href="/documento" className="item">
        Manuais
      </Link>
      <Link href="/bloco" className="item">
        Blocos
      </Link>
      <Link href="/" className="right item">
        Logout
      </Link>
    </div>
  );
};

export default Header;
