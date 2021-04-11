import React from "react";
import Link from "./Link";

const Header = () => {
  return (
    <div className="ui secondary pointing menu">
      <Link href="/codelist" className="item">
        Codelist
      </Link>
      <Link href="/" className="right item">
        Sign Out
      </Link>
    </div>
  );
};

export default Header;
