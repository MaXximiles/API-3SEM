import React, { useEffect, useState } from "react";
import Table from "./Table";
import restAPI from "../apis/restAPI";

const Codelist = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    const getData = async () => {
      const response = await restAPI.get("/mock");

      setData(response.data);
    };

    getData();
  }, []);

  return (
    <div className="Codelist">
      <div className="ui one column stackable grid container">
				<div className="column">
					
				</div>
        <div className="column">
          <Table data={data} />
        </div>
      </div>
    </div>
  );
};

export default Codelist;
