import React, { useEffect, useState } from "react";
import List from "./List";
import { mockupResponse } from "../apis/json";

const App = () => {
  const [folderItems, setFolderItems] = useState([]);

  useEffect(() => {
    setFolderItems(mockupResponse());
  }, []);

  return (
    <div className="ui container">
      <List content={folderItems} />
    </div>
  );
};

export default App;
