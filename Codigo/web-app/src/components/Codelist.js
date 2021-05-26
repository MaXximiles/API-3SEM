import React, { useEffect, useState } from "react";
import Loader from "./Loader";
import Table from "./CodelistTable";
import Dropdown from "./Dropdown";
import Modal from "./Modal";
import CodelistEdit from "./CodelistEdit";
import CodelistDelete from "./CodelistDelete";
import { useParams } from "react-router-dom";
import restAPI from "../apis/restAPI";

const Codelist = () => {
  const [data, setData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const [isDeleting, setIsDeleting] = useState(false);
  const [dataEntry, setDataEntry] = useState(null);
  const [traceOptions, setTraceOptions] = useState([]);
  const [selectedTrace, setSelectedTrace] = useState([]);

  const { id } = useParams();

  useEffect(() => {
    const getTraceOptions = async () => {
      const { data } = await restAPI.get(`/traco_doc/tracodoc?docid=${id}`);

      const options = data.map((value) => {
        return { value: value.tracodocid, label: value.tracodocnome };
      });

      setTraceOptions(options);
    };

    const getData = async () => {
      setIsLoading(true);

      const response = await restAPI.get(`/codelist/codelistdoc?docid=${id}`);

      setData(response.data);

      setIsLoading(false);
    };

    getTraceOptions();
    getData();
  }, [id]);

  useEffect(() => {
    const getData = async () => {
      setIsLoading(true);

      const response = await restAPI.get(
        `/codelist/blocostracos?docid=${id}&tracoid=${selectedTrace[0].value}`
      );

      console.log(response.data);
      setData(response.data);

      setIsLoading(false);
    };

    selectedTrace[0] && getData();
  }, [id, selectedTrace]);

  const getData = async () => {
    setIsLoading(true);

    const response = await restAPI.get(
      `/codelist/blocostracos?docid=${id}&tracoid=${selectedTrace[0].value}`
    );

    setData(response.data);

    setIsLoading(false);
  };

  const onSubmit = async (data, oldData) => {
    setIsEditing(false);

    if (data === oldData) {
      return;
    }

    const response = oldData
      ? await restAPI.put(`/codelist/${oldData.codelistid}`, data)
      : await restAPI.post(`/codelist/${id}`, data);

    console.log(response);

    getData();
  };

  const onDelete = async (data) => {
    setIsDeleting(false);

    if (!data) {
      return;
    }

    const response = await restAPI.delete(`/codelist/${data.codelistid}`);

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
      <CodelistEdit onSubmit={onSubmit} dataEntry={dataEntry} docId={id} />
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
            <h4 className="ui dividing header">Codelist do Manual</h4>
            <div className="one field">
              <div className="field">
                <Dropdown
                  label="Traço"
                  defaultText="Selecione um ou mais traços"
                  options={traceOptions}
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
