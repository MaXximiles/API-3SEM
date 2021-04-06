import React, { useEffect, useState } from "react";
import Table from "./Table";
import Dropdown from "./Dropdown";
import restAPI from "../apis/restAPI";

const options = [
  {
    label: "The Color Red",
    value: "red",
  },
  {
    label: "The Color Green",
    value: "green",
  },
  {
    label: "A Shade of Blue",
    value: "blue",
  },
];

const Codelist = () => {
  const [data, setData] = useState([]);
  const [selected, setSelected] = useState([]);

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
          <div className="ui form">
            <h4 className="ui dividing header">Informações do manual</h4>
            <div className="two fields">
              <div className="field">
                <label>Nome</label>
                <input type="text" name="manualName" placeholder="Nome-PN" />
              </div>
              <div className="field">
                <Dropdown
                  label="Traço"
                  options={options}
                  selected={selected}
                  onSelectedChange={setSelected}
                />
              </div>
            </div>
          </div>
        </div>
        <div className="column">
          <Table data={data} />
        </div>
      </div>
    </div>
  );
};

export default Codelist;
