import React, { useState, useEffect } from "react";
import Dropdown from "./Dropdown";
import restAPI from "../apis/restAPI";

const DocumentEdit = ({ onSubmit, dataEntry }) => {
  const [name, setName] = useState("");
  const [descricao, setDescricao] = useState("");
  const [codigo, setCodigo] = useState("");
  const [tagOptions, setTagOptions] = useState([]);
  const [selectedTag, setSelectedTag] = useState([]);
  const [prevSelectedTag, setPrevSelectedTag] = useState(null);

  useEffect(() => {
    const getCodelistTags = async () => {
      const { data } = await restAPI.get(
        `/tag/tagstracos?tracoid=${dataEntry.tracodocid}`
      );

      const options = data.map((value) => {
        return { value: value.tagId, label: value.tagNome };
      });

      console.log("tags", options);

      setSelectedTag(options);
      setPrevSelectedTag(options);
    };

    const getTagOptions = async () => {
      const { data } = await restAPI.get(`/tag/`);

      const options = data.map((value) => {
        return { value: value.tagId, label: value.tagNome };
      });

      setTagOptions(options);
    };

    dataEntry && getTagOptions();
    dataEntry && getCodelistTags();

    if (dataEntry) {
      setName(dataEntry.tracodocnome);
      setDescricao(dataEntry.tracodocdescricao);
      setCodigo(dataEntry.tracodoccodigo);
    } else {
      clearFields();
    }
  }, [dataEntry]);

  useEffect(() => {
    const toggleTags = async () => {
      if (selectedTag !== prevSelectedTag && dataEntry && prevSelectedTag) {
        selectedTag.forEach((value) => {
          if (!prevSelectedTag.includes(value)) {
            var addTraces = {
              tracoId: dataEntry.tracodocid,
              tagId: value.value,
            };

            restAPI.post(`/tagtraco/`, addTraces);
          }
        });

        // console.log("Traces to be added", addTraces);
        // if (addTraces !== {}) {
        //   const response = await restAPI.post(`/tagtraco/`, addTraces);
        //   console.log(response);
        // }

        prevSelectedTag.forEach((value) => {
          if (!selectedTag.includes(value)) {
            var removeTraces = {
              tracoId: dataEntry.tracodocid,
              tagId: value.value,
            };

            restAPI.post(`/tagtraco/delete`, removeTraces);
          }
        });

        // console.log("Tags to be removed", removeTraces);
        // if (removeTraces !== {}) {
        //   await restAPI.post(`/tagtraco/delete`, removeTraces);
        // }

        setPrevSelectedTag(selectedTag);
      }
    };

    toggleTags();
  }, [selectedTag, prevSelectedTag, dataEntry]);

  const clearFields = () => {
    setName("");
    setDescricao("");
    setCodigo("");
  };

  const submit = async () => {
    const submitedEntry = {
      tracodocnome: name,
      tracodocdescricao: descricao,
      tracodoccodigo: codigo,
    };

    if (dataEntry) {
      await onSubmit(submitedEntry, dataEntry);
    } else {
      await onSubmit(submitedEntry);
    }

    clearFields();
  };

  return (
    <React.Fragment>
      <div className="content">
        <form className="ui form">
          <div className="field">
            <label>Nome</label>
            <input
              type="text"
              name="name"
              placeholder="Nome"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </div>
          <div className="field">
            <label>Codigo</label>
            <input
              type="text"
              name="codigo"
              placeholder="Código"
              value={codigo}
              onChange={(e) => setCodigo(e.target.value)}
            />
          </div>
          <div className="field">
            <label>Descricao</label>
            <input
              type="text"
              name="descricao"
              placeholder="Descricao"
              value={descricao}
              onChange={(e) => setDescricao(e.target.value)}
            />
          </div>
          <div className="field">
            {dataEntry && (
              <Dropdown
                label="Tags"
                defaultText="Selecione um ou mais tags"
                options={tagOptions}
                selected={selectedTag}
                onSelectedChange={setSelectedTag}
              />
            )}
          </div>
        </form>
      </div>
      <div className="actions">
        <div className="ui positive right labeled icon button" onClick={submit}>
          Confirmar
          <i className="checkmark icon"></i>
        </div>
      </div>
    </React.Fragment>
  );
};

export default DocumentEdit;
