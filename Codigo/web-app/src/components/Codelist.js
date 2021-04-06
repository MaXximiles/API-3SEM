import React, { useEffect, useState } from "react";
import Table from "./Table";
import Dropdown from "./Dropdown";
import restAPI from "../apis/restAPI";

const options = [
  {
    label: "Marte",
    value: 1,
  },
  {
    label: "Saturno",
    value: 2,
  },
  {
    label: "Mercúrio",
    value: 3,
  },
	{
    label: "Plutão",
    value: 4,
  },
	{
    label: "Vênus",
    value: 5,
  },
];

const Codelist = () => {
  const [data, setData] = useState([]);
  const [selectedTrace, setSelectedTrace] = useState([]);
	const [name, setName] = useState("");

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
                <input type="text" name="manualName" placeholder="Nome-PN" value={name} onChange={(e) => {setName(e.target.value)}} />
              </div>
              <div className="field">
                <Dropdown
                  label="Traço"
                  options={options}
                  selected={selectedTrace}
                  onSelectedChange={setSelectedTrace}
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
