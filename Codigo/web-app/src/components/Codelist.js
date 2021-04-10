import React, { useEffect, useState } from "react";
import Loader from "./Loader";
import Table from "./Table";
import Dropdown from "./Dropdown";
import Modal from "./Modal";
import CodelistEdit from "./CodelistEdit";
import CodelistDelete from "./CodelistDelete";
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
  const [isLoading, setIsLoading] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const [isDeleting, setIsDeleting] = useState(false);
  const [dataEntry, setDataEntry] = useState(null);
  const [selectedTrace, setSelectedTrace] = useState([]);
  const [name, setName] = useState("");

  useEffect(() => {
    getData();
  }, []);

  const getData = async () => {
    setIsLoading(true);

    const response = await restAPI.get("/codelist/");

    setData(response.data);

    setIsLoading(false);
  };

  const onSubmit = async (data, oldData) => {
    setIsEditing(false);

    if (data === oldData) {
      return;
    }

    const response = oldData
      ? await restAPI.put(`/codelist/${oldData.codelist_id}`, data)
      : await restAPI.post("/codelist/", data);

    console.log(response);

    getData();
  };

  const onDelete = async (data) => {
    setIsDeleting(false);

    if (!data) {
      return;
    }

    const response = await restAPI.delete(`/codelist/${data.codelist_id}`);

    console.log(response);

    getData();
  };

  const deleteItem = (item) => {
    setDataEntry(item);
    setIsDeleting(true);
  };

  const editItem = (item) => {
    setDataEntry(item);
    setIsEditing(true);
  };

  const renderedEditModal = (
    <Modal
      title={`${dataEntry ? "Editando" : "Criando Novo"} Bloco`}
      isOpen={isEditing}
      setIsOpen={setIsEditing}
    >
      <CodelistEdit onSubmit={onSubmit} dataEntry={dataEntry} />
    </Modal>
  );

  const renderedDeleteModal = (
    <Modal
      title="Deletando Bloco"
      isOpen={isDeleting}
      setIsOpen={setIsDeleting}
      size="tiny"
      forceChoice={true}
    >
      <CodelistDelete onSubmit={onDelete} dataEntry={dataEntry} />
    </Modal>
  );

  return (
    <div className="Codelist">
      {renderedEditModal}
      {renderedDeleteModal}
      <div className="ui one column stackable grid container">
        <div className="column">
          <div className="ui form">
            <h4 className="ui dividing header">Informações do manual</h4>
            <div className="two fields">
              <div className="field">
                <label>Nome</label>
                <input
                  type="text"
                  name="manualName"
                  placeholder="Nome-PN"
                  value={name}
                  onChange={(e) => {
                    setName(e.target.value);
                  }}
                />
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
          <Loader isLoading={isLoading} />
          <Table data={data} onEdit={editItem} onDelete={deleteItem} />
        </div>
      </div>
    </div>
  );
};

export default Codelist;
