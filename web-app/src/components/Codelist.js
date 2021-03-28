import React, { useState, useEffect } from "react";
import restAPI from "../apis/restAPI";
import List from "./List";

const Codelist = () => {
  const [content, setContent] = useState([]);

  useEffect(() => {
    const codelist = async () => {
      const { data } = await restAPI.get("/codelist");

      setContent(data);
    };

    if (!content.length) {
      codelist();
    }
  }, [content]);

  return (
    <div className="ui container">
      <List content={content} />
    </div>
  );
};

export default Codelist;
