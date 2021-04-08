import React, { useEffect, useState } from "react";
import Table from "./Table";
import Dropdown from "./Dropdown";
import Modal from "./Modal";
import CodelistEdit from "./CodelistEdit";
import restAPI from "../apis/restAPI";

const options = [
  {
    label: "Mercúrio",
    value: 1,
  },
  {
    label: "Vênus",
    value: 2,
  },
  {
    label: "Terra",
    value: 3,
  },
	{
    label: "Marte",
    value: 4,
  },
	{
    label: "Júpiter",
    value: 5,
  },
	{
    label: "Saturno",
    value: 6,
  },
  {
    label: "Urano",
    value: 7,
  },
  {
    label: "Netuno",
    value: 8,
  },
	{
    label: "Plutão",
    value: 9,
  },
	{
    label: "Sol",
    value: 10,
  },
	{
    label: "Alpha Centauri",
    value: 11,
  },
  {
    label: "VY Canis Majoris",
    value: 12,
  },
  {
    label: "Canopus",
    value: 13,
  },
	{
    label: "Rigel",
    value: 14,
  },
	{
    label: "Vega",
    value: 15,
  },
];

const Codelist = () => {
  const [data, setData] = useState([]);
	const [isEditing, setIsEditing] = useState(false);
	const [dataEntry, setDataEntry] = useState({});
  const [selectedTrace, setSelectedTrace] = useState([]);
	const [name, setName] = useState("");

  useEffect(() => {
    getData();
  }, []);

	const getData = async () => {
		const response = await restAPI.get("/codelist/");

		setData(response.data);
	};


	const onSubmit = async (data) => {
		console.log(data);
		const response = await restAPI.post("/codelist/", data);

		console.log(response);
		setIsEditing(false);
		getData();
	}

	const onEdit = (event) => {
		event.stopPropagation();
		setIsEditing(!isEditing);
	}

	const renderedEditModal = (
    <Modal title="Criando Novo Bloco" isOpen={isEditing} setIsOpen={setIsEditing}>
      <CodelistEdit onSubmit={onSubmit} />
    </Modal>
  );

  return (
    <div className="Codelist">
			{renderedEditModal}
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
									defaultText="Selecione um ou mais traços"
                  options={options}
                  selected={selectedTrace}
                  onSelectedChange={setSelectedTrace}
                />
              </div>
            </div>
          </div>
        </div>
        <div className="column">
          <Table data={data} onEdit={onEdit} />
        </div>
      </div>
    </div>
  );
};

export default Codelist;
