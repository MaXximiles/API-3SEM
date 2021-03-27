import React, { useState, useEffect } from "react";
import List from "./List";
import restAPI from "../apis/restAPI";

const Codelist = () => {
  const [folderItems, setFolderItems] = useState([]);

  useEffect(() => {
    const codelist = async () => {
      const { data } = await restAPI.get("/codelist");
      console.log(data);
      setFolderItems(data);
    };

    codelist();
  }, []);

  return (
    <div className="ui container">
      <List content={folderItems} />
    </div>
  );
};

export default Codelist;
